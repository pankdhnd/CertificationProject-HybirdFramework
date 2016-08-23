package commonLibs;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private static WebDriver oDriver;	
	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";
	private static Properties oDriverProperties;
	private static String screenshotPath;	
	setup Setup;
	mercuryRegistration Register = new mercuryRegistration();
	mercuryFlightBooking bookFlight = new mercuryFlightBooking();
	dataProvider getTestDataFor = new dataProvider();
	
	
//method keywordUtility
//This method is constructor of this class
 public keywordUtility(){
		//oDriver  = new commonDriver();
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
			//return  Setup.openBrowser();
			return openBrowser();
		}		
		
		//Run method PreLoginValidations
		else if (methodName.equalsIgnoreCase("PreLoginValidations")){
			//setup Setup = new setup(oDriver);
			return Setup.verifyLoginElementsExistance();
		}
		
		//Run method inputLoginDetails
		else if (methodName.equalsIgnoreCase("inputLoginDetails")){
			//setup Setup = new setup(oDriver);
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
			//return Register.registerNewUser();
		}
		
		//Run method InputFlightDetails
		else if (methodName.equalsIgnoreCase("InputFlightDetails")){			
			//return bookFlight.inputFlightDetails();
		}
		
		//Run method selectFlight
		else if (methodName.equalsIgnoreCase("selectFlight")){			
			//return bookFlight.selectFlight();
		}
		
		//Run method bookFlight
		else if (methodName.equalsIgnoreCase("bookFlight")){			
			//return bookFlight.bookFlight();
		}
		//Run method openFlightBookingPage
		else if (methodName.equalsIgnoreCase("openFlightBookingPage")){			
			//return bookFlight.openFlightBookingPage();
		}
				
		//Run method Login
		else if(methodName.equalsIgnoreCase("Login")){
			return Setup.login();			
		}
		
		//Run method Logout
		else if(methodName.equalsIgnoreCase("Logout")){						
	//		return Setup.logout();		
		}
		
		//Run method closeBrowser
		else if(methodName.equalsIgnoreCase("closeBrowser")){						
	//		return Setup.closeBrowser();		
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
			//oDriver.takeScreenshot(filePath +utils.getDateTimeStamp()+".jpg");
			Log.info("Screenshot captured successfully");
			return "Screenshot captured successfully";
		} catch (Exception e) {
			Log.error("ERROR: Could not save screenshot; here are some more details: " + e);			
			return "ERROR: Error occured while saving the screnshot";
		}

	}
 
 public String openBrowser() {	   	  
	   try {
		    String Data[] = getTestDataFor.openBrowser();
			String sBrowserType = Data[0];
			String sURL = Data[1];
			String expectedTitle = Data[2];
	    if (sBrowserType.equalsIgnoreCase("firefox") || sBrowserType.equalsIgnoreCase("ff") || sBrowserType.equalsIgnoreCase("mozilla")) {
	     oDriver = new FirefoxDriver();
	    } else if (sBrowserType.equalsIgnoreCase("chrome") || sBrowserType.equalsIgnoreCase("google chrome") || sBrowserType.equalsIgnoreCase("gc")) {
	     System.setProperty("webdriver.chrome.driver", "D:\\webdrivers\\chromedriver.exe");
	     oDriver = new ChromeDriver();

	    } else if (sBrowserType.equalsIgnoreCase("ie") || sBrowserType.equalsIgnoreCase("internet explorer")) {
	     System.setProperty("webdriver.ie.driver", "D:\\webdrivers\\IEDriverServer.exe");
	     oDriver = new InternetExplorerDriver();
	    } else if (sBrowserType.equalsIgnoreCase("opera")){
	    	System.setProperty("webdriver.opera.driver", "D:\\webdrivers\\operadriver.exe");
	        oDriver = new OperaDriver();
	    } else {
	     throw new Exception("Invalid browser type " + sBrowserType);
	     //System.out.println("Invalid driver type "+sBrowserType+" Setting default browser to Firefox...");
	     //oDriver = new FirefoxDriver();
	    }
	    oDriver.manage().window().maximize();
	    oDriver.manage().deleteAllCookies();
	    oDriver.manage().timeouts().pageLoadTimeout(60l, TimeUnit.SECONDS); //set page load time out
	    oDriver.manage().timeouts().implicitlyWait(60l, TimeUnit.SECONDS); // set implicit wait

	    if (sURL.isEmpty()) {
	     sURL = "about:blank";
	    }
	    oDriver.get(sURL);
	    
	    WebDriverWait oWait = new WebDriverWait(oDriver, 60l);
	    oWait.until(ExpectedConditions.titleIs(expectedTitle));
	    
	    Log.info("Browser opened successfully");
    	initializePageObjects(oDriver);
		return "Browser opened successfully";	
	    
	    	   
	   } catch (Exception e) {
	    System.out.println("Could not open browser; here is some more detail: " + e);	    
	    return "ERROR: Error occured while opening browser";
	   }
	  } //END openBrowser

 public void initializePageObjects(WebDriver wDriver) {
	   Setup = new setup(oDriver);	   
 }
 
	// method closeBrowser
	// This method closes currently open browser, and kills the driver.
	public String closeBrowser() {
		try {
			   if (!oDriver.equals(null)) {
	    		oDriver.quit();
			}		
			Log.info("Browser closed successfully");
			return "Browser closed successfully";
		} catch (Exception e) {
			Log.error("Error occured while closing the browser; here are some more details: "
					+ e);
			return "ERROR: Could not close the browser";
		}
	}
 
 
 
}