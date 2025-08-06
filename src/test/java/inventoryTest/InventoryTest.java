package inventoryTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
import objectRepository.InventoryPage;
import objectRepository.LoginPage;
@Listeners(genericUtilities.ListnerImplementation.class) // to makes listeners to monitor our all test cases
public class InventoryTest extends BaseClass{
    
    //In this "InventoryTest" class we combined both "AddToCart" & "ClickOnPriceLowToHigh" classes for TestNG,
    //Where TestNG is 
    @Test(groups = "Smoke")
    public void TC_003_ClickOnPriceLowToHighTest() throws IOException
    {
	
	// 3.Reading data from excel file
	// rows & coulmn will start from index "0"
	String PRODUCTNAME = fUtil.readDataFromExcelFile("PriceLowToHigh", 1, 2);// Incorporating genericUtilites -> File utility 

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
	
//	if(prdtnameincart.equals(PRODUCTNAME)) {
//	    System.out.println("Test case is PASS");
//	    System.out.println("The Available product in cart is :" +prdtnameincart);
//	}
//	else {
//	    System.out.println("Test case is FAIL");
//	    System.out.println("The Available product in cart is :" +PRODUCTNAME);
//	}
	
	// since we using Assert Concept here we commented above line for validation
	
	//1.HardAssert
	Assert.assertEquals(prdtnameincart,PRODUCTNAME,"Both Are Not Equal");
	
	// "Both Are Not Equal" will execute when the condition in false
	
    }
    
    @Test(groups = {"Smoke","Regression"}) 
    public void TC_002_AddToCartTest() throws EncryptedDocumentException, IOException
    {

	// b.Incorporating genericUtilites -> File utility -> 1. where freshers can understand file with out a person help ,
	//                                                  2. no any syntax of java/ selenium is required 
	//                                                  3. code optimization achieved 
	String PRODUCTNAME = fUtil.readDataFromExcelFile("Inventory", 1, 2);// Incorporating genericUtilites -> File utility -> SwagLabTestData.xlsx 

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Click on a product - bike light - Dynamic Xpath
	driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Add the product to Cart
	driver.findElement(By.name("add-to-cart")).click();

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Navigate to Cart
	driver.findElement(By.id("shopping_cart_container")).click();

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

	//Validate the product in Cart
	String productInCart = driver.findElement(By.className("inventory_item_name")).getText();
//	if(productInCart.equals(PRODUCTNAME)) // product variable should be given
//	{
//	    System.out.println("PASS");
//	    System.out.println(productInCart);
//	}
//	else
//	{
//	    System.out.println("FAIL");
//	    System.out.println(productInCart);
//	}
	
	// since we using Assert Concept here we commented above line for validation
	
	//1.HardAssert
	Assert.assertNotEquals(productInCart,PRODUCTNAME,"Both Are Not Equal");
	
	// "Both Are Not Equal" will execute when the condition in false
    }
}
