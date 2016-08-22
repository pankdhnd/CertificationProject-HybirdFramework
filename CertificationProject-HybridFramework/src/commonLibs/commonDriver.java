package commonLibs;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Log;

import java.io.*;

public class commonDriver {
//*******************************************************************************************************************************************************************
// This class acts as a library of various actions, like click, setText, getText etc. The calling class calls the methods of this class by suppling 'By' WebElement
// Methods enclosed within this class performs various actions on the provided By WebElement and returns the result whenever needed
//*******************************************************************************************************************************************************************
	
 private static WebDriver oDriver;
 private long pageLoadTimeOut;
 private long elementDetectionTimeOut;

 //This is the constructor of the current class. This sets default value for pageLoadTimeOut and elementDetectionTimeout, in case automation engineer doesn't want to set it everywhere. 
 public commonDriver() {
   pageLoadTimeOut = 60l;
   elementDetectionTimeOut = 60l;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method setPageLoadTimeOut
  //This method sets the page load time out to user provided value
 public void setPageLoadTimeOut(long pageLoadTimeOut) {
   this.pageLoadTimeOut = pageLoadTimeOut;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method setElementDetectionTimeOut
  //This methods set the element detection timeout to a user provided value
 public void setElementDetectionTimeOut(long elementDetectionTimeOut) {
   this.elementDetectionTimeOut = elementDetectionTimeOut;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//	
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method openBrowser
  //This method opens the browser with the provided browser type, and navigates to the URL provided
 public void openBrowser(String sBrowserType, String sURL) {
   sBrowserType = sBrowserType.trim();
   sURL = sURL.trim();
  
   try {

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
    oDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeOut, TimeUnit.SECONDS); //set page load time out
    oDriver.manage().timeouts().implicitlyWait(elementDetectionTimeOut, TimeUnit.SECONDS); // set implicit wait

    if (sURL.isEmpty()) {
     sURL = "about:blank";
    }
    oDriver.get(sURL);
   } catch (Exception e) {
    System.out.println("Could not open browser; here is some more detail: ");
    e.printStackTrace();
   }
  } //END openBrowser
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method closeBrowser
  //This method closes the browser, and kills the WebDriver
 public void closeBrowser() {
   try {
    if (!oDriver.equals(null)) {
    		oDriver.quit();
    }

   } catch (Exception e) {
    System.out.println("Could not close browser; here is some more detail: ");
    e.printStackTrace();
   }
  } //END closeBrowser
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method click
  //This methods performs click action on the element provided 
 public void click(By oBy) {
   try {
    oDriver.findElement(oBy).click();
   } catch (Exception e) {
    System.out.println("Could not click the given element; here is some more detail: ");
    e.printStackTrace();
   }
  } //END click
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method clear
 //This method clears the text from the element provided
 public void clear(By oBy) {
   try {
    oDriver.findElement(oBy).clear();
   } catch (Exception e) {
    System.out.println("Could not clear the given element; here is some more detail: ");
    e.printStackTrace();
   }
  } //end clear
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method setText
  //This method performs type operation in the element provided
 public void setText(By oBy, String Text) {
   try {
    oDriver.findElement(oBy).sendKeys(Text);
   } catch (Exception e) {
    System.out.println("commonDriver()->setText()->Could not set the given text "+Text+"; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method getText
  //This method fetches text from the element provided
 public String getText(By oBy) {
   try {
    return oDriver.findElement(oBy).getText();
   } catch (Exception e) {
    System.out.println("Could not get text; here is some more detail: ");
    e.printStackTrace();
    return "";
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method getDriver
  //This method returns an instance of WebDriver to the calling method
 public WebDriver getDriver() {
	 return commonDriver.oDriver;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method getTitle
  //This method returns the page title to the calling method
 public String getTitle() {
   try {
    return oDriver.getTitle();
   } catch (Exception e) {
    System.out.println("Could not get page title; here is some more detail: ");
    e.printStackTrace();
    return "";
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method getCurrentURL
  //This method returns current page URL to the calling method
 public String getCurrentURL() {
   try {
    return oDriver.getCurrentUrl();
   } catch (Exception e) {
    System.out.println("Could not get currnt URL; here is some more detail: ");
    e.printStackTrace();
    return "";
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method navigateTo
  //This method navigates to the given URL
 public void navigateTo(String sURL) {
   try {
    oDriver.navigate().to(sURL);
   } catch (Exception e) {
    System.out.println("Could not navgiate to the specified URL; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method navigateBack
  //This method navigates back to the previous page
 public void navigateBack() {
   try {
    oDriver.navigate().back();
   } catch (Exception e) {
    System.out.println("Could not navgiate to back page; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //navigateForward
  //This method navigates to the next page.
 public void navigateForward() {
   try {
    oDriver.navigate().forward();
   } catch (Exception e) {
    System.out.println("Could not navgiate to forward page; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method refreshPage
  //This method reloads the currently open page
 public void refreshPage() {
   try {
    oDriver.navigate().refresh();
   } catch (Exception e) {
    System.out.println("Could not navgiate refresh the page; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method selectByIndex
  //This method selects an element from Select by index
 public void selectByIndex(By oBy, int index) {
   try {
    WebElement element = oDriver.findElement(oBy);
    Select select = new Select(element);
    select.selectByIndex(index);
   } catch (Exception e) {
    System.out.println("Could not select given element; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method selectByValue
  //This method selects an element from Select by value
 public void selectByValue(By oBy, String value) {
   try {
	
    WebElement element = oDriver.findElement(oBy);
    Select select = new Select(element);
    select.selectByValue(value);
   } catch (Exception e) {
	Log.error("Could not find value: "+value + " in the dropdown");
	Log.error("Could not select given element; here is some more detail: "+ e);
    System.out.println("Could not select given element; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method selectByVisibleText
  //This method selects an element from Select by visible text
 public void selectByVisibleText(By oBy, String visibleText) {
   try {	    
    WebElement element = oDriver.findElement(oBy);
    Select select = new Select(element);
    select.selectByVisibleText(visibleText);

   } catch (Exception e) {
	Log.error("Could not select given element; here is some more detail: "+ e);
    System.out.println("Could not select given element; here is some more detail: ");    
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method isSelected
  //This method returns selection status of an element
 public boolean isSelected(By oBy) {
   try {
    return oDriver.findElement(oBy).isSelected();
   } catch (Exception e) {
    System.out.println("Could not determine element status; here is some more detail: ");
    e.printStackTrace();
    return false;
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method isVisible
  //This method returns the visibility of an element
 public boolean isVisible(By oBy) {
   try {
    return oDriver.findElement(oBy).isDisplayed();
   } catch (Exception e) {
    System.out.println("Could not determine element visibility; here is some more detail: ");
    e.printStackTrace();
    return false;
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method isEnabled
  //This method returns the enabled/disabled status (read only property) for an element
 public boolean isEnabled(By oBy) {
   try {
    return oDriver.findElement(oBy).isEnabled();
   } catch (Exception e) {
    System.out.println("Could not determine element state; here is some more detail: ");
    e.printStackTrace();
    return false;
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method switchToFrame
  //This method switches the control to the given frame
 public void switchToFrame(By oBy) {
   try {
    WebElement frameElement = oDriver.findElement(oBy);
    oDriver.switchTo().frame(frameElement);
   } catch (Exception e) {
    System.out.println("Could not switch to specified frame; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method switchToChildWindow
  //This method switches the control to the child window
 public void switchToChildWindow() {
   try {
    String windowHandle = oDriver.getWindowHandles().toArray()[1].toString();
    oDriver.switchTo().window(windowHandle);
   } catch (Exception e) {
    System.out.println("Could not switch to child window; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method switchToWindow
  //This method switches the control to a specific window
 public void switchToWindow(String windowHandle) {
   try {
    oDriver.switchTo().window(windowHandle);
   } catch (Exception e) {
    System.out.println("Could not switch to specified window; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method getWindowHandle
  //This method returns the window handle
 public String getWindowHandle() {
   try {
    return oDriver.getWindowHandle();
   } catch (Exception e) {
    System.out.println("Could not get the window handle; here is some more detail: ");
    e.printStackTrace();
    return "";
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method getWindowHandles
  //This method returns a set of window handles
 public Set getWindowHandles() {
   try {
    return oDriver.getWindowHandles();
   } catch (Exception e) {
    System.out.println("Could not get window handles; here is some more detail: ");
    e.printStackTrace();
   }
   return null;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //method waitTillElementVisible
  //This method waits for the visibility of given element for the given time
 public void waitTillElementVisible(By oBy, long timeOut) {

   try {
    WebDriverWait oWait = new WebDriverWait(oDriver, timeOut);
    oWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
   } catch (Exception e) {
    System.out.println("Error while waiting for element to be visible; here is some more detail: ");
    e.printStackTrace();
   }
  }
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method waitTillElementClickable
 //This method waits for given element to be clickable, for the given time
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 public void waitTillElementClickable(By oBy, long timeOut) {
   try {
	 if (oDriver == null){
		 System.out.println("Odriver is NULLLLLLLL");
	 }
    WebDriverWait oWait = new WebDriverWait(oDriver, timeOut);
    oWait.until(ExpectedConditions.elementToBeClickable(oBy));
   } catch (Exception e) {
    System.out.println("commonDriver()->waitTillElementClickable()->Error while waiting for element to be clickable; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method waitTillAlertVisible
 //This method waits for an alert to be visible
 public void waitTillAlertVisible(long timeOut) {
	   try {
		 if (oDriver == null){
			 System.out.println("Odriver is NULLLLLLLL");
		 }
	    WebDriverWait oWait = new WebDriverWait(oDriver, timeOut);
	    oWait.until(ExpectedConditions.alertIsPresent());
	   } catch (Exception e) {
	    System.out.println("commonDriver()->waitTillElementClickable()->Error while waiting for element to be clickable; here is some more detail: ");
	    e.printStackTrace();
	   }
	  }
	  //------------------------------------------------------------------------------------------------------------------------------------------------------//
	  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method rightClick
 //This method performs right click operation
public void rightClick(By oBy) {
   try {
    Actions action = new Actions(oDriver);
    action.contextClick(oDriver.findElement(oBy)).build().perform();
   } catch (Exception e) {
    System.out.println("Could not right click the specified element; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method doubleClick
//This method performs double click action
public void doubleClick(By oBy) {
   try {
    Actions action = new Actions(oDriver);
    action.doubleClick(oDriver.findElement(oBy)).build().perform();

   } catch (Exception e) {
    System.out.println("Could not double click the specified element; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method moveToElement
//This method moves mouse cursor to the given element
public void moveToElement(By oBy) {
   try {
    Actions action = new Actions(oDriver);
    action.moveToElement(oDriver.findElement(oBy)).build().perform();
   } catch (Exception e) {
    System.out.println("Could not move to the specified element; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method keyDown
//This method performs key down operation
public void keyDown(Keys Key) {
  try {
   Actions action = new Actions(oDriver);
   action.sendKeys(Key).build().perform();
  } catch (Exception e) {
   System.out.println("Could not input the keyboard key; here is some more detail: ");
   e.printStackTrace();
  }
 }
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method switchToAlert
//This method switches control to an open alert
 public void switchToAlert() {
   try {
    oDriver.switchTo().alert();
   } catch (Exception e) {
    System.out.println("Could not switch to specified alert; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method acceptAlert
 //This metods accepts an open alert 
 public void acceptAlert() {
   try {
    oDriver.switchTo().alert().accept();
   } catch (Exception e) {
    System.out.println("Could not accept the specified alert; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method dismissAlert
 //This methods dismisses an open alert
public void dismissAlert() {
   try {
    oDriver.switchTo().alert().dismiss();
   } catch (Exception e) {
    System.out.println("Could not dismiss the specified alert; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method getAlertText
//This method returns alert text to the calling method
 public String getAlertText() {
   try {
    return oDriver.switchTo().alert().getText();
   } catch (Exception e) {
    System.out.println("Could not get alert text; here is some more detail: ");
    e.printStackTrace();
    return "";
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method takeScreenshot
 //This method captures the screenshot of the current page
 public void takeScreenshot(String filePath) {
   try {
    if (new File(filePath).exists()) {
     throw new Exception("Screenshot filename already exists");
    }
    File scrFile = ((TakesScreenshot) oDriver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File(filePath));
   } catch (Exception e) {
    System.out.println("Could not take screenshot; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //------------------------------------------------------------------------------------------------------------------------------------------------------//
 //method JSClick
 //This method performs JavaScript click action
public void JSClick(By oBy) {
  try {
   JavascriptExecutor JSExecutor = (JavascriptExecutor) oDriver;
   JSExecutor.executeScript("arguments[0].click();", oBy);
  } catch (Exception e) {
   System.out.println("Could click using JAVA Script; here is some more detail: ");
   e.printStackTrace();
  }
 }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method implicitlyWait
//This method is implicit wait
public void implicitlyWait(int Seconds){
	oDriver.manage().timeouts().implicitlyWait(Seconds, TimeUnit.SECONDS);
}
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method getAttribute
//This method returns the value for the given element
public String getAttribute(By oBy, String Attribute) {
	   try {
	    return oDriver.findElement(oBy).getAttribute(Attribute);
	   } catch (Exception e) {
	    System.out.println("Could not get alert text; here is some more detail: ");
	    e.printStackTrace();
	    return "";
	   }
	  }
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//method highlightElement
//This method highlights the controls in yellow
public void highlightElement(By oBy){
	WebElement element = oDriver.findElement(oBy);
	JavascriptExecutor js = (JavascriptExecutor) oDriver;
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
}
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//










} //END OF CLASS