package appModules;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import utility.Log;
import commonLibs.dataProvider;
import commonLibs.commonDriver;

public class setup {

	// Variable declaration, creating objects of controls to be operated
	private static By txtUsername = By.name("userName");
	private static By txtPassword = By.name("password");
	private static By loginButton = By.name("login");
	private static By signOffLink = By.linkText("SIGN-OFF");

	static commonDriver oDriver;
	dataProvider getTestDataFor = new dataProvider();

	// setting up constructor
	public setup() {
		DOMConfigurator.configure("log4j.xml");
		oDriver = new commonDriver();
	}

	// method verifyLoginElementsExistance
	// This method verifies whether the contros like username textbox, password
	// textbox and login button exist on the page
	public String verifyLoginElementsExistance() {
		try {
			if (!oDriver.isVisible(txtUsername)) {
				Log.error("ERROR: Could not find Username textbox");
				return "ERROR: Could not find Username textbox";
			}

			if (!oDriver.isVisible(txtPassword)) {
				Log.error("ERROR: Could not find Pasword textbox");
				return "ERROR: Could not find Password textbox";
			}

			if (!oDriver.isVisible(loginButton)) {
				Log.error("ERROR: Could not find Login button");
				return "ERROR: Could not find Login button";
			}

			oDriver.highlightElement(txtUsername);
			oDriver.highlightElement(txtPassword);
			oDriver.highlightElement(loginButton);
			return "Pre-login validations complete";

		} catch (Exception e) {
			Log.error(
					"setup()->verifyLoginElementsExistance()-> ERROR: Could not verify the existance of login elements/controls, here are the details: "
							+ e);
			return "ERROR: Could not verify the existance of login elements/controls";
		}
	}

	// method openBrowser
	// This method gets the browser type and URL form dataProvider class, opens
	// the specified browser and navigates to the specific URL
	public String openBrowser() {
		try {
			String Data[] = getTestDataFor.openBrowser();
			String browserType = Data[0];
			String URL = Data[1];
			String expectedTitle = Data[2];
			Log.info("Opening browser " + browserType);
			oDriver.openBrowser(browserType, URL);
			oDriver.waitTillElementClickable(txtUsername, 60l);
			if (!oDriver.getTitle().equals(expectedTitle)) {
				Log.error("ERROR: Login page title mismatch; login failed");
				return "ERROR: Login page title mismatch; login failed";
			} else {
				Log.info("Browser opened successfully");
				return "Browser opened successfully";
			}
		} catch (Exception e) {
			Log.error("ERROR: Error while opening browser; here are the details: " + e);
			return "ERROR: Error while opening browser";
		}
	}

	// method Login
	// This method logs user into the system
	public String login() {
		try {
			oDriver.click(loginButton);
			try {
				oDriver.waitTillElementVisible(signOffLink, 60l);
				Log.info("Login successful");
				return "Login Successful";
			} catch (Exception e) {
				Log.error("ERROR: Could not find Sign off link; login failed; details: " + e);
				return "ERROR: Could not find Sign off link; login failed";
			}
		} catch (Exception e) {
			Log.error("ERROR: Login failed due to some exception. Below are the details: " + e);
			return "ERROR: Login failed due to some exception";
		}
	}

	// method inputLoginDetails
	// this method inputs the data required for login, like username, password.
	public String inputLoginDetails() {
		try {
			String Data[] = getTestDataFor.inputLoginDetails();
			String userName = Data[0];
			String passWord = Data[1];
			oDriver.setText(txtUsername, userName);
			oDriver.setText(txtPassword, passWord);
			Log.info("Login details populated successfully");
			return "Successful";
		} catch (Exception e) {
			Log.error("ERROR: inputLoginDetails failed due to some exception. Below are the details: " + e);
			return "ERROR: Entered values verification failed due some exception";
		}
	}

	// method verifyEnteredValues
	// This method verified already entered value. It compares the value typed
	// in the textbox, with the values provided as paramters from the excel
	// sheet
	public String verifyEnteredValues() {
		try {
			String Data[] = getTestDataFor.inputLoginDetails();
			String userName = Data[0];
			String passWord = Data[1];
			Log.info("Verifying the already populated value with the values supplied from excel Sheet");
			if (!oDriver.getAttribute(txtUsername, "value").equals(userName)) {
				Log.error("Entered username doesn't match with supplied username");
				return "ERROR: Entered username doesn't match with supplied username";
			} else if (!oDriver.getAttribute(txtPassword, "value").equals(passWord)) {
				Log.error("Entered password doesn't match with supplied username");
				return "ERROR: Entered password doesn't match with supplied username";
			}
			return "Entered value verified successfully";
		} catch (Exception e) {
			Log.error("ERROR: verifyEnteredValues failed due to some exception. Below are the details: " + e);
			return "ERROR: Entered values verification failed due some exception";
		}
	}

	// method Logout
	// this method logs out user form the system.
	public String logout() {
		try {
			oDriver.click(signOffLink);
			oDriver.waitTillElementVisible(txtUsername, 30l);
			Log.info("Logged out successfully");
			return "logged out successfully";
		} catch (Exception e) {
			Log.error("setup()->Logout()-> Logout failed; here are some more details: " + e);
			return "ERROR: Logout failed";
		}

	}

	// method closeBrowser
	// This method closes currently open browser, and kills the driver.
	public String closeBrowser() {
		try {
			oDriver.closeBrowser();
			Log.info("Browser closed successfully");
			return "Browser closed successfully";
		} catch (Exception e) {
			Log.error("setup()->closeBrowser()-> Error occured while closing the browser; here are some more details: "
					+ e);
			return "ERROR: Could not close the browser";
		}
	}
}
