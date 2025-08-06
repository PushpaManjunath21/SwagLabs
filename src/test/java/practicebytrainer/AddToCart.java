package practicebytrainer;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.LoginPage;

public class AddToCart {

    public static void main(String[] args) throws IOException, InterruptedException {

	//1.first Create objects for Generic utilities
	FileUtility fUtil = new FileUtility();
	SeleniumUtility  sUtil = new SeleniumUtility();


	// a.Reading data from property file // before genericUtilites -> File utility created
	//FileInputStream fis = new FileInputStream(".\\src\\main\\resources\\CommonData.properties");
	//Properties p= new Properties();
	//p.load(fis);
	//String URL= p.getProperty("url");
	//String USERNAME = p.getProperty("username");
	//String PASSWORD = p.getProperty("password");

	// b.Incorporating genericUtilites ->File utility-> 1. where freshers can understand file with out a person help ,
	// after genericUtilites -> File utility created    2. no any syntax of java/ selenium is required 
	//                                                  3. code optimization achieved 
	String URL = fUtil.readDataFromPropertyFile("url"); // Incorporating genericUtilites -> File utility -> CommonData.properties 
	String USERNAME = fUtil.readDataFromPropertyFile("username");// Incorporating genericUtilites -> File utility -> CommonData.properties
	String PASSWORD = fUtil.readDataFromPropertyFile("password");// Incorporating genericUtilites -> File utility -> CommonData.properties

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	// a.Reading data from excel file // before genericUtilites -> File utility created
	//FileInputStream fise= new FileInputStream("src\\test\\resources\\SwagLabTestData.xlsx");// path should copied from src/test/resources folder of selenium
	//Workbook wb = WorkbookFactory.create(fise);
	//Sheet sh = wb.getSheet("Inventory");
	//Row rw = sh.getRow(1);
	//Cell cl = rw.getCell(2);
	//String PRODUCTNAME = cl.getStringCellValue();

	// b.Incorporating genericUtilites -> File utility -> 1. where freshers can understand file with out a person help ,
	// after genericUtilites -> File utility created      2. no any syntax of java/ selenium is required 
	//                                                    3. code optimization achieved 
	String PRODUCTNAME = fUtil.readDataFromExcelFile("Inventory", 1, 2);// Incorporating genericUtilites -> File utility -> SwagLabTestData.xlsx 

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//a.Launching the Browser
	WebDriver driver = new EdgeDriver();
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//a.Maximize the screen // before genericUtilites -> File utility created
	//driver.manage().window().maximize();

	//b.Incorporating genericUtilites -> Selenium utility -> 1. where freshers can understand file with out a person help ,
	//  after genericUtilites -> File utility created        2. no any syntax of java/ selenium is required 
	//                                                       3. code optimization achieved 
	sUtil.maximizeWindow(driver);// Incorporating genericUtilites -> Selenium utility -> /**1
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//a.Implicity Wait // before genericUtilites -> File utility created
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	//b.Incorporating genericUtilites -> Selenium utility -> 1. where freshers can understand file with out a person help ,
	// after genericUtilites -> File utility created        2. no any syntax of java/ selenium is required 
	//                                                      3. code optimization achieved 
	sUtil.addImplicitlywait(driver);// Incorporating genericUtilites -> Selenium utility -> /**4

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//a.Navigating to URL // before genericUtilites -> File utility created
	// driver.get(""https://www.saucedemo.com/"");
	
	//b.Incorporating genericUtilites -> Selenium utility-> 1. where freshers can understand file with out a person help ,
	// after genericUtilites -> File utility created        2. no any syntax of java/ selenium is required 
	//                                                      3. code optimization achieved 
	driver.get(URL); // Incorporating genericUtilites -> File utility -> CommonData.properties 
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//a.Login to Application // before genericUtilites -> File utility created
	//driver.findElement(By.id("user-name")).sendKeys(USERNAME);
	//driver.findElement(By.id("password")).sendKeys(PASSWORD);
	//driver.findElement(By.name("login-button")).click();

	//b.object Repository from POM class// after objectRepository -> LoginPage Pom Class 
	LoginPage lp= new LoginPage(driver); // creating object for object repository

	//getters method
	//lp.getUsernameTxt().sendKeys(USERNAME);
	//lp.getPasswordTxt().sendKeys(PASSWORD);
	//lp.getLoginBtn().click();// why commented becasue of bussiness library we using down

	//c.This method will perform login opperation from bussiness library 
	//           created in object Repository->LoginPage Pom Class
	lp.loginToApp(USERNAME, PASSWORD); //Incorporating POM class- "LoginPage" Bussiness Libraries

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Click on a product - bike light - Dynamic Xpath
	
	driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();
       Thread.sleep(Duration.ofSeconds(2000));
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Add the product to Cart
	driver.findElement(By.name("add-to-cart")).click();
	//Thread.sleep(Duration.ofSeconds(2000));
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Navigate to Cart
	driver.findElement(By.id("shopping_cart_container")).click();
	//Thread.sleep(Duration.ofSeconds(2000));
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Validate the product in Cart
	String productInCart = driver.findElement(By.className("inventory_item_name")).getText();
	if(productInCart.equals(PRODUCTNAME)) // product variable should be given
	{
	    System.out.println("PASS");
	    System.out.println(productInCart);
	}
	else
	{
	    System.out.println("FAIL");
	    System.out.println(productInCart);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Logout of Application
	driver.findElement(By.id("react-burger-menu-btn")).click();
//	Thread.sleep(Duration.ofSeconds(2000));
	driver.findElement(By.linkText("Logout")).click();
//	Thread.sleep(Duration.ofSeconds(2000));


    }
}
