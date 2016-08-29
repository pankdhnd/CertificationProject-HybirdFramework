package commonLibs;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Extent {
	static ExtentReports Report;
	static ExtentTest Logger;

	public Extent() {

	}

	public static String startReporting() {
		String ReportFileName = utils.getProperty("reportsFolder") + "TestReport_" + utils.getDateTimeStamp() + ".html";
		Report = new ExtentReports(ReportFileName);
		return ReportFileName;
	}

	public static void startTest(String testName, String Description) {
		Logger = Report.startTest(testName, Description);
	}

	public static void logInfo(String stepName, String details) {
		Logger.log(LogStatus.INFO, stepName, details);
	}

	public static void passTestCase(String Message) {
		Logger.log(LogStatus.PASS, Message);
	}

	public static void failTestCase(String Message) {
		Logger.log(LogStatus.FAIL, Message);
	}

	public static void skipTestCase(String Message) {
		Logger.log(LogStatus.SKIP, Message);
	}

	public static void logError(String stepName, String details) {
		Logger.log(LogStatus.ERROR, stepName, details);
	}

	public static void logFatel(String stepName, String details) {
		Logger.log(LogStatus.FATAL, stepName, details);
	}

	public static void endTestCase() {
		Report.endTest(Logger);
	}

	public static void flushExtent() {
		Report.flush();
	}

}
