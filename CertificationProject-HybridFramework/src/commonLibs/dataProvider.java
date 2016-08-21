package commonLibs;

import java.util.Properties;
import commonLibs.excelDriver;
import utility.Log;

public class dataProvider {
	private static excelDriver oExcelDriver;	
	private static String driverPropertyFile = "D:\\selenium\\Framework\\config\\config.properties";
	private static Properties oDriverProperties;	
	private static String testDataFolder;
	
	
	
	public dataProvider(){
		oDriverProperties = utils.getProperties(driverPropertyFile);
		testDataFolder = oDriverProperties.getProperty("TestDataFolder");
		oExcelDriver = new excelDriver();
		oExcelDriver.openExcelSheet(testDataFolder+"\\TestData.xlsx");
	}
	
	public String[] openBrowser(){
		try {
			String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();		
			String Data[] = new String[3];
			
			Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);			
			Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
			Data[2] = oExcelDriver.getCellData(sheetName, 3, 2);
			
			return Data;
		} catch (Exception e) {
			Log.error("dataProvider()->openBrowser()-> Error occured while getting data: "+ e);
			return null;
		}
		
	}
	
	public String[] inputLoginDetails()
	{		
		try {
			String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
			String Data[] = new String[2];
			Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);
			Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
			
			return Data;
		} catch (Exception e) {
			Log.error("dataProvider()->inputLoginDetails()-> Error occured while getting data: "+ e);
			return null;
		}
		
	}
	
	public String[] verifyEnteredValues()
	{
		
		try {
			String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
			String Data[] = new String[2];	
			
			Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);
			Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
			
			return Data;
		} catch (Exception e) {
			Log.error("dataProvider()->verifyEnteredValues()-> Error occured while getting data: "+ e);
			return null;
		}
		
	}
	
	public String[] registerNewUser(){
		try {
			String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
			String Data[] = new String[13];
			
			Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);
			Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
			Data[2] = oExcelDriver.getCellData(sheetName, 3, 2);
			Data[3] = oExcelDriver.getCellData(sheetName, 4, 2);
			Data[4] = oExcelDriver.getCellData(sheetName, 5, 2);
			Data[5] = oExcelDriver.getCellData(sheetName, 6, 2);
			Data[6] = oExcelDriver.getCellData(sheetName, 7, 2);
			Data[7] = oExcelDriver.getCellData(sheetName, 8, 2);
			Data[8] = oExcelDriver.getCellData(sheetName, 9, 2);
			Data[9] = oExcelDriver.getCellData(sheetName, 10, 2);
			Data[10] = oExcelDriver.getCellData(sheetName, 11, 2);
			Data[11] = oExcelDriver.getCellData(sheetName, 12, 2);
			Data[12] = oExcelDriver.getCellData(sheetName, 13, 2);	
			 
			return Data;
		} catch (Exception e) {
			Log.error("dataProvider()->registerNewUser()-> Error occured while getting data: "+ e);
			return null;
		}
		
		
	}
	
	public String [] inputFlightDetails(){
		try {
			String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
			String Data[] = new String[10];
			Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);
			Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
			Data[2] = oExcelDriver.getCellData(sheetName, 3, 2);
			Data[3] = oExcelDriver.getCellData(sheetName, 4, 2);
			Data[4] = oExcelDriver.getCellData(sheetName, 5, 2);
			Data[5] = oExcelDriver.getCellData(sheetName, 6, 2);
			Data[6] = oExcelDriver.getCellData(sheetName, 7, 2);
			Data[7] = oExcelDriver.getCellData(sheetName, 8, 2);
			Data[8] = oExcelDriver.getCellData(sheetName, 9, 2);
			Data[9] = oExcelDriver.getCellData(sheetName, 10, 2);		
			
			return Data;
		} catch (Exception e) {
			Log.error("dataProvider()->inputFlightDetails()-> Error occured while getting data: "+ e);
			return null;
		}
		
	}
	
	public String[] selectFlight(){
		try {
			String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
			String Data[] = new String[4];
			
			Data[0] = oExcelDriver.getCellData(sheetName, 1, 2); 
			Data[1] = oExcelDriver.getCellData(sheetName, 2, 2); 
			Data[2] = oExcelDriver.getCellData(sheetName, 3, 2); 
			Data[3] = oExcelDriver.getCellData(sheetName, 4, 2);
			
			return Data;
		} catch (Exception e) {
			Log.error("dataProvider()->selectFlight()-> Error occured while getting data: "+ e);
			return null;
		}
	}
	
	
	public String[] bookFlight(){
		try {
			String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
			String Data[] = new String[32];
			
			Data[0] = oExcelDriver.getCellData(sheetName, 1, 2); //Departure city
			Data[1] = oExcelDriver.getCellData(sheetName, 2, 2); //Arrival city
			Data[2] = oExcelDriver.getCellData(sheetName, 3, 2); //Departure flight
			Data[3] = oExcelDriver.getCellData(sheetName, 4, 2); //Departure flight cost
			Data[4] = oExcelDriver.getCellData(sheetName, 5, 2); //Return flight
			Data[5] = oExcelDriver.getCellData(sheetName, 6, 2); //Return flight cost
			Data[6] = oExcelDriver.getCellData(sheetName, 7, 2); //Number of passengers
			Data[7] = oExcelDriver.getCellData(sheetName, 8, 2); //First Name
			Data[8] = oExcelDriver.getCellData(sheetName, 9, 2); //Last Name
			Data[9] = oExcelDriver.getCellData(sheetName, 10, 2); //Meal
			Data[10] = oExcelDriver.getCellData(sheetName, 11, 2); //Card Type
			Data[11] = oExcelDriver.getCellData(sheetName, 12, 2); //Card number
			Data[12] = oExcelDriver.getCellData(sheetName, 13, 2); //Expiry month
			Data[13] = oExcelDriver.getCellData(sheetName, 14, 2); //Expiry year
			Data[14] = oExcelDriver.getCellData(sheetName, 15, 2); //First Name
			Data[15] = oExcelDriver.getCellData(sheetName, 16, 2); //Middle Name
			Data[16] = oExcelDriver.getCellData(sheetName, 17, 2); //Last Name
			Data[17] = oExcelDriver.getCellData(sheetName, 18, 2); //TicketLess travel
			Data[18] = oExcelDriver.getCellData(sheetName, 19, 2); //Billing address1
			Data[19] = oExcelDriver.getCellData(sheetName, 20, 2); //Billing address2
			Data[20] = oExcelDriver.getCellData(sheetName, 21, 2); //City
			Data[21] = oExcelDriver.getCellData(sheetName, 22, 2); //State
			Data[22] = oExcelDriver.getCellData(sheetName, 23, 2); //Postal Code
			Data[23] = oExcelDriver.getCellData(sheetName, 24, 2); //Country
			Data[24] = oExcelDriver.getCellData(sheetName, 25, 2); //Delivery same as billing
			Data[25] = oExcelDriver.getCellData(sheetName, 26, 2); //Delivery address1
			Data[26] = oExcelDriver.getCellData(sheetName, 27, 2); //Delivery address2
			Data[27] = oExcelDriver.getCellData(sheetName, 28, 2); //City 
			Data[28] = oExcelDriver.getCellData(sheetName, 29, 2); //State
			Data[29] = oExcelDriver.getCellData(sheetName, 30, 2); //Postal Code
			Data[30] = oExcelDriver.getCellData(sheetName, 31, 2); //Country
			Data[31] = oExcelDriver.getCellData(sheetName, 32, 2); //Warning text
			
			return Data;
		} catch (Exception e) {
			Log.error("dataProvider()->bookFlight()-> Error occured while getting data: "+ e);
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
