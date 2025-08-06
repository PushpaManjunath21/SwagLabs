package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Rule 1 : create a seperate pom class for webpage
public class CartPage {

    // Rule 2 : Identify the webelement using @FindBy, @FindAll, @FindBys

    @FindBy(id="item_2_title_link") private WebElement prdtName;
    @FindBy(id="checkout") private WebElement checkOut;
    @FindBy(id="continue-shopping") private WebElement continueShopping;
    @FindBy(id="remove-sauce-labs-backpack") private WebElement removeBtn;
    @FindBy(id="item_2_title_link")private WebElement prodtInCartLowToHigh; 

    // Rule 3 : Create a constructor intialisation

    public CartPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
    }

    // Rule 4 : Provide getters  to access the private webelements

    public WebElement getPrdtName() {
	return prdtName;
    }

    public WebElement getCheckOut() {
	return checkOut;
    }

    public WebElement getContinueShopping() {
	return continueShopping;
    }

    public WebElement getRemoveBtn() {
	return removeBtn;
    }
    //7
    public WebElement getProdtInCartLowToHigh() {
	return prodtInCartLowToHigh;
    }

    //  Rule 5 : Creating Bussiness Libraries to perform some actions on webPage 
    //	             by extending it from Selenium Untality class methods, 
    //	             whwere already all selenium methods are made
    
    /**1- method
     * This method helps to get name of product added to cart (for validation purpose)
     * @param driver
     * @param prdtName
     */
    public String gettingProductName() {
	return prodtInCartLowToHigh.getText();
    }
}
