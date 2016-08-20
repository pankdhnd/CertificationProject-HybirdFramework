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

 private static WebDriver oDriver;
 private long pageLoadTimeOut;
 private long elementDetectionTimeOut;

 public commonDriver() {
   pageLoadTimeOut = 60l;
   elementDetectionTimeOut = 60l;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 public void setPageLoadTimeOut(long pageLoadTimeOut) {
   this.pageLoadTimeOut = pageLoadTimeOut;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//	
 public void setElementDetectionTimeOut(long elementDetectionTimeOut) {
   this.elementDetectionTimeOut = elementDetectionTimeOut;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//	
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
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
 public String getText(By oBy) {
   try {
    return oDriver.findElement(oBy).getText();
   } catch (Exception e) {
    System.out.println("Could not set the given text; here is some more detail: ");
    e.printStackTrace();
    return "";
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
 public WebDriver getDriver() {
	 return commonDriver.oDriver;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
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
 public void switchToAlert(String Alert) {
   try {
    oDriver.switchTo().alert();
   } catch (Exception e) {
    System.out.println("Could not switch to specified alert; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
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
public void implicitlyWait(int Seconds){
	oDriver.manage().timeouts().implicitlyWait(Seconds, TimeUnit.SECONDS);
}
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//
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
public void highlightElement(By oBy){
	WebElement element = oDriver.findElement(oBy);
	JavascriptExecutor js = (JavascriptExecutor) oDriver;
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
}

//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//

 //public void switchToFrame(int frameId) {
//   try {
//    oDriver.switchTo().frame(frameId);
//   } catch (Exception e) {
//    System.out.println("Could not switch to specified frame; here is some more detail: ");
//    e.printStackTrace();
//   }
//  }
//  //------------------------------------------------------------------------------------------------------------------------------------------------------//
//  //------------------------------------------------------------------------------------------------------------------------------------------------------//
// public void switchToFrame(String frameName) {
//   try {
//    oDriver.switchTo().frame(frameName);
//   } catch (Exception e) {
//    System.out.println("Could not switch to specified frame; here is some more detail: ");
//    e.printStackTrace();
//   }
//  }
//------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------//










} //END CLASS