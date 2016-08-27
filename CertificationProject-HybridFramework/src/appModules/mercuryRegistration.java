package appModules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import commonLibs.utils;
import commonLibs.Extent;
import commonLibs.dataProvider;


public class mercuryRegistration {

	//Variable declaration section
	@FindBy (linkText = "REGISTER")
	private static WebElement linkRegister;
	
	@FindBy (name = "firstName")
	private static WebElement txtFirstname;
	
	@FindBy (name = "lastName")	 
	private static WebElement txtLastname;
	
	@FindBy (name = "phone")
	private static WebElement txtPhone;
	
	@FindBy (name = "userName")
	private static WebElement txtEmail;
	
	@FindBy (name = "address1")
	private static WebElement txtAddress1;
	
	@FindBy (name = "address2")	
	private static WebElement txtAddress2;
	
	@FindBy (name = "city")	
	private static WebElement txtCity;
	
	@FindBy (name = "state")	
	private static WebElement txtState;
	
	@FindBy (name = "postalCode")	
	private static WebElement txtPostalcode;
	
	@FindBy (tagName = "select")
	private static WebElement selectCountry;
	
	@FindBy (id = "email")
	private static WebElement txtUsername;
	
	@FindBy (name = "password")
	private static WebElement txtPassword;
	
	@FindBy (name = "confirmPassword")	
	private static WebElement txtConfirmPassword;
	
	@FindBy (css = "input[name='register']")
	private static WebElement buttonSubmit;
	
	@FindBy (xpath = "//b[contains(text(),'Dear')]")
	private static WebElement displayUsername;

	@FindBy (xpath = "//b[contains(text(),'Note:')]")
	private static WebElement loginUsername;
	
	WebDriver wDriver;
	//dataProvider getTestDataFor = new dataProvider();	
	
	//method mercuryRegistration
	//This is the constructor for this class.
	public mercuryRegistration(WebDriver driver)
	{
		  this.wDriver = driver;
		  PageFactory.initElements(wDriver, this);		  
	}
	
	//method registerNewUser
	//This method gets all the needed data from the class dataProvider, and creates a new user using that data.
	public String registerNewUser(){
		try {
			String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();	
			String screenshotPath = utils.getProperty("screenshotFolder");
			Extent.logInfo(currentMethodName,"Loading test data for the test case: registerNewUser");
			//Get the data from dataProvider method and put it in a string array
			String Data[] = dataProvider.registerNewUser();
			Extent.logInfo(currentMethodName,"Opening regisration page");		
			linkRegister.click();
			Select select;
			
			//Input registration details, which are provided by dataProvider class form the input excel file
			Extent.logInfo(currentMethodName,"Test data loaded; populating registration data on UI");		
			txtFirstname.sendKeys(Data[0]);
			txtLastname.sendKeys(Data[1]);
			txtPhone.sendKeys(Data[2]);
			txtEmail.sendKeys(Data[3]);
			txtAddress1.sendKeys(Data[4]);
			txtAddress2.sendKeys(Data[5]);
			txtCity.sendKeys(Data[6]);
			txtState.sendKeys(Data[7]);
			txtPostalcode.sendKeys(Data[8]);
			
			 select = new Select(selectCountry);
			 select.selectByVisibleText(Data[9]);
			
			txtUsername.sendKeys(Data[10]);
			txtPassword.sendKeys(Data[11]);
			txtConfirmPassword.sendKeys( Data[12]);
			Extent.logInfo(currentMethodName,"Clicking Submit button");		
			buttonSubmit.click();
			
			Extent.logInfo(currentMethodName,"Data population complete.");
			Extent.logInfo(currentMethodName,"Verifying URL of the page opened");
			//Verify whether current page URL is correct
			if(wDriver.getCurrentUrl().equals(Data[13])){
				Extent.logInfo(currentMethodName,"Verified current page URL");							
			}else{
				String scrPath = utils.takeScreenshot(wDriver, screenshotPath);
				Extent.logError(currentMethodName, "Page URL is not the one we expected, screenshot: "+scrPath);							
				return "ERROR: Page URL is not the one we expected";
			}				
				
			//Verify user display name from UI with the value provided from excel sheet
			Extent.logInfo(currentMethodName,"Verifying user display name");			
			if(displayUsername.isDisplayed() && displayUsername.getText().equals("Dear "+ Data[0] + " " + Data[1] + ",")){
				Extent.logInfo(currentMethodName,"User Display name verification successful");				
			}
			else{
				String scrPath = utils.takeScreenshot(wDriver, screenshotPath + utils.getDateTimeStamp()+"_wrongDisplayName.jpg");			
				Extent.logError(currentMethodName,"Could not find post registration screen; screenshot: "+scrPath);				
				return "ERROR: Could not find post registration screen";
			}
			
			//Verify user login id  from UI with the value provided from excel sheet
			Extent.logInfo(currentMethodName,"Verifying login username");			
			if(loginUsername.isDisplayed() && loginUsername.getText().equals("Note: Your user name is " + Data[10] + ".")){
				Extent.logInfo("registerNewUser","Login username verification successful");				
			}
			else{
				String scrPath = utils.takeScreenshot(wDriver, screenshotPath + utils.getDateTimeStamp()+"._wrongLoginIDjpg");
				Extent.logError(currentMethodName,"Login username displayed is different than the username we provided; screenshot: "+scrPath);				
				return "ERROR: Login username displayed is different than the username we provided";
			}
			Extent.logInfo(currentMethodName,"New user registration successful");			
			return "Registration successful";
		} catch (Exception e) {
			Extent.logError("registerNewUser", "Unexpected exception occured, details: " + e);			
			return "ERROR: Error occurred while entering registration details";
		}		
	}
	
	
}//END of class

