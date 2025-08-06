package practicebytrainer;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class WorkingWithDataProviderWithExcelFile {

    
    FileUtility fUtil = new FileUtility(); // global Decleration both for @Test & DataProvider
    @Test(dataProvider="loginDetails")
    public void login(String USERNAME, String PASSWORD ) throws IOException {
	//1. first Create objects for Generic utilities
	
	//FileUtility fUtil = new FileUtility(); // since it is global has been put before @Test 
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
    public Object[][] loginDetails() throws EncryptedDocumentException, IOException{       //**return type--> Object[][] 
	Object[][] objArr=new Object[6][2]; // creating 2 dimensional object array object for username & password 
	
	//giving user name & password with the help of excel file so we using for loop
	
	for(int i=1;i<=6;i++){
	    
	     // for username 
	    // row always starts with 0 so we using "i-1"
           // coloumn always starts with 0 so we keeping "0" as it is
	    objArr[i-1][0]=fUtil.readDataFromExcelFile("practice",i,0); 
	    
	    
	     // for password
	    // row always starts with 0 so we using "i-1"
           // coloumn for password is always 1 we keeping "1" 
	    objArr[i-1][1]=fUtil.readDataFromExcelFile("practice",i,1); ; 
	}
	return objArr;
    }

}
