package commonLibs;


import java.util.concurrent.TimeUnit;
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

public class keywordUtility { 
//*******************************************************************************************************************************************************************
// This class acts as a Keyword control panel. This class maintains the keywords, or lets call it test steps; along with the action to be performed when that keyword
// is used. When the provided keyword matches, the method enclosed within is called. This method returns the output of the action performed to the calling class
//*******************************************************************************************************************************************************************	
	
	//Variable declaration section	
    private static WebDriver wDriver;		
	setup Setup;
	mercuryRegistration Register;
	mercuryFlightBooking bookFlight;
	dataProvider getTestDataFor = new dataProvider();
	
	
//method keywordUtility
//This method is constructor of this class
 public keywordUtility(){
	
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
			Setup = new setup(wDriver);
			return Setup.verifyLoginElementsExistance();
		}
				
		//Run method PreLoginValidations
		else if (methodName.equalsIgnoreCase("openLoginURL")){
			Setup = new setup(wDriver);
			return Setup.openLoginURL();				
		}	
		
		//Run method inputLoginDetails
		else if (methodName.equalsIgnoreCase("inputLoginDetails")){
			Setup = new setup(wDriver);;
			return Setup.inputLoginDetails();
		}				
		
		//Run method verifyEnteredValues
		else if (methodName.equalsIgnoreCase("verifyEnteredValues")){
			Setup = new setup(wDriver);
			return Setup.verifyEnteredValues();
		}
		
		//Run method takeScreenshot
		else if (methodName.equalsIgnoreCase("takeScreenshot")){			
			return utils.takeScreenshot(wDriver, utils.getProperty("screenshotFolder"));
		}
		
		//Run method RegisterNewUser
		else if (methodName.equalsIgnoreCase("RegisterNewUser")){
			Register = new mercuryRegistration(wDriver);
			return Register.registerNewUser();
		}
		
		//Run method InputFlightDetails
		else if (methodName.equalsIgnoreCase("InputFlightDetails")){
			bookFlight = new mercuryFlightBooking(wDriver);
			return bookFlight.inputFlightDetails();
		}
		
		//Run method selectFlight
		else if (methodName.equalsIgnoreCase("selectFlight")){
			bookFlight = new mercuryFlightBooking(wDriver);
			return bookFlight.selectFlight();
		}
		
		//Run method bookFlight
		else if (methodName.equalsIgnoreCase("bookFlight")){
			bookFlight = new mercuryFlightBooking(wDriver);
			return bookFlight.bookFlight();
		}
		//Run method openFlightBookingPage
		else if (methodName.equalsIgnoreCase("openFlightBookingPage")){
			bookFlight = new mercuryFlightBooking(wDriver);
			return bookFlight.openFlightBookingPage();
		}
				
		//Run method Login
		else if(methodName.equalsIgnoreCase("Login")){
			Setup = new setup(wDriver);
			return Setup.login();			
		}
		
		//Run method Logout
		else if(methodName.equalsIgnoreCase("Logout")){
			Setup = new setup(wDriver);
			return Setup.logout();		
		}
		
		//Run method closeBrowser
		else if(methodName.equalsIgnoreCase("closeBrowser")){						
			return closeBrowser();		
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
//method openBrowser
//This method opens the browser and gets the base URL
 public String openBrowser() {	   	  
	   try {
		   String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();	
		    String Data[] = dataProvider.openBrowser();
			String sBrowserType = Data[0];
			String sURL = Data[1];
			String expectedTitle = Data[2];
			Extent.logInfo(currentMethodName,"Choosing in between browser types");
	    if (sBrowserType.equalsIgnoreCase("firefox") || sBrowserType.equalsIgnoreCase("ff") || sBrowserType.equalsIgnoreCase("mozilla")) {
	     wDriver = new FirefoxDriver();
	    } else if (sBrowserType.equalsIgnoreCase("chrome") || sBrowserType.equalsIgnoreCase("google chrome") || sBrowserType.equalsIgnoreCase("gc")) {
	     System.setProperty("webdriver.chrome.driver", "D:\\webdrivers\\chromedriver.exe");
	     wDriver = new ChromeDriver();

	    } else if (sBrowserType.equalsIgnoreCase("ie") || sBrowserType.equalsIgnoreCase("internet explorer")) {
	     System.setProperty("webdriver.ie.driver", "D:\\webdrivers\\IEDriverServer.exe");
	     wDriver = new InternetExplorerDriver();
	    } else if (sBrowserType.equalsIgnoreCase("opera")){
	    	System.setProperty("webdriver.opera.driver", "D:\\webdrivers\\operadriver.exe");
	        wDriver = new OperaDriver();
	    } else {
	     throw new Exception("Invalid browser type " + sBrowserType);
	     //System.out.println("Invalid driver type "+sBrowserType+" Setting default browser to Firefox...");
	    //wDriver = new FirefoxDriver();
	    }
	    Extent.logInfo(currentMethodName,"Launching browser "+ sBrowserType);
	    wDriver.manage().window().maximize();
	    wDriver.manage().deleteAllCookies();
	    wDriver.manage().timeouts().pageLoadTimeout(60l, TimeUnit.SECONDS); //set page load time out
	    wDriver.manage().timeouts().implicitlyWait(60l, TimeUnit.SECONDS); // set implicit wait
	    Extent.logInfo(currentMethodName,"Navigating to the URL");
	    if (sURL.isEmpty()) {
	     sURL = "about:blank";
	    }
	    wDriver.get(sURL);
	    Extent.logInfo(currentMethodName,"Verifying title of the page opened");
	    WebDriverWait oWait = new WebDriverWait(wDriver, 60l);
	    oWait.until(ExpectedConditions.titleIs(expectedTitle));	 
	    Extent.logInfo(currentMethodName,"Title verified successfully.");
	    Extent.logInfo(currentMethodName,"Browser opened successfully");    	
		return "Browser opened successfully";		    
	    	   
	   } catch (Exception e) {
	    System.out.println("Could not open browser; here is some more detail: " + e);
	    Extent. logError("openBrowser","Error occured while opening browser; here is some more detail: " + e);
	    return "ERROR: Error occured while opening browser";
	   }
	  } //END openBrowser

 
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
// method closeBrowser
// This method closes currently open browser, and kills the driver.
 public String closeBrowser() {
		try {
			 Extent.logInfo("closeBrowser","Closing browser...");
			   if (!wDriver.equals(null)) {
	    		wDriver.quit();
			}		
			   Extent.logInfo("closeBrowser","Browser closed successfully");
			return "Browser closed successfully";
		} catch (Exception e) {
			Extent.logError("closeBrowser","Error occured while closing the browser; here are some more details: "
					+ e);
			return "ERROR: Could not close the browser";
		}
	}
 
 
 
}