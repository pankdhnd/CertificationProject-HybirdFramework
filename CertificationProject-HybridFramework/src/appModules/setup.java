package appModules;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import utility.Log;
import commonLibs.commonDriver;


public class setup {	

	private static By txtUsername = By.name("userName");
	private static By txtPassword = By.name("password");
	private static By loginButton = By.name("login");
	private static String dashboardLink = "//a[contains(text(),'Dashboard')]";
	private static String myAccountLink = "//a[contains(text(),'Howdy, admin')]";
	private static String linkLogout = "//a[contains(text(),'Log Out')]";
	
		
	
	static commonDriver oDriver;	
	public setup(){		  
		  DOMConfigurator.configure("log4j.xml");
		  oDriver = new commonDriver();		
	}
	

	public  String verifyLoginElementsExistance(){		
	try {
		if(!oDriver.isVisible(txtUsername))
		{
			Log.error("ERROR: Could not find Username textbox");
			return "ERROR: Could not find Username textbox";
		}
		
		if(!oDriver.isVisible(txtPassword))
		{
			Log.error("ERROR: Could not find Pasword textbox");
			return "ERROR: Could not find Password textbox";
		}		
		
		if(!oDriver.isVisible(loginButton))
		{
			Log.error("ERROR: Could not find Login button");
			return "ERROR: Could not find Login button";
		}
		
		
		oDriver.highlightElement(txtUsername);
		oDriver.highlightElement(txtPassword);
		oDriver.highlightElement(loginButton);
		
		
	} catch (Exception e) {
		Log.error("setup()->verifyLoginElementsExistance()-> Error occured, here are the details: ");
		Log.error(e.getStackTrace().toString());
	}	
		
		return "Pre-login validations complete";		
	}
	
	public String openBrowser(String browserType, String URL, String expectedTitle){
		try {
			oDriver.openBrowser(browserType, URL);
			oDriver.waitTillElementClickable(txtUsername,60l);   
			if(!oDriver.getTitle().equals(expectedTitle)){
				Log.error("ERROR: Login page title mismatch; login failed");
				return "ERROR: Login page title mismatch; login failed";
			}
			else
			{
				return "Success: Browser opened successfully";
			}
		} catch (Exception e) {
			Log.error("ERROR: Error while opening browser; here are the details: ");
			Log.error(e.getStackTrace().toString());
			e.printStackTrace();
			return "ERROR: Error while opening browser";
		}		
	}
		
	public String login()
	{
		try {		
				oDriver.click(loginButton);  
				try {
					oDriver.waitTillElementVisible(By.xpath(dashboardLink),60l);
					Log.info("Login successful");
					return "Login Successful";
				} catch (Exception e) {
					Log.error("ERROR: Could not find Dashboard link; login failed");
					return "ERROR: Could not find Dashboard link; login failed";					
				}
		} catch (Exception e){
			Log.error("ERROR: Login failed due to some exception. Below are the details: ");
			Log.error(e.getStackTrace().toString());
			return "ERROR: Login failed due to some exception";
		}
	}
	
	public String inputLoginDetails(String userName, String passWord){
		try {
			oDriver.setText(txtUsername, userName);			
			oDriver.setText(txtPassword,passWord);
			return "Successful";
		} catch (Exception e) {
			Log.error("ERROR: inputLoginDetails failed due to some exception. Below are the details: ");
			Log.error(e.getStackTrace().toString());
			return "ERROR: Entered values verification failed due some exception";
		}
	}
	
		
	public String verifyEnteredValues(String userName, String passWord){		
		try {
			if (!oDriver.getAttribute(txtUsername, "value").equals(userName)){
			Log.error("Entered username doesn't match with supplied username");
			return "ERROR: Entered username doesn't match with supplied username";
			}
			else if (!oDriver.getAttribute(txtPassword,"value").equals(passWord)){
				Log.error("Entered password doesn't match with supplied username");
				return "ERROR: Entered password doesn't match with supplied username";
			}
			return "Successful";
		} catch (Exception e) {
			Log.error("ERROR: verifyEnteredValues failed due to some exception. Below are the details: ");
			Log.error(e.getStackTrace().toString());
			return "ERROR: Entered values verification failed due some exception";
		}
	}
	
	public void takeScreenshot(){
		
	}
	
	
	public String logout()
	{		
		try {			
		
			oDriver.waitTillElementVisible(By.xpath(myAccountLink), 60l);			
			oDriver.moveToElement(By.xpath(myAccountLink));
			oDriver.click(By.xpath(linkLogout));		
			oDriver.implicitlyWait(30);
			
			if (oDriver.isVisible(txtUsername)){
			return "Logout Successful";
			}						
		} catch (Exception e) {
			Log.error("ERROR: Logout failed; here are some more details: ");
			Log.error(e.getStackTrace().toString());			
			return "ERROR: Logout failed";
		}		
		return "ERROR";
	}
					

	
}
