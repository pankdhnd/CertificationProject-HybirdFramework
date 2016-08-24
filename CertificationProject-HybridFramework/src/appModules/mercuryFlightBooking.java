package appModules;

import java.util.regex.Pattern;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import commonLibs.Log;
import commonLibs.dataProvider;
import commonLibs.utils;

public class mercuryFlightBooking {

	// Veriable declaration section

	@FindBy(xpath = "//a[contains(.,'Flights')]")
	private static WebElement linkFlightBooking;

	@FindBy(xpath = "//table/tbody/tr[1]/td/font/font/b/font/font")
	private static WebElement headerFlightDetails;

	@FindBy(xpath = "//input[@value='roundtrip']")
	private static WebElement radioJourneyTypeRoundTrip;

	@FindBy(xpath = "//input[@value='oneway']")
	private static WebElement radioJourneyTypeOneWay;

	@FindBy(name = "passCount")
	private static WebElement selectPassengers;

	@FindBy(name = "fromPort")
	private static WebElement selectDepartingFrom;

	@FindBy(name = "fromMonth")
	private static WebElement selectOnMonth;

	@FindBy(name = "fromDay")
	private static WebElement selectOnDay;

	@FindBy(name = "toPort")
	private static WebElement selectArrivingIn;

	@FindBy(name = "toMonth")
	private static WebElement selectReturningMonth;

	@FindBy(name = "toDay")
	private static WebElement selectReturningDay;

	@FindBy(xpath = "//input[@value='Coach']")
	private static WebElement selectServiceClassEconomy;

	@FindBy(xpath = "//input[@value='Business']")
	private static WebElement selectServiceClassBusiness;

	@FindBy(xpath = "//input[@value='First']")
	private static WebElement selectServiceClassFirst;

	@FindBy(name = "airline")
	private static WebElement selectAirline;

	@FindBy(name = "findFlights")
	private static WebElement buttonContiue;

	@FindBy(xpath = "//table[1]/tbody/tr[2]/td[1]/b/font")
	private static WebElement departText;

	@FindBy(xpath = "//table[2]/tbody/tr/td/table/tbody/tr[2]/td/b/font")
	private static WebElement returnText;

	@FindBy(xpath = "//input[@value='Blue Skies Airlines$360$270$5:03']")
	private static WebElement radioSelectDepartureFlightBlueSky360;

	@FindBy(xpath = "//input[@value='Blue Skies Airlines$361$271$7:10']")
	private static WebElement radioSelectDepartureFlightBlueSky361;

	@FindBy(xpath = "//input[@value='Pangea Airlines$362$274$9:17']")
	private static WebElement radioSelectDepartureFlightPangea362;

	@FindBy(xpath = "//input[@value='Unified Airlines$363$281$11:24']")
	private static WebElement radioSelectDepartureFlightUnified363;

	@FindBy(xpath = "//input[@value='Blue Skies Airlines$630$270$12:23']")
	private static WebElement radioSelectArrivalFlightBlueSky630;

	@FindBy(xpath = "//input[@value='Blue Skies Airlines$631$273$14:30']")
	private static WebElement radioSelectArrivalFlightBlueSky631;

	@FindBy(xpath = "//input[@value='Pangea Airlines$632$282$16:37']")
	private static WebElement radioSelectArrivalFlightPangea632;

	@FindBy(xpath = "//input[@value='Unified Airlines$633$303$18:44']")
	private static WebElement radioSelectArrivalFlightUnified633;

	@FindBy(name = "reserveFlights")
	private static WebElement buttonContinuePage2;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/font/b/font")
	private static WebElement textDepart;

	@FindBy(xpath = "//table/tbody/tr[1]/td/font/font/b/font/font")
	private static WebElement textSummary;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/b/font")
	private static WebElement flightBookingFromTo;

	@FindBy(xpath = "//table/tbody/tr[4]/td[1]/b/font")
	private static WebElement flightBookingToFrom;

	@FindBy(xpath = "//table/tbody/tr[3]/td[1]/font/b")
	private static WebElement bookingDepartureFlight;

	@FindBy(xpath = "//table/tbody/tr[6]/td[1]/font/font/font[1]/b")
	private static WebElement bookingReturnFlight;

	@FindBy(xpath = "//table/tbody/tr[3]/td[3]/font")
	private static WebElement summaryDepartureFlightCost;

	@FindBy(xpath = "//table/tbody/tr[6]/td[3]/font")
	private static WebElement summaryArrivalFlightCost;

	@FindBy(xpath = "//table/tbody/tr[7]/td[2]/font")
	private static WebElement summaryNumberOfPassengers;

	@FindBy(xpath = "//table/tbody/tr[8]/td[2]/font")
	private static WebElement summaryTaxes;

	@FindBy(xpath = "//table/tbody/tr[9]/td[2]/font/b")
	private static WebElement summaryTotalCost;

	@FindBy(name = "passFirst0")
	private static WebElement txtFirstname;

	@FindBy(name = "passLast0")
	private static WebElement txtLastName;

	@FindBy(name = "pass.0.meal")
	private static WebElement selectMeal;

	@FindBy(name = "creditCard")
	private static WebElement selectCreditCard;

	@FindBy(name = "creditnumber")
	private static WebElement txtCreditCardNumber;

	@FindBy(name = "cc_exp_dt_mn")
	private static WebElement selectExpiryMonth;

	@FindBy(name = "cc_exp_dt_yr")
	private static WebElement selectExpiryYear;

	@FindBy(name = "cc_frst_name")
	private static WebElement txtCCFirstName;

	@FindBy(name = "cc_mid_name")
	private static WebElement txtCCMiddleName;

	@FindBy(name = "cc_last_name")
	private static WebElement txtCCLastName;

	@FindBy(xpath = "//tr[9]/td[2]/input[@name='ticketLess']")
	private static WebElement checkboxTicketlessBilling;

	@FindBy(name = "billAddress1")
	private static WebElement txtBillingAddress1;

	@FindBy(name = "billAddress2")
	private static WebElement txtBillingAddress2;

	@FindBy(name = "billCity")
	private static WebElement txtBillingCity;

	@FindBy(name = "billState")
	private static WebElement txtBillingState;

	@FindBy(name = "billZip")
	private static WebElement txtBillingPostalCode;

	@FindBy(name = "billCountry")
	private static WebElement selectBillingCountry;

	@FindBy(xpath = "//tr[15]/td[2]/input[@name='ticketLess']")
	private static WebElement checkboxSameBillingDelivery;

	@FindBy(name = "delAddress1")
	private static WebElement txtDeliveryAddress1;

	@FindBy(name = "delAddress2")
	private static WebElement txtDeliveryAddress2;

	@FindBy(name = "delCity")
	private static WebElement txtDeliveryCity;

	@FindBy(name = "delState")
	private static WebElement txtDeliveryState;

	@FindBy(name = "delZip")
	private static WebElement txtDeliveryPostalCode;

	@FindBy(name = "delCountry")
	private static WebElement selectDeliveryCountry;

	@FindBy(name = "buyFlights")
	private static WebElement buttonSecurePurchase;

	@FindBy(xpath = "//table/tbody/tr[3]/td/p/font/b/font[2]")
	private static WebElement headerConfirmation;

	@FindBy(xpath = "//table/tbody/tr/td[1]/a/img")
	private static WebElement buttonBackToFlights;

	@FindBy(xpath = "//table/tbody/tr/td[2]/a/img")
	private static WebElement buttonBackToHome;

	@FindBy(name = "userName")
	public static WebElement txtUserName;

	WebDriver wDriver;
	dataProvider getTestDataFor = new dataProvider();

	// method mercuryFlightBooking
	// This is the constructor of the class
	public mercuryFlightBooking(WebDriver driver) {
		this.wDriver = driver;
		PageFactory.initElements(wDriver, this);
		DOMConfigurator.configure("log4j.xml");
	}// END OF CONSTRUCTOR

	// method openFlightBookingPage
	// This method opens flight bookings page, and waits for the page header
	// text to exist
	public String openFlightBookingPage() {
		try {
			linkFlightBooking.click();
			utils.waitTillElementVisible(wDriver, headerFlightDetails, 30);
			// WebDriverWait oWait = new WebDriverWait(wDriver, 30);
			// oWait.until(ExpectedConditions.visibilityOf(headerFlightDetails));
			return "Flight booking page opened successfully";
		} catch (Exception e) {
			Log.error(
					"mercuryFlightBooking()->openFlightBookingPage()-> Could not open flight booking page, here is the error: "
							+ e);
			return "ERROR: Could not open flight booking page";
		}
	}// END OF METHOD openFlightBookingPage

	// method inputFlightDetails
	// This method inputs basic flights details. The data is fetched from excel
	// sheet using dataProvider class.
	public String inputFlightDetails() {
		try {

			// Verify title of the page , just to make sure that we are on the
			// correct page before we proceed further
			if (wDriver.getTitle().equals("Find a Flight: Mercury Tours:")) {
				Log.info("mercuryFlightBooking()->inputFlightDetails()->Now on the find flight page");
			} else {
				Log.error(
						"mercuryFlightBooking()->inputFlightDetails()-> Page title mismatch, currently not on the find flight page");
				return "ERROR: Page title mismatch, currently not on the find flight page";
			}

			// Fetch the data from excel using dataProvider class
			String Data[] = getTestDataFor.inputFlightDetails();

			// Check which of the radio button is selected WebElement default
			// when the
			// page is loaded.
			if (radioJourneyTypeRoundTrip.isSelected()) {
				Log.info("Return Trip checkbox is slected WebElement default");
			}

			if (radioJourneyTypeOneWay.isSelected()) {
				Log.info("Return Trip checkbox is slected WebElement default");
			}

			// Populating all the order fields, as per the test data provided
			// WebElement
			// dataProvider class.
			Log.info("Populatig all the provided data");

			if (Data[0].equals("Round Trip")) {
				radioJourneyTypeRoundTrip.click();
			} else if (Data[0].equals("One way")) {
				radioJourneyTypeOneWay.click();
			}
			Select select;

			utils.selectByVisibleText(wDriver, selectPassengers, Data[1]);
			utils.selectByVisibleText(wDriver, selectDepartingFrom, Data[2]);
			utils.selectByVisibleText(wDriver, selectOnMonth, Data[3]);
			utils.selectByVisibleText(wDriver, selectOnDay, Data[4]);
			utils.selectByVisibleText(wDriver, selectArrivingIn, Data[5]);
			utils.selectByVisibleText(wDriver, selectReturningMonth, Data[6]);
			utils.selectByVisibleText(wDriver, selectReturningDay, Data[7]);

			/*
			 * select = new Select(selectPassengers);
			 * select.selectByVisibleText(Data[1]);
			 * 
			 * select = new Select(selectDepartingFrom);
			 * select.selectByVisibleText(Data[2]);
			 * 
			 * select = new Select(selectOnMonth);
			 * select.selectByVisibleText(Data[3]);
			 * 
			 * select = new Select(selectOnDay);
			 * select.selectByVisibleText(Data[4]);
			 * 
			 * select = new Select(selectArrivingIn);
			 * select.selectByVisibleText(Data[5]);
			 * 
			 * select = new Select(selectReturningMonth);
			 * select.selectByVisibleText(Data[6]);
			 * 
			 * select = new Select(selectReturningDay);
			 * select.selectByVisibleText(Data[7]);
			 */

			if (Data[8].equalsIgnoreCase("Economy")) {
				selectServiceClassEconomy.click();
			} else if (Data[8].equalsIgnoreCase("Business")) {
				selectServiceClassBusiness.click();
			} else if (Data[8].equalsIgnoreCase("First")) {
				selectServiceClassFirst.click();
			}

			utils.selectByVisibleText(wDriver, selectAirline, Data[9]);

			// select = new Select(selectAirline);
			// select.selectByVisibleText(Data[9]);

			buttonContiue.click();
			utils.waitTillElementVisible(wDriver, textDepart, 30l);
			Log.info("mercuryFlightBooking()->inputFlightDetails()->All fields populated successfully");
			return "All fields populated successfully";

		} catch (Exception e) {
			Log.error("mercuryFlightBooking()->inputFlightDetails()-> Error occured, here are the details: " + e);
			return "ERROR: Error occurred while entering flight details";
		}
	}// END OF METHOD inputFlightDetails

	// method selectFlight
	// This method selects the departure and returning flights
	public String selectFlight() {
		try {

			// verify page title to make sure that we are on the correct page
			// before we proceed.
			if (wDriver.getTitle().equals("Select a Flight: Mercury Tours")) {
				Log.info("mercuryFlightBooking()->selectFlight()->Now on the flight selection page");
			} else {
				Log.error(
						"mercuryFlightBooking()->selectFlight()->Page title mismatch, currently not on the flight selection page");
				return "ERROR: Page title mismatch, currently not on the flight selection page";
			}

			// Fetch the input test data from excle sheet using dataProvider
			// class
			String Data[] = getTestDataFor.selectFlight();

			// Verify FROM->TO flight text is correct and as per the values we
			// entered in first step.
			if (departText.getText().equalsIgnoreCase(Data[0] + " to " + Data[1])) {
				Log.error("mercuryFlightBooking()->selectFlight()-> FROM and TO flights match");
			} else {
				Log.error("mercuryFlightBooking()->selectFlight()-> FROM and TO flights doesn't match");
				return "ERROR: FROM and TO flights doesn't match";
			}

			// Verify To->FROM flight text is correct and as per the values we
			// entered in first step.
			if (returnText.getText().equalsIgnoreCase(Data[1] + " to " + Data[0])) {
				Log.error("mercuryFlightBooking()->selectFlight()-> TO and FROM flights match");
			} else {
				Log.error("mercuryFlightBooking()->selectFlight()-> TO and FROM flights doesn't match");
				return "ERROR: TO and FROM flights doesn't match";
			}

			// Select appropriate departure flight as per the input test data
			// from excel sheet
			if (Data[2].trim().equalsIgnoreCase("Blue Skies Airlines 360")) {
				radioSelectDepartureFlightBlueSky360.click();
			} else if (Data[2].trim().equalsIgnoreCase("Blue Skies Airlines 361")) {
				radioSelectDepartureFlightBlueSky361.click();
			} else if (Data[2].trim().equalsIgnoreCase("Pangaea Airlines 362")) {
				radioSelectDepartureFlightPangea362.click();
			} else if (Data[2].trim().equalsIgnoreCase("Unified Airlines 363")) {
				radioSelectDepartureFlightUnified363.click();
			}

			// Select appropriate return flight as per the input test data from
			// excel sheet
			if (Data[3].trim().equalsIgnoreCase("Blue Skies Airlines 630")) {
				radioSelectArrivalFlightBlueSky630.click();
			} else if (Data[3].trim().equalsIgnoreCase("Blue Skies Airlines 631")) {
				radioSelectArrivalFlightBlueSky631.click();
			} else if (Data[3].trim().equalsIgnoreCase("Pangea Airlines 632")) {
				radioSelectArrivalFlightPangea632.click();
			} else if (Data[3].trim().equalsIgnoreCase("Unified Airlines 633")) {
				radioSelectArrivalFlightUnified633.click();
			}

			// Click Continue button to proceed to next page to complete the
			// registration
			buttonContinuePage2.click();
			utils.waitTillElementVisible(wDriver, textSummary, 30l);
			Log.info("Flights selected successfully");
			return "Flight selected successfully";

		} catch (Exception e) {
			Log.error("mercuryFlightBooking()->selectFlight()-> Error occured, here are the details: " + e);
			return "ERROR: Error occurred while selecting flight";
		}
	}// END OF METHOD selectFlight

	// method bookFlight
	// This method books the flight, and captures the screenshot of the final
	// page showing all the details of the passenger and flight
	public String bookFlight() {
		try {
			// Verify page title to make sure we are on the correct page
			if (wDriver.getTitle().equals("Book a Flight: Mercury Tours")) {
				Log.info("mercuryFlightBooking()->bookFlight()->Now on the flight booking page");
			} else {
				Log.error(
						"mercuryFlightBooking()->bookFlight()->Page title mismatch, currently not on the flight booking page");
				return "ERROR: Page title mismatch, currently not on the flight booking page";
			}

			String Data[] = getTestDataFor.bookFlight();

			// Verify depart FROM -> TO, and TO -> FROM cities
			if (flightBookingFromTo.getText().equalsIgnoreCase(Data[0] + " to " + Data[1])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> FROM and TO flights match");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> FROM and TO flights doesn't match");
				return "ERROR:  FROM and TO flights doesn't match";
			}

			if (flightBookingToFrom.getText().equalsIgnoreCase(Data[1] + " to " + Data[0])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> TO and FROM flights match");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> TO and FROM flights doesn't match");
				return "ERROR:  TO and FROM flights doesn't match";
			}

			// Verify if the flights are correct
			if (bookingDepartureFlight.getText().equalsIgnoreCase(Data[2])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Departure flight is correct");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Wrong departure flight");
				return "ERROR: Wrong departure flight";
			}

			if (bookingReturnFlight.getText().equalsIgnoreCase(Data[4])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Return flight is correct");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Wrong return flight");
				return "ERROR: TO and FROM flights doesn't match";
			}

			// Verify flight costs are correct
			if (summaryDepartureFlightCost.getText().equalsIgnoreCase(Data[3])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Departure flight cost is displayed corretly");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Departure flight cost mismatch");
				return "ERROR: Departure flight cost mismatch";
			}

			if (summaryArrivalFlightCost.getText().equalsIgnoreCase(Data[5])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Return flight cost is displayed correctly");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Return flight cost mismatch");
				return "ERROR: Return flight cost mismatch";
			}

			// Verify number of passengers
			if (summaryNumberOfPassengers.getText().equalsIgnoreCase(Data[6])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Number of passenges are correct");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Wrong number of passengers");
				return "ERROR: Wrong number of passengers";
			}

			// Verify flight cost calculation
			String[] tempString = summaryTaxes.getText().split(Pattern.quote("$"));

			int tempTaxes = Integer.parseInt(tempString[1]);
			tempString = summaryTotalCost.getText().split(Pattern.quote("$"));
			int actualTotalCost = Integer.parseInt(tempString[1]);
			int NumberOfPassengers = Integer.parseInt(Data[6]);
			int expectedTotalCost = (Integer.parseInt(Data[3]) + Integer.parseInt(Data[5])) * NumberOfPassengers
					+ tempTaxes;

			if (expectedTotalCost == actualTotalCost) {
				Log.info("mercuryFlightBooking()->bookFlight()-> Expected and actual total cost matches");
			} else {
				Log.info("mercuryFlightBooking()->bookFlight()-> Expected and actual total cost mismatch");
				return "ERROR: Expected and actual total cost mismatch";
			}

			// select remaining data from the excel
			Log.info("Inputting all the details");
			txtFirstname.sendKeys(Data[7]);
			txtLastName.sendKeys(Data[8]);
			utils.selectByVisibleText(wDriver, selectMeal, Data[9]);
			utils.selectByVisibleText(wDriver, selectCreditCard, Data[10]);
			txtCreditCardNumber.sendKeys(Data[11]);
			utils.selectByVisibleText(wDriver, selectExpiryMonth, Data[12]);
			utils.selectByVisibleText(wDriver, selectExpiryYear, Data[13]);
			txtCCFirstName.sendKeys(Data[14]);
			txtCCMiddleName.sendKeys(Data[15]);
			txtCCLastName.sendKeys(Data[16]);

			if (Data[17].equalsIgnoreCase("yes")) {
				checkboxTicketlessBilling.click();
			}

			txtBillingAddress1.sendKeys(Data[18]);
			txtBillingAddress2.sendKeys(Data[19]);
			txtBillingCity.sendKeys(Data[20]);
			txtBillingState.sendKeys(Data[21]);
			txtBillingPostalCode.sendKeys(Data[22]);
			utils.selectByVisibleText(wDriver, selectBillingCountry, Data[23]);

			if (Data[24].equalsIgnoreCase("yes")) {
				checkboxSameBillingDelivery.click();
			}

			txtDeliveryAddress1.sendKeys(Data[25]);
			txtDeliveryAddress2.sendKeys(Data[26]);
			txtDeliveryState.sendKeys(Data[27]);
			txtDeliveryCity.sendKeys(Data[28]);
			txtDeliveryPostalCode.sendKeys(Data[29]);

			// If ticket delivery country is not UNITED STATES, a popup is
			// displayed showing the extra delivery charges. The popup is
			// handled here.
			if (!Data[30].equals("UNITED STATES")) {
				utils.selectByVisibleText(wDriver, selectDeliveryCountry, Data[30]);
				Log.info("Waiting for alert to appear");
				utils.waitTillAlertVisible(wDriver, 60l);
				Log.info("Alert visible, switching to alert");
				utils.switchToAlert(wDriver);
				Log.info("Switched to alert");
				Log.info("Alert Text: " + utils.getAlertText(wDriver));
				Log.info("Accpeting the alert");
				utils.acceptAlert(wDriver);
				Log.info("Alert accepted");
			}

			buttonSecurePurchase.click();
			utils.waitTillElementVisible(wDriver, headerConfirmation, 30l);
			Log.info("Flight booked successfully");
			return "Flight booked successfully";

		} catch (Exception e) {
			Log.error("mercuryFlightBooking()->bookFlight()-> Error occured, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while booking flight";
		}
	}// END OF METHOD bookFlight

	// method backToFlights
	// This method clicks Back To Flights button on the page and navigates user
	// to flights page again.
	public String backToFlights() {
		try {
			buttonBackToFlights.click();
			utils.waitTillElementVisible(wDriver, headerFlightDetails, 30l);
			;
			return "Flight booking page opened successfully";
		} catch (Exception e) {
			Log.error(
					"mercuryFlightBooking()->backToFlights()-> Error occurred while going back to flights page, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while going back to flights page";
		}
	}// END OF METHOD backToFlight

	// method backToHome
	// This method clisk Back To Home button on the UI and navigates the user to
	// home page.
	public String backToHome() {
		try {
			buttonBackToHome.click();
			utils.waitTillElementVisible(wDriver, txtUserName, 30l);
			return "Navigated to Home page successfully";
		} catch (Exception e) {
			Log.error(
					"mercuryFlightBooking()->backToHome()-> Error occurred while going back to home page, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while going back to home page";
		}
	}// END OF METHOD backToHome

}// END OF CLASS
