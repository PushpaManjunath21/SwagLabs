package cartTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;
@Listeners(genericUtilities.ListnerImplementation.class) // to makes listeners to monitor our all test cases
public class ClickOnCartIconTest extends BaseClass {
    @Test (groups = {"Smoke","Regression"})
    public void TC_001_ClickOnCartIconTest() throws IOException

    {
	// 3.Reading data from excel file
	String PRODUCTNAME = fUtil.readDataFromExcelFile("Cart", 1, 2);// Incorporating genericUtilites -> File utility 

	//7.Click on a product - Sauce Labs Backpack - Dynamic Xpath --so we using  find element,instead Object Repository 
	driver.findElement(By.xpath("//div[text()='"+PRODUCTNAME+"']")).click();

	//8.Adding the product to Cart - Sauce Labs Backpack 
	//driver.findElement(By.id("add-to-cart")).click();-- use POM class & take it from respective POM class
	InventoryItemPage addProToCart = new InventoryItemPage(driver); // creating object for object repository with respective classname
	addProToCart.clickOnAddToCartBtn(); // taking methods from "InventoryItemPage" pom class

	//9.Navigate to Cart
	//driver.findElement(By.id("shopping_cart_container")).click();-- use POM class & take it from respective POM class
	InventoryPage clickOnCart = new InventoryPage(driver); // creating object for object repository with respective classname
	clickOnCart.clickOncartContainer();

	//10.Validate the product in Cart
	//String productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
	CartPage productInCart=new CartPage(driver);
	WebElement prdtIncart = productInCart.getPrdtName();

//	if(prdtIncart.equals(PRODUCTNAME)) {
//	    System.out.println("PASS");
//	    System.out.println(prdtIncart);
//	}
//	else {
//	    System.out.println("FAIL");
//	    System.out.println(prdtIncart);
//	}
	
	// since we using Assert Concept here we commented above line for validation
	
	//1.HardAssert
	Assert.assertEquals(prdtIncart, PRODUCTNAME);
    }
}
