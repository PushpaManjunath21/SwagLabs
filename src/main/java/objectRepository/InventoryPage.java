package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.SeleniumUtility;

        // Rule 1 : create a seperate pom class for webpage
public class InventoryPage extends SeleniumUtility{

	// Rule 2 : Identify the webelement using @FindBy, @FindAll, @FindBys

	@FindBy(linkText = "Sauce Labs Backpack") private WebElement productName; //@FindBy is not for Dynamic WebElements, so we selecting here particular product to identify element
	@FindBy(xpath = "//select[@data-test='product-sort-container']") private WebElement sortDropDown;// inspect for only dropdown button
	@FindBy(id="shopping_cart_container") private WebElement cartContainer;
	@FindBy(xpath= "//button[text()= 'Open Menu']") private WebElement menuBtn;
	@FindBy(id= "logout_sidebar_link") private WebElement logOutLink;
	@FindBy(id="add-to-cart-sauce-labs-onesie") private WebElement addToCartbtn;
	//@FindBy(id="item_2_title_link")private WebElement prodtInCartLowToHigh;//7

	// Rule 3 : Create a constructor intialisation
	
	public InventoryPage(WebDriver driver){
		PageFactory.initElements(driver, this);		
	}

	// Rule 4 : Provide getters  to access the private webelements
	
	       //6
	public WebElement getAddToCartbtn() {
	    return addToCartbtn;
	}
//	      //7
//              public WebElement getProdtInCartLowToHigh() {
//	    return prodtInCartLowToHigh;
//	}

	      //1
	public WebElement getProductName() {
		return productName;
	}
	      //2
	public WebElement getsortDropDown() {
		return sortDropDown;
	}
	      //3
	public WebElement getCartContainer() {
		return cartContainer;
	}
	      //4
	public WebElement getMenuBtn() {
		return menuBtn;
	}
	      //5
	public WebElement getLogOutLink() {
		return logOutLink;
	}

	//  Rule 5 : Creating Bussiness Libraries to perform some actions on webPage by extending it from Selenium Untality class methods,
	//	                                                   where already all selenium methods are made
	
	/**1- method
	 * This method will perform logout operation
	 */
	public void logOutToApp() {
		menuBtn.click();
		logOutLink.click();
	}

	/**2- method
	 * This method will perform click on a product
	 * @param driver
	 * @param productName
	 */
	public void clickOnProduct(WebDriver driver, String productName ) {
		driver.findElement(By.xpath("//div[.='"+productName+"']")).click();
	}
	
	/**3- method
	 * This method will click on cart container button
	 */
	public void clickOncartContainer() {
		cartContainer.click();
	}
	
	/**4- method
	 * This method will sort the product for lowest price and click on the product
	 * @param driver
	 * @param sortoption
	 * @param produtname
	 */
	public void clickOnlowestPriceProduct(WebDriver driver, String sortoption, String produtname) {
		
		// (Inheritance) extended from Selenium Utility class to use methods available in that class instead creating again here 
		handleDropDown(sortDropDown, sortoption);   // /**8
		driver.findElement(By.xpath("//div[.='"+productName+"']"));
		
	}
	
	/**5- method
	 * This method will help to click on add to cart button
	 * 
	 */
	
	public void addToCartBtn() {
	    addToCartbtn.click();
	}
	    
}
