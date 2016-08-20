package commonLibs;


import java.util.Properties;

import commonLibs.dataProvider;
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
	mercuryFlightBooking bookFlight = new mercuryFlightBooking();
	dataProvider getTestDataFor = new dataProvider();
	
	
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
	
		//Run method openBrowser
		if (methodName.equalsIgnoreCase("openBrowser")){
			return  Setup.openBrowser();
		}		
		
		//Run method PreLoginValidations
		else if (methodName.equalsIgnoreCase("PreLoginValidations")){
			return Setup.verifyLoginElementsExistance();
		}
		
		//Run method inputLoginDetails
		else if (methodName.equalsIgnoreCase("inputLoginDetails")){						
			return Setup.inputLoginDetails();
		}				
		
		//Run method verifyEnteredValues
		else if (methodName.equalsIgnoreCase("verifyEnteredValues")){				
			return Setup.verifyEnteredValues();
		}
		
		//Run method takeScreenshot
		else if (methodName.equalsIgnoreCase("takeScreenshot")){			
			Setup.takeScreenshot(screenshotPath);
		}
		
		//Run method RegisterNewUser
		else if (methodName.equalsIgnoreCase("RegisterNewUser")){				
			return Register.registerNewUser();
		}
		
		//Run method InputFlightDetails
		else if (methodName.equalsIgnoreCase("InputFlightDetails")){			
			return bookFlight.inputFlightDetails();
		}
		
		//Run method Login
		else if(methodName.equalsIgnoreCase("Login")){
			return Setup.login();			
		}
		
		//Run method Logout
		else if(methodName.equalsIgnoreCase("Logout")){						
			return Setup.logout();		
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