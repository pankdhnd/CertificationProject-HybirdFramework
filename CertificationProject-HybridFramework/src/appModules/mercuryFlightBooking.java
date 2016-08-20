package appModules;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;

import commonLibs.commonDriver;
import commonLibs.utils;

public class mercuryFlightBooking {

	private static By radioJourneyType = By.name("tripType");
	private static By selectPassengers = By.name("passCount");
	private static By selectDepartingFrom = By.name("fromPort");
	private static By selectOnMonth = By.name("fromMonth");
	private static By selectOnDay = By.name("fromDay");
	private static By selectArrivingOn = By.name("toPort");
	private static By selectReturningMonth = By.name("toMonth");
	private static By selectReturningDay = By.name("toDay");
	private static By selectServiceClass = By.name("servClass");
	private static By listAirline = By.name("airline");
	private static By buttonContiue = By.name("findFlights");
	private static By headerDepart = By.xpath("//b/font[contains(text(),'DEPART')]");
	private static By departText = By.xpath("//table[1]/tbody/tr[2]/td[1]/b/font");
	private static By returnText = By.xpath("//table[2]/tbody/tr/td/table/tbody/tr[2]/td/b/font");
	private static By radioSelectDepartureFlight = By.name("outFlight");
	private static By radioSelectArrivalFlight = By.name("inFlight");
	private static By buttonContinuePage2 = By.name("reserveFlights");
	private static By headerSummary = By.xpath("//b/font[contains(text(),'Summary')]");
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
	
	static commonDriver oDriver;
	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";
	private static Properties oDriverProperties;
	private static String screenshotPath;
	
	public mercuryFlightBooking(){
		  DOMConfigurator.configure("log4j.xml");
		  oDriver = new commonDriver();
		  oDriverProperties = utils.getProperties(driverPropertyFile);	
		  screenshotPath = oDriverProperties.getProperty("screenshotFolder").trim();
	}
	
	public String inputFlightDetails(){
		return "";
	}
	
	
}
