package commonLibs;

import java.util.Properties;
import commonLibs.excelDriver;

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
		String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();		
		String Data[] = new String[3];
		
		Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);			
		Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
		Data[2] = oExcelDriver.getCellData(sheetName, 3, 2);
		
		return Data;
	}
	
	public String[] inputLoginDetails()
	{
		String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
		String Data[] = new String[2];
		Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);
		Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
		
		return Data;
	}
	
	public String[] verifyEnteredValues()
	{
		String sheetName =  Thread.currentThread().getStackTrace()[1].getMethodName();
		String Data[] = new String[2];	
		
		Data[0] = oExcelDriver.getCellData(sheetName, 1, 2);
		Data[1] = oExcelDriver.getCellData(sheetName, 2, 2);
		
		return Data;
	}
	
	public String[] registerNewUser(){
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
	}
	
	public String [] inputFlightDetails(){
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
	}
}
