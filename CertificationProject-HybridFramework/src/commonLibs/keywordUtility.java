package commonLibs;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import appModules.setup;

public class keywordUtility { 
	private static excelDriver oExcelDriver;
	static commonDriver oDriver;
	setup Setup = new setup();
	
public keywordUtility(){
		oDriver  = new commonDriver();
		
	}	
	 
 public String runMethod(String methodName, String testDataFolder){
try {		
		//int Row, rowCount;
		methodName = methodName.trim();
		testDataFolder = testDataFolder.trim();
	
		if (methodName.isEmpty() || testDataFolder.isEmpty()){
			return "ERROR: Invalid Method or Test Data Folder";
		}
		
		oExcelDriver = new excelDriver();
		oExcelDriver.openExcelSheet(testDataFolder+"\\TestData.xlsx");
	
		if (methodName.equalsIgnoreCase("openBrowser")){
			String browserType = oExcelDriver.getCellData(methodName, 1, 2);			
			String URL = oExcelDriver.getCellData(methodName, 2, 2);
			return Setup.openBrowser(browserType, URL);
		}		
		else if (methodName.equalsIgnoreCase("PreLoginValidations")){
			return Setup.verifyLoginElementsExistance();
		}
		else if (methodName.equalsIgnoreCase("inputLoginDetails")){
			String userName = oExcelDriver.getCellData(methodName, 1, 2);
			String passWord = oExcelDriver.getCellData(methodName, 2, 2);
			System.out.println(userName+"  "+passWord);
			return Setup.inputLoginDetails(userName, passWord);
		}		
		
		else if (methodName.equalsIgnoreCase("verifyEnteredValues")){
			String userName = oExcelDriver.getCellData(methodName, 1, 2);
			String passWord = oExcelDriver.getCellData(methodName, 2, 2);	
			return Setup.verifyEnteredValues(userName, passWord);
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