package appModules;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import utility.Log;
import commonLibs.commonDriver;


public class setup {	

	private static String txtUsername = "user_login";
	private static String txtPassword ="user_pass";
	private static String loginButton = "wp-submit";
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
		if(!oDriver.isVisible(By.id(txtUsername)))
		{
			Log.error("ERROR: Could not find Username textbox");
			return "ERROR: Could not find Username textbox";
		}
		
		if(!oDriver.isVisible(By.id(txtPassword)))
		{
			Log.error("ERROR: Could not find Pasword textbox");
			return "ERROR: Could not find Password textbox";
		}
		
		
		if(!oDriver.isVisible(By.id(loginButton)))
		{
			Log.error("ERROR: Could not find Login button");
			return "ERROR: Could not find Login button";
		}
		
	} catch (Exception e) {
		Log.error("setup()->verifyLoginElementsExistance()-> Error occured, here are the details: ");
		Log.error(e.getStackTrace().toString());
	}	
		
		return "Pre-login validations complete";		
	}
	
	public String openBrowser(String browserType, String URL){
		try {
			oDriver.openBrowser(browserType, URL);
			oDriver.waitTillElementClickable(By.id(txtUsername),60l);   

			if(!oDriver.getTitle().equals("WordPress Demo Install � Log In")){
				Log.error("ERROR: Login page title mismatch; login failed");
				return "ERROR: Login page title mismatch; login failed";
			}
		} catch (Exception e) {
			Log.error("ERROR: Error while opening browser; here are the details: ");
			Log.error(e.getStackTrace().toString());
			e.printStackTrace();
			return "ERROR: Error while opening browser";
		}
		return "ERROR";
	}
	
	
	
	
	

	public String login(String userName, String passWord)
	{
		try {			
				oDriver.setText(By.id(txtUsername), userName);
				oDriver.setText(By.id(txtPassword),passWord);
				oDriver.click(By.id(loginButton));   			
			
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
	
	public String logout()
	{		
		try {			
		
			oDriver.waitTillElementVisible(By.xpath(myAccountLink), 60l);			
			oDriver.moveToElement(By.xpath(myAccountLink));
			oDriver.click(By.xpath(linkLogout));		
			oDriver.implicitlyWait(30);
			
			if (oDriver.isVisible(By.id(txtUsername))){
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
