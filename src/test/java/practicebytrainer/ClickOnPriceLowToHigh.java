package practicebytrainer;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class ClickOnPriceLowToHigh {

    public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	// 1.first Create objects for Generic utilities
	FileUtility fUtil = new FileUtility();
	SeleniumUtility  sUtil = new SeleniumUtility();

	// 2.Reading data from property file
	// Incorporating genericUtilites -> File utility -> 1. where freshers can understand file with out a person help ,
	//                                                  2. no any syntax of java/ selenium is required 
	//                                                  3. code optimization achieved 
	String URL = fUtil.readDataFromPropertyFile("url"); // Incorporating genericUtilites -> File utility 
	String USERNAME = fUtil.readDataFromPropertyFile("username");// Incorporating genericUtilites -> File utility 
	String PASSWORD = fUtil.readDataFromPropertyFile("password");// Incorporating genericUtilites -> File utility
	
	// 3.Reading data from excel file
	String PRODUCTNAME = fUtil.readDataFromExcelFile("PriceLowToHigh", 1, 2);// Incorporating genericUtilites -> File utility 

	//4.Launch the Browser
	WebDriver driver = new EdgeDriver();
	sUtil.maximizeWindow(driver);// Incorporating genericUtilites -> Selenium utility -> /**1
	sUtil.addImplicitlywait(driver);// Incorporating genericUtilites -> Selenium utility -> /**4 

	//5.navigating to application (Load URL)
	driver.get(URL);// Incorporating genericUtilites -> File utility -> CommonData.properties 

	//6.log in to application(object Repository from POM class) 
	LoginPage lp= new LoginPage(driver); // creating object for object repository
	lp.loginToApp(USERNAME, PASSWORD); //Incorporating POM class- "LoginPage" Bussiness Library
	
	//7.Click on DropDown button for visible Text -> "Price (low to high)" option
	String VISIBLETEXT = fUtil.readDataFromExcelFile("PriceLowToHigh", 1, 3);// Incorporating genericUtilites -> File utility 
	InventoryPage selectLowToHigh = new InventoryPage(driver);// Incorporating genericUtilites -> Selenium utility -> /**8 
	sUtil.handleDropDown(selectLowToHigh.getsortDropDown(), VISIBLETEXT);

	//8.Click on Add to cart button of first element 
	InventoryPage addtocartbutton = new InventoryPage(driver);
	addtocartbutton.addToCartBtn();
	
	//9.Navigate to Cart    
	InventoryPage clickOnCart = new InventoryPage(driver); // creating object for object repository with respective classname
	clickOnCart.clickOncartContainer();
	
	//10.Validate the product in Cart
	CartPage productInCart=new CartPage(driver);
	String prdtnameincart = productInCart.gettingProductName();
	
	if(prdtnameincart.equals(PRODUCTNAME)) {
	    System.out.println("Test case is PASS");
	    System.out.println("The Available product in cart is :" +prdtnameincart);
	}
	else {
	    System.out.println("Test case is FAIL");
	    System.out.println("The Available product in cart is :" +PRODUCTNAME);
	}
	
	//11.Logout of Application
	InventoryPage logOut= new InventoryPage(driver); // creating object for object repository with respective classname
	logOut.logOutToApp();
	
    }

}
