package appModules;

import java.util.regex.Pattern;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import commonLibs.commonDriver;
import commonLibs.dataProvider;
import utility.Log;

public class mercuryFlightBooking {

	// Veriable declaration section
	private static By linkFlightBooking = By.xpath("//a[contains(.,'Flights')]");
	private static By headerFlightDetails = By.xpath("//table/tbody/tr[1]/td/font/font/b/font/font");
	private static By radioJourneyTypeRoundTrip = By.xpath("//input[@value='roundtrip']");
	private static By radioJourneyTypeOneWay = By.xpath("//input[@value='oneway']");
	private static By selectPassengers = By.name("passCount");
	private static By selectDepartingFrom = By.name("fromPort");
	private static By selectOnMonth = By.name("fromMonth");
	private static By selectOnDay = By.name("fromDay");
	private static By selectArrivingIn = By.name("toPort");
	private static By selectReturningMonth = By.name("toMonth");
	private static By selectReturningDay = By.name("toDay");
	private static By selectServiceClassEconomy = By.xpath("//input[@value='Coach']");
	private static By selectServiceClassBusiness = By.xpath("//input[@value='Business']");
	private static By selectServiceClassFirst = By.xpath("//input[@value='First']");
	private static By selectAirline = By.name("airline");
	private static By buttonContiue = By.name("findFlights");
	private static By departText = By.xpath("//table[1]/tbody/tr[2]/td[1]/b/font");
	private static By returnText = By.xpath("//table[2]/tbody/tr/td/table/tbody/tr[2]/td/b/font");
	private static By radioSelectDepartureFlightBlueSky360 = By
			.xpath("//input[@value='Blue Skies Airlines$360$270$5:03']");
	private static By radioSelectDepartureFlightBlueSky361 = By
			.xpath("//input[@value='Blue Skies Airlines$361$271$7:10']");
	private static By radioSelectDepartureFlightPangea362 = By.xpath("//input[@value='Pangea Airlines$362$274$9:17']");
	private static By radioSelectDepartureFlightUnified363 = By
			.xpath("//input[@value='Unified Airlines$363$281$11:24']");
	private static By radioSelectArrivalFlightBlueSky630 = By
			.xpath("//input[@value='Blue Skies Airlines$630$270$12:23']");
	private static By radioSelectArrivalFlightBlueSky631 = By
			.xpath("//input[@value='Blue Skies Airlines$631$273$14:30']");
	private static By radioSelectArrivalFlightPangea632 = By.xpath("//input[@value='Pangea Airlines$632$282$16:37']");
	private static By radioSelectArrivalFlightUnified633 = By.xpath("//input[@value='Unified Airlines$633$303$18:44']");
	private static By buttonContinuePage2 = By.name("reserveFlights");
	private static By textDepart = By.xpath("//table/tbody/tr[1]/td[1]/font/b/font");
	private static By textSummary = By.xpath("//table/tbody/tr[1]/td/font/font/b/font/font");
	private static By flightBookingFromTo = By.xpath("//table/tbody/tr[1]/td[1]/b/font");
	private static By flightBookingToFrom = By.xpath("//table/tbody/tr[4]/td[1]/b/font");
	private static By bookingDepartureFlight = By.xpath("//table/tbody/tr[3]/td[1]/font/b");
	private static By bookingReturnFlight = By.xpath("//table/tbody/tr[6]/td[1]/font/font/font[1]/b");
	private static By summaryDepartureFlightCost = By.xpath("//table/tbody/tr[3]/td[3]/font");
	private static By summaryArrivalFlightCost = By.xpath("//table/tbody/tr[6]/td[3]/font");
	private static By summaryNumberOfPassengers = By.xpath("//table/tbody/tr[7]/td[2]/font");
	private static By summaryTaxes = By.xpath("//table/tbody/tr[8]/td[2]/font");
	private static By summaryTotalCost = By.xpath("//table/tbody/tr[9]/td[2]/font/b");
	private static By txtFirstname = By.name("passFirst0");
	private static By txtLastName = By.name("passLast0");
	private static By selectMeal = By.name("pass.0.meal");
	private static By selectCreditCard = By.name("creditCard");
	private static By txtCreditCardNumber = By.name("creditnumber");
	private static By selectExpiryMonth = By.name("cc_exp_dt_mn");
	private static By selectExpiryYear = By.name("cc_exp_dt_yr");
	private static By txtCCFirstName = By.name("cc_frst_name");
	private static By txtCCMiddleName = By.name("cc_mid_name");
	private static By txtCCLastName = By.name("cc_last_name");
	private static By checkboxTicketlessBilling = By.xpath("//tr[9]/td[2]/input[@name='ticketLess']");
	private static By txtBillingAddress1 = By.name("billAddress1");
	private static By txtBillingAddress2 = By.name("billAddress2");;
	private static By txtBillingCity = By.name("billCity");
	private static By txtBillingState = By.name("billState");
	private static By txtBillingPostalCode = By.name("billZip");
	private static By selectBillingCountry = By.name("billCountry");
	private static By checkboxSameBillingDelivery = By.xpath("//tr[15]/td[2]/input[@name='ticketLess']");
	private static By txtDeliveryAddress1 = By.name("delAddress1");
	private static By txtDeliveryAddress2 = By.name("delAddress2");;
	private static By txtDeliveryCity = By.name("delCity");
	private static By txtDeliveryState = By.name("delState");
	private static By txtDeliveryPostalCode = By.name("delZip");
	private static By selectDeliveryCountry = By.name("delCountry");
	private static By buttonSecurePurchase = By.name("buyFlights");
	private static By headerConfirmation = By.xpath("//table/tbody/tr[3]/td/p/font/b/font[2]");
	private static By buttonBackToFlights = By.xpath("//table/tbody/tr/td[1]/a/img");
	private static By buttonBackToHome = By.xpath("//table/tbody/tr/td[2]/a/img");
	public static By txtUserName = By.name("userName");
	static commonDriver oDriver;
	dataProvider getTestDataFor = new dataProvider();

	// method mercuryFlightBooking
	// This is the constructor of the class
	public mercuryFlightBooking() {
		DOMConfigurator.configure("log4j.xml");
		oDriver = new commonDriver();
	}// END OF CONSTRUCTOR

	// method openFlightBookingPage
	// This method opens flight bookings page, and waits for the page header
	// text to exist
	public String openFlightBookingPage() {
		try {
			oDriver.click(linkFlightBooking);
			oDriver.waitTillElementVisible(headerFlightDetails, 30l);
			return "Flight booking page opened successfully";
		} catch (Exception e) {
			Log.error(
					"mercuryFlightBooking()->openFlightBookingPage()-> Could not open flight booking page, here is the error: "
							+ e);
			return "ERROR: Could not open flight booking page";
		}
	}// END OF METHOD openFlightBookingPage

	// method inputFlightDetails
	// This method inputs basic flighs details. The data is fetched from excel
	// sheet using dataProvider class.
	public String inputFlightDetails() {
		try {

			// Verify title of the page , just to make sure that we are on the
			// correct page before we proceed further
			if (oDriver.getTitle().equals("Find a Flight: Mercury Tours:")) {
				Log.info("mercuryFlightBooking()->inputFlightDetails()->Now on the find flight page");
			} else {
				Log.error(
						"mercuryFlightBooking()->inputFlightDetails()-> Page title mismatch, currently not on the find flight page");
				return "ERROR: Page title mismatch, currently not on the find flight page";
			}

			// Fetch the data from excel using dataProvider class
			String Data[] = getTestDataFor.inputFlightDetails();

			// Check which of the radio button is selected by default when the
			// page is loaded.
			if (oDriver.isSelected(radioJourneyTypeRoundTrip)) {
				Log.info("Return Trip checkbox is slected by default");
			}

			if (oDriver.isSelected(radioJourneyTypeOneWay)) {
				Log.info("Return Trip checkbox is slected by default");
			}

			// Populating all the order fields, as per the test data provided by
			// dataProvider class.
			Log.info("Populatig all the provided data");

			if (Data[0].equals("Round Trip")) {
				oDriver.click(radioJourneyTypeRoundTrip);
			} else if (Data[0].equals("One way")) {
				oDriver.click(radioJourneyTypeOneWay);
			}

			oDriver.selectByVisibleText(selectPassengers, Data[1]);
			oDriver.selectByVisibleText(selectDepartingFrom, Data[2]);
			oDriver.selectByVisibleText(selectOnMonth, Data[3]);
			oDriver.selectByVisibleText(selectOnDay, Data[4]);
			oDriver.selectByVisibleText(selectArrivingIn, Data[5]);
			oDriver.selectByVisibleText(selectReturningMonth, Data[6]);
			oDriver.selectByVisibleText(selectReturningDay, Data[7]);

			if (Data[8].equalsIgnoreCase("Economy")) {
				oDriver.click(selectServiceClassEconomy);
			} else if (Data[8].equalsIgnoreCase("Business")) {
				oDriver.click(selectServiceClassBusiness);
			} else if (Data[8].equalsIgnoreCase("First")) {
				oDriver.click(selectServiceClassFirst);
			}

			oDriver.selectByVisibleText(selectAirline, Data[9]);
			oDriver.click(buttonContiue);
			oDriver.waitTillElementVisible(textDepart, 30l);
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
			if (oDriver.getTitle().equals("Select a Flight: Mercury Tours")) {
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
			if (oDriver.getText(departText).equalsIgnoreCase(Data[0] + " to " + Data[1])) {
				Log.error("mercuryFlightBooking()->selectFlight()-> FROM and TO flights match");
			} else {
				Log.error("mercuryFlightBooking()->selectFlight()-> FROM and TO flights doesn't match");
				return "ERROR: FROM and TO flights doesn't match";
			}

			// Verify To->FROM flight text is correct and as per the values we
			// entered in first step.
			if (oDriver.getText(returnText).equalsIgnoreCase(Data[1] + " to " + Data[0])) {
				Log.error("mercuryFlightBooking()->selectFlight()-> TO and FROM flights match");
			} else {
				Log.error("mercuryFlightBooking()->selectFlight()-> TO and FROM flights doesn't match");
				return "ERROR: TO and FROM flights doesn't match";
			}

			// Select appropriate departure flight as per the input test data
			// from excel sheet
			if (Data[2].trim().equalsIgnoreCase("Blue Skies Airlines 360")) {
				oDriver.click(radioSelectDepartureFlightBlueSky360);
			} else if (Data[2].trim().equalsIgnoreCase("Blue Skies Airlines 361")) {
				oDriver.click(radioSelectDepartureFlightBlueSky361);
			} else if (Data[2].trim().equalsIgnoreCase("Pangaea Airlines 362")) {
				oDriver.click(radioSelectDepartureFlightPangea362);
			} else if (Data[2].trim().equalsIgnoreCase("Unified Airlines 363")) {
				oDriver.click(radioSelectDepartureFlightUnified363);
			}

			// Select appropriate return flight as per the input test data from
			// excel sheet
			if (Data[3].trim().equalsIgnoreCase("Blue Skies Airlines 630")) {
				oDriver.click(radioSelectArrivalFlightBlueSky630);
			} else if (Data[3].trim().equalsIgnoreCase("Blue Skies Airlines 631")) {
				oDriver.click(radioSelectArrivalFlightBlueSky631);
			} else if (Data[3].trim().equalsIgnoreCase("Pangea Airlines 632")) {
				oDriver.click(radioSelectArrivalFlightPangea632);
			} else if (Data[3].trim().equalsIgnoreCase("Unified Airlines 633")) {
				oDriver.click(radioSelectArrivalFlightUnified633);
			}

			// Click Continue button to proceed to next page to complete the
			// registration
			oDriver.click(buttonContinuePage2);
			oDriver.waitTillElementVisible(textSummary, 30l);
			Log.info("Flights selected successfully");
			return "Flight selected successfully";

		} catch (Exception e) {
			Log.error("mercuryFlightBooking()->selectFlight()-> Error occured, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while selecting flight";
		}
	}// END OF METHOD selectFlight

	// method bookFlight
	// This method books the flight, and captures the screenshot of the final
	// page showing all the details of the passenger and flight
	public String bookFlight() {
		try {
			// Verify page title to make sure we are on the correct page
			if (oDriver.getTitle().equals("Book a Flight: Mercury Tours")) {
				Log.info("mercuryFlightBooking()->bookFlight()->Now on the flight booking page");
			} else {
				Log.error(
						"mercuryFlightBooking()->bookFlight()->Page title mismatch, currently not on the flight booking page");
				return "ERROR: Page title mismatch, currently not on the flight booking page";
			}

			String Data[] = getTestDataFor.bookFlight();

			// Verify depart FROM -> TO, and TO -> FROM cities
			if (oDriver.getText(flightBookingFromTo).equalsIgnoreCase(Data[0] + " to " + Data[1])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> FROM and TO flights match");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> FROM and TO flights doesn't match");
				return "ERROR:  FROM and TO flights doesn't match";
			}

			if (oDriver.getText(flightBookingToFrom).equalsIgnoreCase(Data[1] + " to " + Data[0])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> TO and FROM flights match");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> TO and FROM flights doesn't match");
				return "ERROR:  TO and FROM flights doesn't match";
			}

			// Verify if the flights are correct
			if (oDriver.getText(bookingDepartureFlight).equalsIgnoreCase(Data[2])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Departure flight is correct");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Wrong departure flight");
				return "ERROR: Wrong departure flight";
			}

			if (oDriver.getText(bookingReturnFlight).equalsIgnoreCase(Data[4])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Return flight is correct");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Wrong return flight");
				return "ERROR: TO and FROM flights doesn't match";
			}

			// Verify flight costs are correct
			if (oDriver.getText(summaryDepartureFlightCost).equalsIgnoreCase(Data[3])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Departure flight cost is displayed corretly");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Departure flight cost mismatch");
				return "ERROR: Departure flight cost mismatch";
			}

			if (oDriver.getText(summaryArrivalFlightCost).equalsIgnoreCase(Data[5])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Return flight cost is displayed correctly");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Return flight cost mismatch");
				return "ERROR: Return flight cost mismatch";
			}

			// Verify number of passengers
			if (oDriver.getText(summaryNumberOfPassengers).equalsIgnoreCase(Data[6])) {
				Log.error("mercuryFlightBooking()->bookFlight()-> Number of passenges are correct");
			} else {
				Log.error("mercuryFlightBooking()->bookFlight()-> Wrong number of passengers");
				return "ERROR: Wrong number of passengers";
			}

			// Verify flight cost calculation
			String[] tempString = oDriver.getText(summaryTaxes).split(Pattern.quote("$"));

			int tempTaxes = Integer.parseInt(tempString[1]);
			tempString = oDriver.getText(summaryTotalCost).split(Pattern.quote("$"));
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
			oDriver.setText(txtFirstname, Data[7]);
			oDriver.setText(txtLastName, Data[8]);
			oDriver.selectByVisibleText(selectMeal, Data[9]);
			oDriver.selectByVisibleText(selectCreditCard, Data[10]);
			oDriver.setText(txtCreditCardNumber, Data[11]);
			oDriver.selectByVisibleText(selectExpiryMonth, Data[12]);
			oDriver.selectByVisibleText(selectExpiryYear, Data[13]);
			oDriver.setText(txtCCFirstName, Data[14]);
			oDriver.setText(txtCCMiddleName, Data[15]);
			oDriver.setText(txtCCLastName, Data[16]);

			if (Data[17].equalsIgnoreCase("yes")) {
				oDriver.click(checkboxTicketlessBilling);
			}

			oDriver.setText(txtBillingAddress1, Data[18]);
			oDriver.setText(txtBillingAddress2, Data[19]);
			oDriver.setText(txtBillingCity, Data[20]);
			oDriver.setText(txtBillingState, Data[21]);
			oDriver.setText(txtBillingPostalCode, Data[22]);
			oDriver.selectByVisibleText(selectBillingCountry, Data[23]);

			if (Data[24].equalsIgnoreCase("yes")) {
				oDriver.click(checkboxSameBillingDelivery);
			}

			oDriver.setText(txtDeliveryAddress1, Data[25]);
			oDriver.setText(txtDeliveryAddress2, Data[26]);
			oDriver.setText(txtDeliveryState, Data[27]);
			oDriver.setText(txtDeliveryCity, Data[28]);
			oDriver.setText(txtDeliveryPostalCode, Data[29]);

			// If ticket delivery country is not UNITED STATES, a popup is
			// displayed showing the extra delivery charges. The popup is
			// handled here.
			if (!Data[30].equals("UNITED STATES")) {
				oDriver.setText(selectDeliveryCountry, Data[30]);
				Log.info("Waiting for alert to appear");
				oDriver.waitTillAlertVisible(30l);
				Log.info("Alert visible, switching to alert");
				oDriver.switchToAlert();
				Log.info("Switched to alert");
				Log.info("Alert Text: " + oDriver.getAlertText());
				Log.info("Accpeting the alert");
				oDriver.acceptAlert();
				Log.info("Alert accepted");
			}

			oDriver.click(buttonSecurePurchase);
			oDriver.waitTillElementVisible(headerConfirmation, 30l);
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
			oDriver.click(buttonBackToFlights);
			oDriver.waitTillElementVisible(headerFlightDetails, 30l);
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
			oDriver.click(buttonBackToHome);
			oDriver.waitTillElementVisible(txtUserName, 30l);
			return "Navigated to Home page successfully";
		} catch (Exception e) {
			Log.error(
					"mercuryFlightBooking()->backToHome()-> Error occurred while going back to home page, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while going back to home page";
		}
	}// END OF METHOD backToHome

}// END OF CLASS
