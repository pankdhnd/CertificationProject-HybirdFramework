package commonLibs;


import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import appModules.mercuryRegistration;
import appModules.*;
import commonLibs.utils;
import utility.Log;

public class keywordUtility { 
	private static excelDriver oExcelDriver;
	static commonDriver oDriver;	
	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";
	private static Properties oDriverProperties;
	private static String screenshotPath;
	
	
	setup Setup = new setup();
	mercuryRegistration Register = new mercuryRegistration();
	
public keywordUtility(){
		oDriver  = new commonDriver();
		oDriverProperties = utils.getProperties(driverPropertyFile);	
		screenshotPath = oDriverProperties.getProperty("screenshotFolder").trim();
	}	
	 
 public String runMethod(String methodName, String testDataFolder){
try {		
		//int Row, rowCount;
		methodName = methodName.trim();		testDataFolder = testDataFolder.trim();
	
		if (methodName.isEmpty() || testDataFolder.isEmpty()){
			return "ERROR: Invalid Method or Test Data Folder";
		}
		
		oExcelDriver = new excelDriver();
		oExcelDriver.openExcelSheet(testDataFolder+"\\TestData.xlsx");
	
		if (methodName.equalsIgnoreCase("openBrowser")){
			String browserType = oExcelDriver.getCellData(methodName, 1, 2);			
			String URL = oExcelDriver.getCellData(methodName, 2, 2);
			String expectedTitle = oExcelDriver.getCellData(methodName, 3, 2);
			return Setup.openBrowser(browserType, URL, expectedTitle);
		}		
		else if (methodName.equalsIgnoreCase("PreLoginValidations")){
			return Setup.verifyLoginElementsExistance();
		}
		else if (methodName.equalsIgnoreCase("inputLoginDetails")){
			String userName = oExcelDriver.getCellData(methodName, 1, 2);
			String passWord = oExcelDriver.getCellData(methodName, 2, 2);			
			return Setup.inputLoginDetails(userName, passWord);
		}				
		else if (methodName.equalsIgnoreCase("verifyEnteredValues")){
			String userName = oExcelDriver.getCellData(methodName, 1, 2);
			String passWord = oExcelDriver.getCellData(methodName, 2, 2);	
			return Setup.verifyEnteredValues(userName, passWord);
		}
		else if (methodName.equalsIgnoreCase("takeScreenshot")){			
			try {
				oDriver.takeScreenshot(screenshotPath +utils.getDateTimeStamp()+".jpg");
				return "Screenshot saved successfully";
			} catch (Exception e) {
				Log.error("ERROR: Could not save screenshot; here are some more details: ");
				Log.error(e.getStackTrace().toString());
				return "ERROR: Error occured while saving the screnshot";
			}
		}
		else if (methodName.equalsIgnoreCase("RegisterNewUser")){
			String firstName = oExcelDriver.getCellData(methodName, 1, 2);
			String lastName = oExcelDriver.getCellData(methodName, 2, 2);
			String Phone = oExcelDriver.getCellData(methodName, 3, 2);
			String Email = oExcelDriver.getCellData(methodName, 4, 2);
			String Address1 = oExcelDriver.getCellData(methodName, 5, 2);
			String Address2 = oExcelDriver.getCellData(methodName, 6, 2);
			String City = oExcelDriver.getCellData(methodName, 7, 2);
			String State = oExcelDriver.getCellData(methodName, 8, 2);
			String postalCode = oExcelDriver.getCellData(methodName, 9, 2);
			String Country = oExcelDriver.getCellData(methodName, 10, 2);
			String userName = oExcelDriver.getCellData(methodName, 11, 2);
			String passWord = oExcelDriver.getCellData(methodName, 12, 2);
			String confirmPassword = oExcelDriver.getCellData(methodName, 13, 2);
			
			return Register.registerNewUser(firstName, lastName, Phone, Email, Address1, Address2, City, State, postalCode, Country, userName, passWord, confirmPassword);
		}
		
		
		else if(methodName.equalsIgnoreCase("Login"))
		{
			return Setup.login();			
		}
		else if(methodName.equalsIgnoreCase("Logout"))
		{			
			//setup setup = new setup();
			return Setup.logout();		
		}
		else if(methodName.equalsIgnoreCase("OpenFileManager"))
		{			
		//	classFileManager FileManager = new classFileManager();
		//	return FileManager.OpenFileManager();		
		}		
		
		
} catch (Exception e) {
	System.out.println("keywordUtility->runMethod()->Error occured while performing an action; here is some more detail: ");
	   e.printStackTrace();
	   return "ERROR: Exception occured";
}

	 return "";
 }
 
 
 
 /*
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------	
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------
public String performAction(String sAction, By oBy, String Value) {
 try {
  sAction = sAction.trim();
  if (sAction.isEmpty()) {
   return "ERROR: Empty action supplied";
  }

  if (sAction.equalsIgnoreCase("openbrowser")) {
   oDriver.openBrowser(Value, "about:blank");
   return "";
  }

  if (sAction.equalsIgnoreCase("setpageloadtimeout")) {
   oDriver.setPageLoadTimeOut(Long.valueOf(Value));
   return "";
  }

  if (sAction.equalsIgnoreCase("setelementdetectiontimeout")) {
   oDriver.setElementDetectionTimeOut(Long.valueOf(Value));
   return "";
  }

  if (sAction.equalsIgnoreCase("navigatetourl")) {
   oDriver.navigateTo(Value);
   return "";
  }

  if (sAction.equalsIgnoreCase("navigateback")) {
   oDriver.navigateBack();
   return "";
  }

  if (sAction.equalsIgnoreCase("navigateforward")) {
   oDriver.navigateForward();
   return "";
  }

  if (sAction.equalsIgnoreCase("click")) {
   oDriver.click(oBy);
   return "";
  }

  if (sAction.equalsIgnoreCase("clear")) {
   oDriver.clear(oBy);
   return "";
  }

  if (sAction.equalsIgnoreCase("settext")) {
   oDriver.setText(oBy, Value);
   return "";
  }

  if (sAction.equalsIgnoreCase("selectByValue")) {
   oDriver.selectByValue(oBy, Value);
   return "";
  }
  if (sAction.equalsIgnoreCase("selectByVisibleText")) {
   oDriver.selectByVisibleText(oBy, Value);
   return "";
  }

  if (sAction.equalsIgnoreCase("selectByIndex")) {
   oDriver.selectByIndex(oBy, Integer.parseInt(Value));
   return "";
  }

  if (sAction.equalsIgnoreCase("getItemCount")) {
   WebElement Element = oDriver.getDriver().findElement(oBy);
   Select oList = new Select(Element);
   return String.valueOf(oList.getOptions().size());
  }

  if (sAction.equalsIgnoreCase("switchToFrame")) {
   oDriver.switchToFrame(oBy);
   return "";
  }

  if (sAction.equalsIgnoreCase("mouseOver")) {
   oDriver.moveToElement(oBy);
   return "";
  }
  if (sAction.equalsIgnoreCase("JSClick")) {
   oDriver.JSClick(oBy);
   return "";
  }

  if (sAction.equalsIgnoreCase("switchToChildWindow")) {
   oDriver.switchToChildWindow();
   return "";
  }

  if (sAction.equalsIgnoreCase("switchToWindow")) {
   oDriver.switchToWindow(Value);
   return "";
  }

  if (sAction.equalsIgnoreCase("closebrowser")) {
   oDriver.closeBrowser();
   return "";
  }

  if (sAction.equalsIgnoreCase("waitTillElementVisible")) {
   oDriver.waitTillElementVisible(oBy, Long.valueOf(Value));
   return "";
  }

  if (sAction.equalsIgnoreCase("waitTillElementClickable")) {
   oDriver.waitTillElementClickable(oBy, Long.valueOf(Value));
   return "";
  }

  if (sAction.equalsIgnoreCase("takeScreenshot")) {
   oDriver.takeScreenshot(Value);
   return "";
  }

  if (sAction.equalsIgnoreCase("accpetAlert")) {
   oDriver.acceptAlert();
   return "";
  }

  if (sAction.equalsIgnoreCase("rejectAlert")) {
   oDriver.dismissAlert();
   return "";
  }

  if (sAction.equalsIgnoreCase("refreshPage")) {
   oDriver.refreshPage();
   return "";
  }

  if (sAction.equalsIgnoreCase("getcurrenturl")) {
   return oDriver.getCurrentURL();
  }

  if (sAction.equalsIgnoreCase("getAlertText")) {
   return oDriver.getAlertText();
  }

  if (sAction.equalsIgnoreCase("getTitle")) {
   oDriver.getTitle();
  }

  if (sAction.equalsIgnoreCase("isSelected")) {
   return String.valueOf(oDriver.isSelected(oBy));
  }

  if (sAction.equalsIgnoreCase("isEnabled")) {
   return String.valueOf(oDriver.isEnabled(oBy));
  }

  return "Error: invalid keyword";

 } catch (Exception e) {
  System.out.println("keywordUtility->performAction()->Error occured while performing an action; here is some more detail: ");
  e.printStackTrace();
  return "ERROR: Exception occured";
 }
}

*/
 

}