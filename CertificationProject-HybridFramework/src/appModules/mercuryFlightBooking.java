package appModules;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;

import commonLibs.commonDriver;
import commonLibs.dataProvider;
import commonLibs.utils;
import utility.Log;

public class mercuryFlightBooking {

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
	private static By headerDepart = By.xpath("//b/font[contains(text(),'DEPART')]");
	private static By departText = By.xpath("//table[1]/tbody/tr[2]/td[1]/b/font");
	private static By returnText = By.xpath("//table[2]/tbody/tr/td/table/tbody/tr[2]/td/b/font");
	private static By radioSelectDepartureFlightBlueSky360 = By.xpath("//input[@value='Blue Skies Airlines$360$270$5:03']");	
	private static By radioSelectDepartureFlightBlueSky361 = By.xpath("//input[@value='Blue Skies Airlines$361$271$7:10']");
	private static By radioSelectDepartureFlightPangea362 = By.xpath("//input[@value='Pangea Airlines$362$274$9:17']");
	private static By radioSelectDepartureFlightUnified363 = By.xpath("//input[@value='Unified Airlines$363$281$11:24']");
	private static By radioSelectArrivalFlightBlueSky630 = By.xpath("//input[@value='Blue Skies Airlines$630$270$12:23']");
	private static By radioSelectArrivalFlightBlueSky631 = By.xpath("//input[@value='Blue Skies Airlines$631$273$14:30']");
	private static By radioSelectArrivalFlightPangea632 = By.xpath("//input[@value='Pangea Airlines$632$282$16:37']");
	private static By radioSelectArrivalFlightUnified633 = By.xpath("//input[@value='Unified Airlines$633$303$18:44']");	
	private static By buttonContinuePage2 = By.name("reserveFlights");
	
	private static By priceBlueSkies360 = By.xpath("//table[1]/tbody/tr[4]/td/font/font/b");
	private static By priceBlueSkies361 = By.xpath("//table[1]/tbody/tr[4]/td/font/font/b");
	private static By pricePangaea362 = By.xpath("//table[1]/tbody/tr[8]/td/font/font/b");
	private static By priceUnified363 = By.xpath("//table[1]/tbody/tr[10]/td/font/font/b");
	
	private static By priceBlueSkies630 = By.xpath("//table[2]/tbody/tr[4]/td/font/font/b");
	private static By priceBlueSkies631 = By.xpath("//table[2]/tbody/tr[6]/td/font/font/b");
	private static By pricePangaea632 = By.xpath("//table[2]/tbody/tr[8]/td/font/font/b");
	private static By priceUnified633 = By.xpath("//table[2]/tbody/tr[10]/td/font/font/b");
	
	
	
	private static By headerSummary = By.xpath("//table/tbody/tr[1]/td/font/font/b/font/font");
	private static By summaryDepartText = By.xpath("//table/tbody/tr[1]/td[1]/b/font");
	private static By summaryArrivalText = By.xpath("//table/tbody/tr[4]/td[1]/b/font");
	private static By summaryDepartureFlightCost = By.xpath("//table/tbody/tr[3]/td[3]/font");
	private static By summaryArrivalFlightCost = By.xpath("//table/tbody/tr[6]/td[3]/font");
	private static By summaryNumberOfPassengers = By.xpath("//table/tbody/tr[7]/td[2]/font");
	private static By summaryTaxes = By.xpath("//table/tbody/tr[8]/td[2]/font");
	private static By summaryTotalCost = By.xpath("//table/tbody/tr[9]/td[2]/font/b");
	private static By txtFirstname;
	private static By txtLastName;
	private static By selectMeal;
	private static By selectCreditCard = By.name("creditCard");
	private static By txtCreditCardNumber = By.name("creditnumber");
	private static By selectExpiryMonth = By.name("cc_exp_dt_mn");
	private static By selectExpiryYear = By.name("cc_exp_dt_yr");
	private static By txtCCFirstName = By.name("cc_frst_name");
	private static By txtCCMiddleName = By.name("cc_mid_name");
	private static By txtCCLastName = By.name("cc_last_name");
	private static By checkboxTicketlessBilling = By.name("ticketLess");
	private static By txtBillingAddress1 = By.name("billAddress1");
	private static By txtBillingAddress2 = By.name("billAddress2");;
	private static By txtBillingCity = By.name("billCity");
	private static By txtBillingState = By.name("billState");
	private static By txtBillingPostalCode = By.name("billZip");
	private static By selectBillingCountry = By.name("billCountry");	
	private static By txtDeliveryAddress1 = By.name("delAddress1");
	private static By txtDeliveryAddress2 = By.name("delAddress2");;
	private static By txtDeliveryCity = By.name("delCity");
	private static By txtDeliveryState = By.name("delState");
	private static By txtDeliveryPostalCode = By.name("delZip");
	private static By selectDeliveryCountry = By.name("delCountry");
	private static By buttonSecurePurchase = By.name("buyFlights");
	private static By headerConfirmation = By.xpath("//table/tbody/tr[3]/td/p/font/b/font[2]");
	private static By headerFlightConfirmatioNumber = By.xpath("//table/tbody/tr/td[1]/b/font/font/b/font[1]");
	private static By headerConfirmDeparting = By.xpath("//table/tbody/tr[3]/td/font/b");
	private static By headerConfirmReturning = By.xpath("//table/tbody/tr[5]/td/font");
	private static By headerConfirmNumberOfPessengers = By.xpath("//table/tbody/tr[7]/td/font");
	private static By headerBilledTo = By.xpath("//table/tbody/tr[9]/td/p");
	private static By headerDeliveryTo = By.xpath("//table/tbody/tr[11]/td/p");
	private static By headerTotalTaxes = By.xpath("//table/tbody/tr[1]/td[2]/font/font/font/b/font");
	private static By headerTotalPrice = By.xpath("//table/tbody/tr[2]/td[2]/font/b/font/font/b/font");
	private static By buttonBackToHome = By.xpath("//table/tbody/tr/td[2]/a/img");
	private static By buttonBackToFlights = By.xpath("//table/tbody/tr/td[1]/a/img");
	private static By buttonLogout = By.xpath("//table/tbody/tr/td[3]/a/img");
	private static String departureFlightCost;
	private static String arrivalFlightCost;
	
	
	static commonDriver oDriver;
	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";
	private static Properties oDriverProperties;
	private static String screenshotPath;
	dataProvider getTestDataFor = new dataProvider();
	
	
	public mercuryFlightBooking(){
		  DOMConfigurator.configure("log4j.xml");
		  oDriver = new commonDriver();
		  oDriverProperties = utils.getProperties(driverPropertyFile);	
		  screenshotPath = oDriverProperties.getProperty("screenshotFolder").trim();
	}
	
	public String inputFlightDetails(){
		try {
			
			String Data[] = getTestDataFor.inputFlightDetails();			
			//Check which of the radio button is already selected
			if (oDriver.isSelected(radioJourneyTypeRoundTrip)){
				Log.info("Return Trip checkbox is slected by default");
			}
						
			if (oDriver.isSelected(radioJourneyTypeOneWay)){
				Log.info("Return Trip checkbox is slected by default");
			}
			
			Log.info("Populatig all the provided data");
			
			if(Data[0].equals("Round Trip")) 
			{
				oDriver.click(radioJourneyTypeRoundTrip);
			}
			else if (Data[0].equals("One way")){
				oDriver.click(radioJourneyTypeOneWay);
			}			
			
			oDriver.selectByVisibleText(selectPassengers, Data[1]);
			oDriver.selectByVisibleText(selectDepartingFrom, Data[2]);
			oDriver.selectByVisibleText(selectOnMonth, Data[3]);
			oDriver.selectByVisibleText(selectOnDay, Data[4]);
			oDriver.selectByVisibleText(selectArrivingIn, Data[5]);
			oDriver.selectByVisibleText(selectReturningMonth, Data[6]);
			oDriver.selectByVisibleText(selectReturningDay, Data[7]);
			
			if (Data[8].equalsIgnoreCase("Economy")){
				oDriver.click(selectServiceClassEconomy);				
			}
			else if(Data[8].equalsIgnoreCase("Business")){
				oDriver.click(selectServiceClassBusiness);
			}
			else if(Data[8].equalsIgnoreCase("First")){
				oDriver.click(selectServiceClassFirst);
			}
			
			oDriver.selectByVisibleText(selectAirline, Data[9]);
			oDriver.click(buttonContiue);
			Log.info("mercuryFlightBooking()->inputFlightDetails()->All fields populated successfully");
			return "All fields populated successfully";

		} catch (Exception e) {			
			Log.error("mercuryFlightBooking()->inputFlightDetails()-> Error occured, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while entering flight details";
		}
	}
	
	public String selectFlight(){
		try {			
			if(oDriver.isVisible(headerDepart) && oDriver.getText(headerDepart).equals("DEPART")){
				Log.info("mercuryFlightBooking()->selectFlight()->Now on the flight selection page");
			}
			else{
				Log.error("mercuryFlightBooking()->selectFlight()->Could not find DEPART header");
				return "ERROR: Could not find DEPART header";
			}
			
			String Data[] = getTestDataFor.selectFlight();
			
			if (oDriver.getText(departText).equalsIgnoreCase(Data[0] + " to " + Data[1])){
				Log.error("mercuryFlightBooking()->selectFlight()-> FROM and TO flights match");
			}
			else{
				Log.error("mercuryFlightBooking()->selectFlight()-> FROM and TO flights doesn't match");
				return "ERROR: FROM and TO flights doesn't match";
			}
			
			
			if (oDriver.getText(returnText).equalsIgnoreCase(Data[1] + " to " + Data[0])){
				Log.error("mercuryFlightBooking()->selectFlight()-> TO and FROM flights match");
			}
			else{
				Log.error("mercuryFlightBooking()->selectFlight()-> TO and FROM flights doesn't match");
				return "ERROR: TO and FROM flights doesn't match";
			}
			
			if(Data[2].equalsIgnoreCase("Blue Skies Airlines 360")){
				oDriver.click(radioSelectDepartureFlightBlueSky360);
				departureFlightCost = oDriver.getText(priceBlueSkies360);
			}else if (Data[3].equalsIgnoreCase("Blue Skies Airlines 361")){
				oDriver.click(radioSelectDepartureFlightBlueSky361);
			}else if (Data[3].equalsIgnoreCase("Pangaea Airlines 362")){
				oDriver.click(radioSelectDepartureFlightPangea362);
			}else if (Data[3].equalsIgnoreCase("Unified Airlines 363")){
				oDriver.click(radioSelectDepartureFlightUnified363);
			}
			
			if(Data[3].equalsIgnoreCase("Blue Skies Airlines 630")){
				oDriver.click(radioSelectArrivalFlightBlueSky630);
			}else if (Data[3].equalsIgnoreCase("Blue Skies Airlines 631")){
				oDriver.click(radioSelectArrivalFlightBlueSky631);
			}else if (Data[3].equalsIgnoreCase("Pangea Airlines 632")){
				oDriver.click(radioSelectArrivalFlightPangea632);
			}else if (Data[3].equalsIgnoreCase("Unified Airlines 633")){
				oDriver.click(radioSelectArrivalFlightUnified633);
			}
			
			oDriver.click(buttonContinuePage2);
			Log.info("Flights selected successfully");
			return "Flight selected successfully";
			
		} catch (Exception e) {
			Log.error("mercuryFlightBooking()->selectFlight()-> Error occured, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while selecting flight";
		}	
	}
	
	public String bookFlight(){
		try {
			if(oDriver.isVisible(headerSummary) && oDriver.getText(headerSummary).equals("Summary")){
				Log.info("mercuryFlightBooking()->bookFlight()->Now on the flight booking page");
			}else{
				Log.error("mercuryFlightBooking()->bookFlight()->Could not find Summary header");
				return "ERROR: Could not find DEPART header";
			}
			
			System.out.println("Departure Flight Cost: " + departureFlightCost);
			
			return "";
			
		} catch (Exception e) {
			Log.error("mercuryFlightBooking()->bookFlight()-> Error occured, here are the details: ");
			Log.error(e.toString());
			return "ERROR: Error occurred while booking flight";
		}
	}
	
	
}
