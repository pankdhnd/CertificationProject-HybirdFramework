package frameworkDriver;
//import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import commonLibs.Extent;
//import commonLibs.Log;
import commonLibs.excelDriver;
import commonLibs.keywordUtility;
import commonLibs.utils;;
public class automationEngine {

	private static keywordUtility kUtil;
	private static String testSuiteFolder;	
	private static String resultFolder;
	private static String TestSuite;	
	private static excelDriver oExcelDriver;
	private static String currentTestCaseStatus;
	
	
	public static void main(String[] args) {
		
		DOMConfigurator.configure("log4j.xml");	
		testSuiteFolder =utils.getProperty("TestSuiteFolder");
		TestSuite =utils.getProperty("TestSuite");
		resultFolder =utils.getProperty("resultFolder");		
		System.out.println("****************STARTING EXECUTION******************");
		Extent.startReporting();
		testSuiteDriver();
		exportToExcel();
		System.out.println("");
		System.out.println("****************FINISHED EXECUTION******************");
		Extent.flushExtent();
		}

	
	private static void testSuiteDriver(){
		String testCaseSheetName, runFlag, runStatus, Comment, excelFile;
		int Row, rowCount;
		
		excelFile = testSuiteFolder  + "\\" + TestSuite;
		oExcelDriver = new excelDriver();
		oExcelDriver.openExcelSheet(excelFile);		
		rowCount = oExcelDriver.getRowCount("TestSuite");	
		
		for (Row = 2;Row <= rowCount; Row++ ){
			testCaseSheetName = "";
			runFlag = "";
			runStatus = "";
			Comment = "";
			currentTestCaseStatus = "PASS";				
			testCaseSheetName = oExcelDriver.getCellData("TestSuite", Row, 2);				
			runFlag = oExcelDriver.getCellData("TestSuite", Row, 3);			
			testCaseSheetName = testCaseSheetName.trim();			
			runFlag = runFlag.toLowerCase().trim();				
			if(runFlag.equalsIgnoreCase(("Y"))) {
				kUtil = null;
				System.out.println("");
				System.out.println("----------------------------------------------------");
				Extent.startTest(testCaseSheetName, "");
				System.out.println("Starting Test Case >> " + testCaseSheetName);
				//Log.startOfTestCase(testCaseSheetName);
				runStatus = testCaseDriver(testCaseSheetName);				
				if (runStatus.equals("")){					
					if (currentTestCaseStatus.equals("PASS")){
					runStatus = "PASS";
					Extent.passTestCase("Passed test case: "+testCaseSheetName);
					}
					else{
						currentTestCaseStatus = "FAIL";
						Comment = "Failure occured";
						Extent.failTestCase("Failed test case: "+testCaseSheetName);
					}										
				}
				else{
					Extent.failTestCase("Failed test case: "+testCaseSheetName);
					Comment = runStatus;
					runStatus = "FAIL";
				}
				Extent.endTestCase();
				//Log.endOfTestCase();
			}
			else
			{				
				runStatus = "Skipped";
				Comment = "Run Flag is set to "+ runFlag;
			}
			oExcelDriver.setCellData("TestSuite", runStatus, Row, 4);
			oExcelDriver.setCellData("TestSuite", Comment, Row, 5);				
		}
		
			
	}//END  testSuiteDriver
//--------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------
	public static String testCaseDriver(String sheetName){
		int Row, rowCount;
		
		String testCaseDriverReturnValue = "";
		String methodName = "";		
		String runStatus = "";
		String Comment = "";
		String returnValue = "";
		
		try {			
			kUtil = new keywordUtility();	
			rowCount = oExcelDriver.getRowCount(sheetName);			
			for(Row = 2; Row <= rowCount; Row++){
			methodName = oExcelDriver.getCellData(sheetName, Row, 2);			
			System.out.println("    --Executing method >> "+ methodName);
			if (methodName.equals("")){
				runStatus = "Skipped";
				Comment = "Invalid action keyword";
			}
			else
			{				
				try {
					returnValue = kUtil.runMethod(methodName);
									
					if (returnValue.contains("ERROR")){
						Comment = returnValue;
						runStatus = "FAIL";						
						testCaseDriverReturnValue = "FAIL: Failure occured in method: "+methodName;
						currentTestCaseStatus = "FAIL";						
					}
					else{
						runStatus = "PASS";
						currentTestCaseStatus = "PASS";
						Comment = returnValue;												
					}	
					
				} catch (Exception e) {
					Comment = e.toString();
					runStatus = "EXCEPTION";
					currentTestCaseStatus = "FAIL: "+e.getMessage();
					Extent.logFatel("testCaseDriver", "EXCEPTION: "+ Comment);					
				}
			
			oExcelDriver.setCellData(sheetName, runStatus, Row, 3);
			oExcelDriver.setCellData(sheetName, Comment, Row, 4);
			oExcelDriver.setCellData(sheetName, returnValue, Row, 5);			
			}
			
			}
			
		} catch (Exception e) {
			testCaseDriverReturnValue = e.getMessage();
			currentTestCaseStatus = "FAIL";
		}
		System.out.println("----------------------------------------------------");
		return testCaseDriverReturnValue;
	}//END testCaseDriver
	
		
	private static void exportToExcel()
	{
		String outputFileName = "";
		String timeStamp = utils.getDateTimeStamp();
		
		outputFileName = resultFolder + "\\" + "Result_"+timeStamp+".xlsx";
		oExcelDriver.saveAs(outputFileName);
	}//END exportToExcel
	
	
	
}//END CLASS AUTOMATIONENGINE
