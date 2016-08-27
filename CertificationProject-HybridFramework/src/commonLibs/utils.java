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

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class utils {

	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// method waitForSeconds
	// This method sleeps the thread for the specific number of seconds
	public static void waitForSeconds(long Seconds) {
		try {
			Thread.sleep(Seconds * 1000L);
		} catch (Exception e) {
			// Intentionally empty
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// method getDateTimeStamp
	// This method returns date and timestamp
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
	// method getProperty
	// This method returns the asked property
	public static String getProperty(String Property) {
		try {
			InputStream ofileReader;
			Properties oProperty;
			ofileReader = new FileInputStream(driverPropertyFile);
			oProperty = new Properties();
			oProperty.load(ofileReader);
			return oProperty.getProperty(Property).trim();
		} catch (Exception e) {
			Extent.logFatel("utils.getProperty", "Exception occured: " + e);
			return "EXCEPTION";
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// method highlightElement
	// This method highlights the given web control
	public static void highlightElement(WebDriver oDriver, WebElement Element) {
		// WebElement element = oDriver.findElement(oBy);
		JavascriptExecutor js = (JavascriptExecutor) oDriver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", Element);
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// method highlightElement
	// This method highlights the given web control
	public static String takeScreenshot(WebDriver driver, String FolderPath) {
		try {
			String filePath = FolderPath + utils.getDateTimeStamp() + ".jpg";
			if (new File(filePath).exists()) {
				throw new Exception("Screenshot filename already exists");
			}
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filePath));
			Extent.logInfo("takeScreenshot", "Screenshot captured successfully");
			return filePath;
		} catch (Exception e) {
			Extent.logError("takeScreenshot", "Could not take screenshot; here is some more detail: " + e);
			return "ERROR: Error occured while saving the screnshot";
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// method selectByVisibleText
	// This method selects from a drop down using visible text
	public static void selectByVisibleText(WebDriver driver, WebElement Element, String visibleText) {
		try {

			Select select = new Select(Element);
			select.selectByVisibleText(visibleText);

		} catch (Exception e) {
			Extent.logError("selectByVisibleText", "Could not select given element; here is some more detail: " + e);
			System.out.println("Could not select given element; here is some more detail: ");
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// method waitTillElementVisible
	// This method waits till the given element is visible on the UI
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
	// method waitTillAlertVisible
	// This method waits till the alert is visible
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
	// method switchToAlert
	// This method switches the control to the open alert
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
	// method getAlertText
	// This method returns the text of an open alert
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
	// method acceptAlert
	// This method accepts an open alert
	public static void acceptAlert(WebDriver driver) {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("Could not accept the specified alert; here is some more detail: ");
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	// method acceptAlert
	// This method sends the email to the addresses which are passed to it
	public static void sendEmail(final String adminEmail, final String adminPass, String toAddress, String fileName) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(adminEmail, adminPass);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(adminEmail));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

			// Set Subject: header field
			message.setSubject("Testing Subject");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText("This is message body");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileName);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}// END CLASS
