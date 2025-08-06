package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    // Rule 1 : create a seperate pom class for webpage
public class InventoryItemPage {

    // Rule 2 : Identify the webelement using @FindBy, @FindAll, @FindBys

    @FindBy(className  ="iinventory_details_desc large_size") private WebElement prdtDescription;
    @FindBy(className  ="inventory_details_price") private WebElement prdtPrice;
//    @FindBy(id = "add-to-cart") private WebElement addToCartBtn;
    @FindBy(id="add-to-cart") private WebElement addToCartBtn;

    // Rule 3 : Create a constructor intialisation

    public InventoryItemPage(WebDriver driver) {	
	PageFactory.initElements(driver, this);
    }
    
    // Rule 4 : Provide getters  to access the private webelements
    
    public WebElement getPrdtDescription() {
	return prdtDescription;
    }

    public WebElement getPrdtPrice() {
	return prdtPrice;
    }

    public WebElement getAddToCartBtn() {
	return addToCartBtn;
    }

    // Rule 5 : Creating Bussiness Libraries to perform some actions on webPage 
    //                   by extending it from Selenium Untality class methods, 
    //                   whwere already all selenium methods are made

    /**1- method
     * This method will click on add to cart button
     */
    public void clickOnAddToCartBtn() {
	addToCartBtn.click();
    }



}
