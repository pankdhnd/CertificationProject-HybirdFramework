package commonLibs;


import java.util.Properties;

import commonLibs.dataProvider;
import appModules.mercuryRegistration;
import appModules.*;
import commonLibs.utils;
import utility.Log;

public class keywordUtility { 
//*******************************************************************************************************************************************************************
// This class acts as a Keyword control panel. This class maintains the keywords, or lets call it test steps; along with the action to be performed when that keyword
// is used. When the provided keyword matches, the method enclosed within is called. This method returns the output of the action performed to the calling class
//*******************************************************************************************************************************************************************	
	
	//Variable declaration section	
	static commonDriver oDriver;	
	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";
	private static Properties oDriverProperties;
	private static String screenshotPath;	
	setup Setup = new setup();
	mercuryRegistration Register = new mercuryRegistration();
	mercuryFlightBooking bookFlight = new mercuryFlightBooking();
	dataProvider getTestDataFor = new dataProvider();
	
//method keywordUtility
//This method is constructor of this class
 public keywordUtility(){
		oDriver  = new commonDriver();
		oDriverProperties = utils.getProperties(driverPropertyFile);	
		screenshotPath = oDriverProperties.getProperty("screenshotFolder").trim();
	}	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//method runMethod
//This method accepts the action to be perform, and matches it with the list enclosed, and if found, calls the specific method and returns the result to the calling class
 public String runMethod(String methodName){
try {			
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
		
		//Run method closeBrowser
		else if(methodName.equalsIgnoreCase("closeBrowser")){						
			return Setup.closeBrowser();		
		}
		
		
} catch (Exception e) {
	System.out.println("keywordUtility->runMethod()->Error occured while performing an action; here is some more detail: ");
	   e.printStackTrace();
	   return "ERROR: Exception occured";
}

	 return "";
 }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//method takeScreenshot
//This method captures the screenshot of the current page
 public String takeScreenshot(String filePath){
		try {			
			oDriver.takeScreenshot(filePath +utils.getDateTimeStamp()+".jpg");
			Log.info("Screenshot captured successfully");
			return "Screenshot captured successfully";
		} catch (Exception e) {
			Log.error("ERROR: Could not save screenshot; here are some more details: " + e);			
			return "ERROR: Error occured while saving the screnshot";
		}

	}
 
  

}