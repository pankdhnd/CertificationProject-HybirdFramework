package appModules;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commonLibs.commonDriver;
import commonLibs.utils;
import utility.Log;
import commonLibs.dataProvider;


public class mercuryRegistration {

	//Veriable declaration section
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
	dataProvider getTestDataFor = new dataProvider();
	
	
	//method mercuryRegistration
	//This is the constructor for this class.
	public mercuryRegistration()
	{
		  DOMConfigurator.configure("log4j.xml");
		  oDriver = new commonDriver();
		  oDriverProperties = utils.getProperties(driverPropertyFile);	
		  screenshotPath = oDriverProperties.getProperty("screenshotFolder").trim();
	}
	
	//method registerNewUser
	//This method gets all the needed data from the class dataProvider, and creates a new user using that data.
	public String registerNewUser(){
		try {
			//Get the data from dataProvider method and put it in a string array
			String Data[] = getTestDataFor.registerNewUser();
			Log.info("Opening regisration page");
			oDriver.click(linkRegister);						
			
			//Input registration details, which are provided by dataProvider class form the input excel file
			Log.info("Inputting registration details");
			oDriver.setText(txtFirstname, Data[0]);
			oDriver.setText(txtLastname, Data[1]);
			oDriver.setText(txtPhone, Data[2]);
			oDriver.setText(txtEmail, Data[3]);
			oDriver.setText(txtAddress1, Data[4]);
			oDriver.setText(txtAddress2, Data[5]);
			oDriver.setText(txtCity, Data[6]);
			oDriver.setText(txtState, Data[7]);
			oDriver.setText(txtPostalcode, Data[8]);
			oDriver.selectByVisibleText(selectCountry, Data[9]);
			oDriver.setText(txtUsername, Data[10]);
			oDriver.setText(txtPassword, Data[11]);
			oDriver.setText(txtConfirmPassword, Data[12]);			
			Log.info("Clicking Submit button");
			oDriver.click(buttonSubmit);
			
			
			//Verify user display name from UI with the value provided from excel sheet
			Log.info("Verifying user display name");
			if(oDriver.isVisible(displayUsername) && oDriver.getText(displayUsername).equals("Dear "+ Data[0] + " " + Data[1] + ",")){
				Log.info("User Display name verification successful");
			}
			else{
				oDriver.takeScreenshot(screenshotPath + utils.getDateTimeStamp()+".jpg");
				Log.error("ERROR: Could not find post registration screen; screenshot captured");				
				return "ERROR: Could not find post registration screen";
			}
			
			//Verify user login id  from UI with the value provided from excel sheet
			Log.info("Verifying login username");			
			if(oDriver.isVisible(loginUsername) && oDriver.getText(loginUsername).equals("Note: Your user name is " + Data[10] + ".")){
				Log.info("Login username verification successful");
			}
			else{
				oDriver.takeScreenshot(screenshotPath + utils.getDateTimeStamp()+".jpg");
				Log.error("ERROR: Login username displayed is different than the username we provided; screenshot taken");				
				return "ERROR: Login username displayed is different than the username we provided";
			}
			
			//if all is good, return successful response
			return "Registration successful";
		} catch (Exception e) {
			Log.error("mercuryRegistration()->registerNewUser()-> Error occured, here are the details: "+ e );
			//return error response in case of exception
			return "ERROR: Error occurred while entering registration details";
		}		
	}
	
	
}//END of class

