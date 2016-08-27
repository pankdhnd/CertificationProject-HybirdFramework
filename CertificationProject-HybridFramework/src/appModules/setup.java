package appModules;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import commonLibs.Extent;

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

	
//	dataProvider getTestDataFor = new dataProvider();
	WebDriver wDriver;

//	 setting up constructor
	public setup(WebDriver driver) {
		this.wDriver = driver;		
		PageFactory.initElements(wDriver, this);
	}

//	 method verifyLoginElementsExistance
//	 This method verifies whether the controls like username textbox, password
//	 textbox and login button exist on the page
	public String verifyLoginElementsExistance() {
		try {
			if (!txtUsername.isDisplayed()) {
				Extent.logError("verifyLoginElementsExistance", "Could not find Username textbox");				
				return "ERROR: Could not find Username textbox";
			}

			if (!txtPassword.isDisplayed()) {
				Extent.logError("verifyLoginElementsExistance", "ERROR: Could not find Pasword textbox");
				return "ERROR: Could not find Password textbox";
			}

			if (!loginButton.isDisplayed()) {
				Extent.logError("verifyLoginElementsExistance", "ERROR: Could not find Login button");
				return "ERROR: Could not find Login button";
			}
			Extent.logInfo("verifyLoginElementsExistance","Pre-login validations complete"); 
			utils.highlightElement(wDriver, txtUsername);
			return "Pre-login validations complete";

		} catch (Exception e) {
			Extent.logError("verifyLoginElementsExistance", "Could not verify the existance of login elements/controls, here are the details: "
							+ e);
			return "ERROR: Could not verify the existance of login elements/controls";
		}
	}
	
//	 method Login
//	 This method logs user into the system
	public String login() {
		try {							
			try {
				Extent.logInfo("login", "Clicking loggin button");
				loginButton.click();			    
				 WebDriverWait oWait = new WebDriverWait(wDriver, 30);				 
				 oWait.until(ExpectedConditions.titleIs("Find a Flight: Mercury Tours:"));
				 Extent.logInfo("login", "Login successful");			
				return "Login Successful";
			} catch (Exception e) {
				Extent.logError("login", "Title does not match after login; login failed; details: " + e);				
				return "ERROR: Title does not match after login; login failed";
			}
		} catch (Exception e) {
			Extent.logError("login", "Login failed due to some exception. Below are the details: " + e);
			return "ERROR: Login failed due to some exception";
		}
	}
//
//	 method inputLoginDetails
//	 this method inputs the data required for login, like username, password.
	public String inputLoginDetails() {
		try {
			Extent.logInfo("inputLoginDetails", "Fetching input data from excel...");
			String Data[] = dataProvider.inputLoginDetails();
			String userName = Data[0];
			String passWord = Data[1];
			txtUsername.sendKeys(userName);
			txtPassword.sendKeys(passWord);
			Extent.logInfo("inputLoginDetails", "Login details populated successfully");			
			return "Successful";
		} catch (Exception e) {
			Extent.logError("inputLoginDetails", "inputLoginDetails failed due to some exception. Below are the details: " + e);			
			return "ERROR: Entered values verification failed due some exception";
		}
	}
//
//	method verifyEnteredValues
//	This method verified already entered value. It compares the value typed
//	in the textbox, with the values provided as parameters from the excel//	// sheet
	public String verifyEnteredValues() {
		try {
			Extent.logInfo("verifyEnteredValues","Fetching data from excel...");
			String Data[] = dataProvider.inputLoginDetails();
			String userName = Data[0];
			String passWord = Data[1];
			Extent.logInfo("verifyEnteredValues","Verifying the already populated value with the values supplied from excel Sheet");			
			
			if (!txtUsername.getAttribute("value").equals(userName)){			
				Extent.logError("verifyEnteredValues","Entered username doesn't match with supplied username");
				return "ERROR: Entered username doesn't match with supplied username";
			} else if (!txtPassword.getAttribute("value").equals(passWord)) {
				Extent.logError("verifyEnteredValues","Entered password doesn't match with supplied username");
				return "ERROR: Entered password doesn't match with supplied username";
			}
			return "Entered value verified successfully";
		} catch (Exception e) {
			Extent.logError("verifyEnteredValues","verifyEnteredValues failed due to some exception. Below are the details: " + e);
			return "ERROR: Entered values verification failed due some exception";
		}
	}
//
//	// method Logout
//	// this method logs out user form the system.
	public String logout() {		
		try {
			Extent.logInfo("logout","Logging out...");
			signOffLink.click();
			WebDriverWait Wait = new WebDriverWait(wDriver, 60l);
			Wait.until(ExpectedConditions.titleIs("Sign-on: Mercury Tours"));			
			Extent.logInfo("logout","Logged out successfully");
			return "logged out successfully";
		} catch (Exception e) {
			Extent.logError("Logout","Logout failed; here are some more details: " + e);			
			return "ERROR: Logout failed";
		}
	}//END of method logout

//method openLoginURL
// This metod navigates to login page URL
	public String openLoginURL(){
		try {
			Extent.logInfo("openLoginURL","Opening URL for login page");			
			String Data[] = dataProvider.openBrowser();			
			String sURL = Data[1];
			wDriver.navigate().to(sURL);
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Extent.logInfo("openLoginURL","Login URL opened successfully");		
			return "Login URL opened successfully";
		} catch (Exception e) {
			Extent.logError("openLoginURL", "Error occured while opening the login URL, here are the details: "+ e);						
			return "ERROR: Error occurred while entering registration details";
		}		
	}//END of method openLoginURL
	
	
}//END OF CLASS SETUP
