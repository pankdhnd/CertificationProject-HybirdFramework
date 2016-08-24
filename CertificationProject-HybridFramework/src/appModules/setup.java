package appModules;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import commonLibs.Log;
import commonLibs.dataProvider;
import commonLibs.utils;


public class setup {

	// Variable declaration, creating objects of controls to be operated
	@FindBy (name="userName")
	private static WebElement txtUsername;
	
	@FindBy (name="password")
	private static WebElement txtPassword;
	
	@FindBy (name = "login")
	private static WebElement loginButton;
	
	@FindBy (linkText="SIGN-OFF")
	private static WebElement signOffLink;

	
	dataProvider getTestDataFor = new dataProvider();
	WebDriver wDriver;

//	 setting up constructor
	public setup(WebDriver driver) {
		this.wDriver = driver;	
		DOMConfigurator.configure("log4j.xml");
		PageFactory.initElements(wDriver, this);
	}

//	 method verifyLoginElementsExistance
//	 This method verifies whether the contros like username textbox, password
//	 textbox and login button exist on the page
	public String verifyLoginElementsExistance() {
		try {
			if (!txtUsername.isDisplayed()) {
				Log.error("ERROR: Could not find Username textbox");
				return "ERROR: Could not find Username textbox";
			}

			if (!txtPassword.isDisplayed()) {
				Log.error("ERROR: Could not find Pasword textbox");
				return "ERROR: Could not find Password textbox";
			}

			if (!loginButton.isDisplayed()) {
				Log.error("ERROR: Could not find Login button");
				return "ERROR: Could not find Login button";
			}

			utils.highlightElement(wDriver, txtUsername);
//			oDriver.highlightElement(txtUsername);
//			oDriver.highlightElement(txtPassword);
//			oDriver.highlightElement(loginButton);
			return "Pre-login validations complete";

		} catch (Exception e) {
			Log.error(
					"setup()->verifyLoginElementsExistance()-> ERROR: Could not verify the existance of login elements/controls, here are the details: "
							+ e);
			return "ERROR: Could not verify the existance of login elements/controls";
		}
	}
	
//	 method Login
//	 This method logs user into the system
	public String login() {
		try {							
			try {
				loginButton.click();			    
				 WebDriverWait oWait = new WebDriverWait(wDriver, 30);				 
				 oWait.until(ExpectedConditions.titleIs("Find a Flight: Mercury Tours:"));					
				Log.info("Login successful");
				return "Login Successful";
			} catch (Exception e) {
				Log.error("ERROR: Title does not match after login; login failed; details: " + e);
				return "ERROR: Title does not match after login; login failed";
			}
		} catch (Exception e) {
			Log.error("ERROR: Login failed due to some exception. Below are the details: " + e);
			return "ERROR: Login failed due to some exception";
		}
	}
//
//	 method inputLoginDetails
//	 this method inputs the data required for login, like username, password.
	public String inputLoginDetails() {
		try {
			String Data[] = getTestDataFor.inputLoginDetails();
			String userName = Data[0];
			String passWord = Data[1];
			txtUsername.sendKeys(userName);
			txtPassword.sendKeys(passWord);
			//oDriver.setText(txtUsername, userName);
			//oDriver.setText(txtPassword, passWord);
			Log.info("Login details populated successfully");
			return "Successful";
		} catch (Exception e) {
			Log.error("ERROR: inputLoginDetails failed due to some exception. Below are the details: " + e);
			return "ERROR: Entered values verification failed due some exception";
		}
	}
//
//	method verifyEnteredValues
//	This method verified already entered value. It compares the value typed
//	in the textbox, with the values provided as parameters from the excel//	// sheet
	public String verifyEnteredValues() {
		try {
			String Data[] = getTestDataFor.inputLoginDetails();
			String userName = Data[0];
			String passWord = Data[1];
			Log.info("Verifying the already populated value with the values supplied from excel Sheet");			
			
			if (!txtUsername.getAttribute("value").equals(userName)){			
				Log.error("Entered username doesn't match with supplied username");
				return "ERROR: Entered username doesn't match with supplied username";
			} else if (!txtPassword.getAttribute("value").equals(passWord)) {
				Log.error("Entered password doesn't match with supplied username");
				return "ERROR: Entered password doesn't match with supplied username";
			}
			return "Entered value verified successfully";
		} catch (Exception e) {
			Log.error("ERROR: verifyEnteredValues failed due to some exception. Below are the details: " + e);
			return "ERROR: Entered values verification failed due some exception";
		}
	}
//
//	// method Logout
//	// this method logs out user form the system.
	public String logout() {		
		try {
			signOffLink.click();
			WebDriverWait Wait = new WebDriverWait(wDriver, 60l);
			Wait.until(ExpectedConditions.titleIs("Sign-on: Mercury Tours"));			
			Log.info("Logged out successfully");
			return "logged out successfully";
		} catch (Exception e) {
			Log.error("setup()->Logout()-> Logout failed; here are some more details: " + e);
			return "ERROR: Logout failed";
		}
	}

//method openLoginURL
// This metod navigates to login page URL
	public String openLoginURL(){
		try {
			Log.info("Opening URL for login page");;
			String Data[] = getTestDataFor.openBrowser();			
			String sURL = Data[1];
			wDriver.navigate().to(sURL);
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Log.info("Login URL opened successfully");
			return "Login URL opened successfully";
		} catch (Exception e) {
			Log.error("Error occured while opening the login URL, here are the details: "+ e );			
			return "ERROR: Error occurred while entering registration details";
		}		
	}
	
	
}
