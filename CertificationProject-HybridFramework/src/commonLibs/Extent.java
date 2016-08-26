package commonLibs;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Extent {
	static ExtentReports Report;
	static ExtentTest Logger;
	
	public Extent(){
				
	}
	
	public static void startReporting(){
		Report = new ExtentReports("D:\\selenium\\report_"+utils.getDateTimeStamp()+".html");
	}
	
	public static void startTest(String testName, String Description){
		 Logger = Report.startTest(testName,Description);
	}
	
	public static void logInfo(String Message){
		Logger.log(LogStatus.INFO, Message);
	}
	
	public static void passTestCase(String Message){
		Logger.log(LogStatus.PASS, Message);
	}
	
	public static void failTestCase(String Message){
		Logger.log(LogStatus.FAIL, Message);
	}
	
	public static void skipTestCase(String Message){
		Logger.log(LogStatus.SKIP, Message);
	}
	
	public static void logError(String Message){
		Logger.log(LogStatus.ERROR, Message);
	}
	
	public static void endTestCase(){
		  Report.endTest(Logger);
	}
	
	public static void flushExtent(){
		Report.flush();
	}
	
	
}
