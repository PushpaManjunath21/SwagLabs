package practicebytrainer;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class WorkingWithDataProvider {

    @Test(dataProvider="loginDetails")
    public void login(String USERNAME, String PASSWORD ) throws IOException {
	//1. first Create objects for Generic utilities
	FileUtility fUtil = new FileUtility();
	SeleniumUtility  sUtil = new SeleniumUtility();

	// 2.Reading data from property file
	String URL = fUtil.readDataFromPropertyFile("url"); // Incorporating genericUtilites -> File utility

	// since we taking from dataprovider we commenting below line
	//	String USERNAME = fUtil.readDataFromPropertyFile("username");// Incorporating genericUtilites -> File utility 
	//	String PASSWORD = fUtil.readDataFromPropertyFile("password");// Incorporating genericUtilites -> File utility

	//3.Launch the Browser
	WebDriver driver = new EdgeDriver();
	sUtil.maximizeWindow(driver);// Incorporating genericUtilites -> Selenium utility 
	sUtil.addImplicitlywait(driver);// Incorporating genericUtilites -> Selenium utility

	//4.navigating to application (Load URL)
	driver.get(URL);

	//5.log in to application(object Repository from POM class) 
	LoginPage lp= new LoginPage(driver); // creating object for object repository
	lp.loginToApp(USERNAME, PASSWORD);

	//6.log out from application(object Repository from POM class) 
	InventoryPage logOut= new InventoryPage(driver);
	logOut.logOutToApp();
	driver.quit();
    }

    @DataProvider
    public Object[][] loginDetails(){       //**note here return type is "Object[][] " 
	Object[][] objArr=new Object[6][2]; // creating 2 dimensional object array object for
	//giving user name & password
	objArr[0][0]="standard_user";        //1 st trail user name
	objArr[0][1]="secret_sauce";         //1 st trail  password
	objArr[1][0]="locked_out_user";//2 nd trail  user name
	objArr[1][1]="secret_sauce";//2 nd trail password
	objArr[2][0]="problem_user";//3 rd trail user name
	objArr[2][1]="secret_sauce";//3 rd trail password
	objArr[3][0]="performance_glitch_user";//4 th trail user name
	objArr[3][1]="secret_sauce";//4 th trail password
	objArr[4][0]="error_user";//5 th trail user name
	objArr[4][1]="secret_sauce";//5 th trail password
	objArr[5][0]="visual_user";//6 th trail user name
	objArr[5][1]="secret_sauce";//6 th trail password

	return objArr;

    }

}
