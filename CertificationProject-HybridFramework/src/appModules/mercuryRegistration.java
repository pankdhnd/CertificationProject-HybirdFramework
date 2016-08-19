package appModules;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commonLibs.commonDriver;
import commonLibs.utils;
import utility.Log;



public class mercuryRegistration {

	private static By linkRegister = By.linkText("REGISTER");
	private static By txtFirstname = By.name("firstName");
	private static By txtLastname = By.name("lastName");
	private static By txtPhone = By.name("phone");
	private static By txtEmail = By.name("userName");
	private static By txtAddress1 = By.name("address1");
	private static By txtAddress2 = By.name("address2");
	private static By txtCity = By.name("city");
	private static By txtState = By.name("state");
	private static By txtPostalcode = By.name("postalCode");
	private static By selectCountry = By.name("country");	
	private static By txtUsername = By.name("email");
	private static By txtPassword = By.name("password");
	private static By txtConfirmPassword = By.name("confirmPassword");
	private static By buttonSubmit = By.name("register");
	private static By displayUsername = By.xpath("//b[contains(text(),'Dear')]"); 
	private static By loginUsername = By.xpath("//b[contains(text(),'Note:')]");
	static commonDriver oDriver;
	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";
	private static Properties oDriverProperties;
	private static String screenshotPath;
	
	
	public mercuryRegistration()
	{
		  DOMConfigurator.configure("log4j.xml");
		  oDriver = new commonDriver();
		  oDriverProperties = utils.getProperties(driverPropertyFile);	
		  screenshotPath = oDriverProperties.getProperty("screenshotFolder").trim();
	}
	
	public String registerNewUser(String firstName, String lastName, String Phone, String Email, String Address1, String Address2, String City, String State, String postalCode, String Country, String userName, String passWord, String confirmPassword){
		try {
			Log.info("Opening regisration page");
			oDriver.click(linkRegister);
			
			Log.info("Inputting registration details");
			oDriver.setText(txtFirstname, firstName);
			oDriver.setText(txtLastname, lastName);
			oDriver.setText(txtPhone, Phone);
			oDriver.setText(txtEmail, Email);
			oDriver.setText(txtAddress1, Address1);
			oDriver.setText(txtAddress2, Address2);
			oDriver.setText(txtCity, City);
			oDriver.setText(txtState, State);
			oDriver.setText(txtPostalcode, postalCode);
			oDriver.selectByVisibleText(selectCountry, Country);
			oDriver.setText(txtUsername, userName);
			oDriver.setText(txtPassword, passWord);
			oDriver.setText(txtConfirmPassword, confirmPassword);
			
			Log.info("Clicking Submit button");
			oDriver.click(buttonSubmit);
			
			Log.info("Verifying user display name");
			if(oDriver.isVisible(displayUsername) || oDriver.getText(displayUsername).equals("Dear "+ firstName + " " + lastName)){
				Log.info("User Display name verification successful");
			}
			else{
				oDriver.takeScreenshot(screenshotPath + utils.getDateTimeStamp()+".jpg");
				Log.error("ERROR: Could not find post registration screen; screenshot captured");				
				return "ERROR: Could not find post registration screen";
			}
			
			Log.info("Verifying login username");
			if(oDriver.isVisible(loginUsername) && loginUsername.equals("Note: Your user name is " + txtUsername)){
				Log.info("Login username verification complete");
			}
			else{
				oDriver.takeScreenshot(screenshotPath + utils.getDateTimeStamp()+".jpg");
				Log.error("ERROR: Login username displayed is different than the username we provided; screenshot taken");				
				return "ERROR: Login username displayed is different than the username we provided";
			}
			
			return "Registration successful";
		} catch (Exception e) {
			Log.error("mercuryRegistration()->registerNewUser()-> Error occured, here are the details: ");
			Log.error(e.getStackTrace().toString());
			return "ERROR: Error occurred while entering registration details";
		}		
	}
	
	
}

