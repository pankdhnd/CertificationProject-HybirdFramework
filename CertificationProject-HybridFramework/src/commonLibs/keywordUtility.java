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
			return takeScreenshot(screenshotPath);
		}
		
		//Run method RegisterNewUser
		else if (methodName.equalsIgnoreCase("RegisterNewUser")){				
			return Register.registerNewUser();
		}
		
		//Run method InputFlightDetails
		else if (methodName.equalsIgnoreCase("InputFlightDetails")){			
			return bookFlight.inputFlightDetails();
		}
		
		//Run method selectFlight
		else if (methodName.equalsIgnoreCase("selectFlight")){			
			return bookFlight.selectFlight();
		}
		
		//Run method bookFlight
				else if (methodName.equalsIgnoreCase("bookFlight")){			
					return bookFlight.bookFlight();
				}
		//Run method openFlightBookingPage
				else if (methodName.equalsIgnoreCase("openFlightBookingPage")){			
					return bookFlight.openFlightBookingPage();
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
 
 public String takeScreenshot(String filePath){
		try {
			oDriver.takeScreenshot(filePath +utils.getDateTimeStamp()+".jpg");
			return "Screenshot saved successfully";
		} catch (Exception e) {
			Log.error("ERROR: Could not save screenshot; here are some more details: ");
			Log.error(e.getStackTrace().toString());
			return "ERROR: Error occured while saving the screnshot";
		}

	}
 
  

}