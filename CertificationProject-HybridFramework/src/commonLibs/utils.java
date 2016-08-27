package commonLibs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils {

	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";	
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method waitForSeconds
	//This method sleeps the thread for the specific number of seconds
	public static void waitForSeconds(long Seconds) {
		try {
			Thread.sleep(Seconds * 1000L);
		} catch (Exception e) {
			// Intentionally empty
		}
	}
	
	 
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method getDateTimeStamp
	//This method returns date and timestamp 
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

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method getProperty
	//This method returns the asked property
	public static String getProperty(String Property){
		try {
			InputStream ofileReader;
			Properties oProperty;
			ofileReader = new FileInputStream(driverPropertyFile);
			oProperty = new Properties();
			oProperty.load(ofileReader);	
			return	oProperty.getProperty(Property).trim();						
		} catch (Exception e) {
			Extent.logFatel("utils.getProperty", "Exception occured: " +e);
			return "EXCEPTION";
		}
				
	}
	
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method highlightElement
	//This method highlights the given web control
	public static void highlightElement(WebDriver oDriver, WebElement Element) {
		// WebElement element = oDriver.findElement(oBy);
		JavascriptExecutor js = (JavascriptExecutor) oDriver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", Element);
	}

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method highlightElement
	//This method highlights the given web control
	public static String takeScreenshot(WebDriver driver, String FolderPath) {
		try {
			String filePath = FolderPath + utils.getDateTimeStamp() + ".jpg";
			if (new File(filePath).exists()) {
				throw new Exception("Screenshot filename already exists");
			}
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filePath));
			Extent.logInfo("takeScreenshot","Screenshot captured successfully");
			return filePath;
		} catch (Exception e) {
			Extent.logError("takeScreenshot","Could not take screenshot; here is some more detail: " + e);
			return "ERROR: Error occured while saving the screnshot";
		}

	}

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method selectByVisibleText
	//This method selects from a drop down using visible text
	public static void selectByVisibleText(WebDriver driver, WebElement Element, String visibleText) {
		try {

			Select select = new Select(Element);
			select.selectByVisibleText(visibleText);

		} catch (Exception e) {
			Extent.logError("selectByVisibleText","Could not select given element; here is some more detail: " + e);
			System.out.println("Could not select given element; here is some more detail: ");
		}
	}

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method waitTillElementVisible
	//This method waits till the given element is visible on the UI
	public static void waitTillElementVisible(WebDriver driver, WebElement Element, long timeOut) {

		try {
			WebDriverWait oWait = new WebDriverWait(driver, timeOut);
			oWait.until(ExpectedConditions.visibilityOf(Element));
		} catch (Exception e) {
			System.out.println("Error while waiting for element to be visible; here is some more detail: ");
			e.printStackTrace();
		}
	}

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method waitTillAlertVisible
	//This method waits till the alert is visible
	public static void waitTillAlertVisible(WebDriver driver, long timeOut) {
		try {
			WebDriverWait oWait = new WebDriverWait(driver, timeOut);
			oWait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			System.out.println(
					"commonDriver()->waitTillElementClickable()->Error while waiting for element to be clickable; here is some more detail: ");
			e.printStackTrace();
		}
	}

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method switchToAlert
	//This method switches the control to the open alert
	public static void switchToAlert(WebDriver driver) {
		try {
			driver.switchTo().alert();
		} catch (Exception e) {
			System.out.println("Could not switch to specified alert; here is some more detail: ");
			e.printStackTrace();
		}
	}

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method getAlertText
	//This method returns the text of an open alert
	public static String getAlertText(WebDriver driver) {
		try {
			return driver.switchTo().alert().getText();
		} catch (Exception e) {
			System.out.println("Could not get alert text; here is some more detail: ");
			e.printStackTrace();
			return "";
		}
	}

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	//method acceptAlert
	//This method accepts an open alert
	public static void acceptAlert(WebDriver driver) {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("Could not accept the specified alert; here is some more detail: ");
			e.printStackTrace();
		}
	}

	
		
	
}// END CLASS
