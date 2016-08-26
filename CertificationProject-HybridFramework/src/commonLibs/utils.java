package commonLibs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils {

	public utils() {
		DOMConfigurator.configure("log4j.xml");
	}

	public static void waitForSeconds(long Seconds) {
		try {
			Thread.sleep(Seconds * 1000L);
		} catch (Exception e) {
			// Intentionally empty
		}
	}

	public static Properties getProperties(String propertyFileName) {
		try {

			InputStream ofileReader;
			Properties oProperty;

			ofileReader = new FileInputStream(propertyFileName);
			oProperty = new Properties();
			oProperty.load(ofileReader);
			return oProperty;
		} catch (Exception e) {
			System.out.println("utils->getProperties()->Error while reading config file; here is some more detail: ");
			e.printStackTrace();
			return null;
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	public static By getLocatorBy(String locatorString) {
		try {

			String[] Locator;
			locatorString = locatorString.trim();

			// if(locatorString.isEmpty() || locatorString.contains(":=")){
			// throw new Exception ("utils->getLocatorBy()->Invalid locator");
			// }
			if (locatorString.isEmpty()) {
				throw new Exception("utils->getLocatorBy()->Invalid locator");
			}

			Locator = locatorString.split(":=");

			if (Locator[0].equalsIgnoreCase("id")) {
				return By.id(Locator[1]);
			}

			if (Locator[0].equalsIgnoreCase("class")) {
				return By.className(Locator[1]);
			}

			if (Locator[0].equalsIgnoreCase("xpath")) {
				return By.xpath(Locator[1]);
			}

			if (Locator[0].equalsIgnoreCase("css")) {
				return By.cssSelector(Locator[1]);
			}

			if (Locator[0].equalsIgnoreCase("link")) {
				return By.linkText(Locator[1]);
			}

			if (Locator[0].equalsIgnoreCase("partiallink")) {
				return By.partialLinkText(Locator[1]);
			}

			if (Locator[0].equalsIgnoreCase("name")) {
				return By.name(Locator[1]);
			}

			if (Locator[0].equalsIgnoreCase("tagname")) {
				return By.tagName(Locator[1]);
			}

			throw new Exception("utils->getLocatorBy()->Invalid locator");
		} catch (Exception e) {
			System.out.println("utils->getLocatorBy()->Error while getting locator; here is some more detail: ");
			e.printStackTrace();
			return null;
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	public static String getDateTimeStamp() {

		try {
			Date todaysDate;
			String[] dateInParts;
			String finalDate;

			todaysDate = new Date();

			dateInParts = todaysDate.toString().split(" ");
			finalDate = dateInParts[1] + "_" + dateInParts[2] + "_" + dateInParts[5] + "_" + dateInParts[3];
			finalDate = finalDate.replace(":", "_");
			return finalDate;
		} catch (Exception e) {
			System.out.println(
					"utils->getDateTimeStamp()->Error while getting/converting date/time; here is some more detail: ");
			e.printStackTrace();
			return null;
		}

	}

	public static void highlightElement(WebDriver oDriver, WebElement Element) {
		// WebElement element = oDriver.findElement(oBy);
		JavascriptExecutor js = (JavascriptExecutor) oDriver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", Element);
	}

	public static String takeScreenshot(WebDriver driver, String FolderPath) {
		try {
			String filePath = FolderPath + utils.getDateTimeStamp() + ".jpg";
			
			if (new File(filePath).exists()) {
				throw new Exception("Screenshot filename already exists");
			}			
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filePath));
			Log.info("Screenshot captured successfully");
			return filePath;
		} catch (Exception e) {
			Log.error("Could not take screenshot; here is some more detail: " + e);
			return "ERROR: Error occured while saving the screnshot";
		}

	}
	
	
	public static void selectByVisibleText(WebDriver driver, WebElement Element, String visibleText) {
		   try {	    
		    
		    Select select = new Select(Element);
		    select.selectByVisibleText(visibleText);

		   } catch (Exception e) {
			Log.error("Could not select given element; here is some more detail: "+ e);
		    System.out.println("Could not select given element; here is some more detail: ");    
		   }
		  }
	
	public static void waitTillElementVisible(WebDriver driver, WebElement Element, long timeOut) {

		   try {
		    WebDriverWait oWait = new WebDriverWait(driver, timeOut);
		    oWait.until(ExpectedConditions.visibilityOf(Element));
		   } catch (Exception e) {
		    System.out.println("Error while waiting for element to be visible; here is some more detail: ");
		    e.printStackTrace();
		   }
		  }
	 
	public static void waitTillAlertVisible(WebDriver driver, long timeOut) {
		   try {			 
		    WebDriverWait oWait = new WebDriverWait(driver, timeOut);
		    oWait.until(ExpectedConditions.alertIsPresent());
		   } catch (Exception e) {
		    System.out.println("commonDriver()->waitTillElementClickable()->Error while waiting for element to be clickable; here is some more detail: ");
		    e.printStackTrace();
		   }
		  }

	public static void switchToAlert(WebDriver driver) {
		   try {
			   driver.switchTo().alert();
		   } catch (Exception e) {
		    System.out.println("Could not switch to specified alert; here is some more detail: ");
		    e.printStackTrace();
		   }
		  }
	 
	 public static String getAlertText(WebDriver driver) {
		   try {
		    return driver.switchTo().alert().getText();
		   } catch (Exception e) {
		    System.out.println("Could not get alert text; here is some more detail: ");
		    e.printStackTrace();
		    return "";
		   }
		  }
	 
	 public static void acceptAlert(WebDriver driver) {
		   try {
			driver.switchTo().alert().accept();
		   } catch (Exception e) {
		    System.out.println("Could not accept the specified alert; here is some more detail: ");
		    e.printStackTrace();
		   }
		  }
	 
	 
	 
	 
}// END CLASS