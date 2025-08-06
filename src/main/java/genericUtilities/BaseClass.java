package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class BaseClass {

    public WebDriver driver; // since it is non static method, if we create object for this in "ListenerImplimentation" class 
    // all the methods of this will be loaded in memory to avoid this, 
    // so we created new static driver variable in this class, 35th line "static WebDriver sdriver"
    public SeleniumUtility sUtil = new SeleniumUtility();
    public InventoryPage ip = new InventoryPage(driver);
    public FileUtility fUtil = new FileUtility();  //create object utilities
    public static WebDriver sdriver; //for "ListnerImplementation" class we creating new variable
    // where driver is launching, in @beforeClass, 86th line(ListnerImplementation)

    @BeforeMethod(groups = {"Smoke","Regression"})
    public void beforeMethod() throws IOException {
	System.out.println("login");


	//	//Read data from Property file
	String URL = fUtil.readDataFromPropertyFile("url"); // Incorporating genericUtilites -> File utility 
	//	String USERNAME = fUtil.readDataFromPropertyFile("username");// Incorporating genericUtilites -> File utility 
	//	String PASSWORD = fUtil.readDataFromPropertyFile("password");// Incorporating genericUtilites -> File utility

	//Read data from commond prompt- when we reading url from COMMOND PROMPT we using this codes
	//commond Prompt format to give cerdentials syntax is :- "mvn test -Durl=https://www.saucedemo.com/ test"
//	String URL = System.getProperty("url"); // Incorporating from COMMOND PROMPT  
	String USERNAME = fUtil.readDataFromPropertyFile("username");// Incorporating genericUtilites -> File utility 
	String PASSWORD = fUtil.readDataFromPropertyFile("password");// Incorporating genericUtilites -> File utility

	//Load URL
	driver.get(URL);

	//login to application
	LoginPage lp= new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);

    }

    @AfterMethod(groups = {"Smoke","Regression"})
    public void afterMethod() {
	System.out.println("Logout");
	ip.logOutToApp();
    }

    //    @Parameters("Browser") //parameter "Browser" it shoud be same as suite file
    // since we used this "<parameter name="Browser" value="chrome"></parameter>"
    // in cross browsing parallel suite file,we need to use this annotation

    @BeforeClass(groups = {"Smoke","Regression"})
    //public void beforeClass(String BROWSER) throws IOException {
    public void beforeClass() throws IOException {// since u added "Browser" in @Parameters u should add 
	//parameter for this method, the parameter should be what u used in "if" statement

	System.out.println("Launch the browser");

	//a.driver= new EdgeDriver(); // we taking it from "common data" file so commented

	String BROWSER = fUtil.readDataFromPropertyFile("browser");// since we doing 
	//"CROSS BROWSER" Parallel execution we commented this & adding "@parameters" annotation in 59th line 

	if(BROWSER.equalsIgnoreCase("Edge")) 
	    driver= new EdgeDriver();
	else if(BROWSER.equalsIgnoreCase("Chrome"))
	    driver= new ChromeDriver();
	else if(BROWSER.equalsIgnoreCase("FireFox"))
	    driver= new FirefoxDriver();
	else if(BROWSER.equalsIgnoreCase("Safari"))
	    driver= new SafariDriver();

	sdriver=driver;

	sUtil.maximizeWindow(driver);// Incorporating genericUtilites -> Selenium utility 
	sUtil.addImplicitlywait(driver);// Incorporating genericUtilites -> Selenium utilityriver = new EdgeDriver();
    }

    @AfterClass(groups = {"Smoke","Regression"})
    public void afteerClass() {
	System.out.println("Close the browser");
	driver.quit();
    }

    @BeforeTest(groups = {"Smoke","Regression"})
    public void beforeTest() {
	System.out.println("Pre-Conditions for parallel executions");
    }

    @AfterTest(groups = {"Smoke","Regression"})
    public void afterTest() {
	System.out.println("Pre-Conditions for parallel executions");

    }

    @BeforeSuite(groups = {"Smoke","Regression"})
    public void beforeSuite() {
	System.out.println("Connect to DB");
    }

    @AfterSuite(groups = {"Smoke","Regression"})
    public void afterSuite() {
	System.out.println("Disconnect to DB");
    }

}
