package practicebytrainer;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class ClickOnCartIcon {
    public static void main(String[] args) throws IOException {

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
	String PRODUCTNAME = fUtil.readDataFromExcelFile("Cart", 1, 2);// Incorporating genericUtilites -> File utility 

	//4.Launch the Browser
	WebDriver driver = new EdgeDriver();
	sUtil.maximizeWindow(driver);// Incorporating genericUtilites -> Selenium utility 
	sUtil.addImplicitlywait(driver);// Incorporating genericUtilites -> Selenium utility

	//5.navigating to application (Load URL)
	driver.get(URL);
	
	//6.log in to application(object Repository from POM class) 
	LoginPage lp= new LoginPage(driver); // creating object for object repository
	lp.loginToApp(USERNAME, PASSWORD);
	
	//7.Click on a product - Sauce Labs Backpack - Dynamic Xpath --so we using  find element,instead Object Repository 
	driver.findElement(By.xpath("//div[text()='"+PRODUCTNAME+"']")).click();
	
//	//8.Adding the product to Cart - Sauce Labs Backpack 
//        //driver.findElement(By.id("add-to-cart")).click();-- use POM class & take it from respective POM class
//	InventoryItemPage addProToCart = new InventoryItemPage(driver); // creating object for object repository with respective classname
//	addProToCart.clickOnAddToCartBtn(); // taking methods from "InventoryItemPage" pom class
	
//	//9.Navigate to Cart
//        //driver.findElement(By.id("shopping_cart_container")).click();-- use POM class & take it from respective POM class
//	InventoryPage clickOnCart = new InventoryPage(driver); // creating object for object repository with respective classname
//	clickOnCart.clickOncartContainer();
	
	//10.Validate the product in Cart
        //String productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
	CartPage productInCart=new CartPage(driver);
	WebElement prdtIncart = productInCart.getPrdtName();
	
	if(prdtIncart.equals(PRODUCTNAME)) {
	    System.out.println("PASS");
	    System.out.println(prdtIncart);
	}
	else {
	    System.out.println("FAIL");
	    System.out.println(prdtIncart);
	}
	
	//11.Logout of Application
	InventoryPage logOut= new InventoryPage(driver); // creating object for object repository with respective classname
	logOut.logOutToApp();
    }
}
