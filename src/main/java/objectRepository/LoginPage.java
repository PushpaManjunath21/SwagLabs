package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

        //rule 1 : create a seperate pom class for webpage
    public class LoginPage {
    	
	// Rule 2 : Identify the webelement using @FindBy, @FindAll, @FindBys
	
	@FindBy(id="user-name") private WebElement usernameTxt;
	@FindBy(name="password") private WebElement passwordTxt;
	@FindBy(name="login-button") private WebElement loginBtn;

	// Rule 3 : Create a constructor intialisation
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	// Rule 4 : Provide getters  to access the private webelements
	
	public WebElement getUsernameTxt() {
		return usernameTxt;
	}
	public WebElement getPasswordTxt() {
		return passwordTxt;
	}
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	
         // Rule 5 : Creating Bussiness Libraries to perform some actions on webPage by extending it from Selenium Untality class methods, whwere already all selenium methods are made
	
	/**1- method
	 * This method will perform login opperation f0r bussiness library of object Repository-POM class
	 * @param username
	 * @param password
	 */
	
	public void loginToApp(String username, String password) {
		usernameTxt.sendKeys(username);
		passwordTxt.sendKeys(password);
		loginBtn.click();
	}

}
