package genericUtilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import sun.util.calendar.BaseCalendar.Date;

public class ListnerImplementation implements ITestListener,ISuiteListener {
    // Declearing few variables globally
    public ExtentSparkReporter spark;
    public ExtentReports report; 
    public ExtentTest test;

    //once after creating this class & implimenting listeners, 
    //do mouse right click->source->"overide,impliment methods" click
    // we will get these overide methods by selecting what methods we need

    // if we have more test cases, it's difficult to add individulay "@Listeners" in each Testcases
    // so we add it in respective Suite file itself

    @Override
    public void onStart(ISuite suite) {
	System.out.println("Execution Started--> Report configuration");

	JavaUtility jv = new JavaUtility();//creating object for "JavaUtility" of generic utilities
	String dateTime = jv.GetSystemDateInFormat().toString();

	//ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReports/report_"+dateTime+".html");//before  declearing globally
	spark = new ExtentSparkReporter("./AdvanceReports/report_"+dateTime+".html");//after declearing globally
	//./AdvanceReports--> signifies creating new folder in current directory "AdvanceReports"
	// report_"+dateTime+".html--> signifies file name as report with date & time in "html" format

	//configuraing Reports with title, name & themes 
	spark.config().setDocumentTitle("Swag Labs Test Suite Results");
	spark.config().setReportName("Sauce Demo Reort");
	spark.config().setTheme(Theme.DARK);

	//ExtentReports report= new ExtentReports();//before  declearing globally
	report= new ExtentReports();//after  declearing globally
	report.attachReporter(spark);
	report.setSystemInfo("Browser", "Chrome");
	report.setSystemInfo("OS", "Windows 11");
    }

    @Override
    public void onFinish(ISuite suite) {
	System.out.println("Execution Ended--> Report Backup");
    }


    @Override
    public void onTestStart(ITestResult result) {
	//a.add TestCaseName for clarity that which TestCase is success
	String testcasename = result.getMethod().getMethodName();//take a variable in method & use that to get TestCaseName
	//ExtentTest test= report.createTest(testcasename);//before  declearing globally
	test= report.createTest(testcasename);//after  declearing globally
	//System.out.println(testcasename+"Execution Started");// before Extent Test
	test.log(Status.INFO, testcasename+"Execution Started");// after Extent Test no "Syso", only log
    }


    @Override
    public void onTestSuccess(ITestResult result) {
	//a.add TestCaseName for clarity that which TestCase is success
	String testcasename = result.getMethod().getMethodName();//take a variable in method & use that to get TestCaseName
	//System.out.println(testcasename+"passed");// before Extent Test
	test.log(Status.PASS, testcasename+"passed");// after Extent Test no "Syso", only log
    }

    @Override
    public void onTestFailure(ITestResult result) {
	//a.add TestCaseName for clarity that which TestCase is success
	String testcasename = result.getMethod().getMethodName();//take a variable in method & use that to get TestCaseName

	//b.we would like to take screen shot of failure test case so we using TakesScreenShotAs Interface
	//TakesScreenshot ts=(TakesScreenshot)driver;//<-- downcasting 
	// we getting error for "driver",

	//which we cannot impliment driver from base file() here 
	//with extends so we creating new variable sdriver in baseclass 
	//in 33rd line & using it here

	TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver; // we made it static & calling it by classname
	//File src = ts.getScreenshotAs(OutputType.FILE);// before Extent Test
	String src = ts.getScreenshotAs(OutputType.BASE64);// after Extent Test no "File" format, only "BASE64" format
	test.addScreenCaptureFromBase64String(src);
	test.log(Status.FAIL, testcasename+"failed");// after Extent Test no "Syso", only log

	//c.adding date to screenshots// before Extent Test
	//JavaUtility jv = new JavaUtility();//creating object for "JavaUtility" of generic utilities// before Extent Test
	//String dateTime = jv.GetSystemDateInFormat().toString();// before Extent Test

	//d.we creating a new folder "ErrorScreenshots" in a current project as destination // before Extent Test
	//File dest=new File("./ErrorScreenshots/"+testcasename+dateTime+".png");// before Extent Test

	//d.we transfering File from source to destination // before Extent Test
	//org.openqa.selenium.io.FileHandler.copy(src, dest);// remove in import "import java.util.logging.FileHandler;" 
	//& rewrite "FileHandler" from selenium, not from java
	
//	try {                                     // before Extent Test
//	    FileHandler.copy(src, dest);          // before Extent Test  
//	} catch (IOException e) {                 // before Extent Test
//	    // TODO Auto-generated catch block    // before Extent Test
//	    e.printStackTrace();                  // before Extent Test
//	}

	//System.out.println(testcasename+"failed"); // before Extent Test
    }


    @Override
    public void onTestSkipped(ITestResult result) {
	//a.add TestCaseName for clarity that which TestCase is success
	String testcasename = result.getMethod().getMethodName();//take a variable in method & use that to get TestCaseName
	test.log(Status.SKIP, testcasename+"skipped");// after Extent Test no "Syso", only log
	//System.out.println(testcasename+"skipped");// before Extent Test
    }

    // to makes listeners to monitor our all test cases, after all process in "ListenerImplimentation" class 
    //we will impliment this Listeners in all testcases in the begining of RunAll with following syntax
    //------>@Listeners(genericUtilities.ListnerImplementation.class)<----
    // of 20th line, "InventoryTest"  & "ClickOnCartIcon" class

    // if we have more test cases, it's difficult to add individulay "@Listeners" in each Testcases
    // so we add it in Suite file file itself

}



