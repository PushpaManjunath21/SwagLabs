package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

     /**
     * This class consists of reusable methods related to Selenium
     * @author manju
     * 
     */

     public class SeleniumUtility {

	/**1
	 * This method will maximize the window
	 * @author manju
	 */

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}


	/**2
	 * This method will minimize the window
	 * @author manju
	 */
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}


	/**3
	 * This method will fullscreen the window
	 * @author manju
	 */
	public void fullscreenWindow(WebDriver driver) {
		driver.manage().window().fullscreen();
	}


	/**4
	 * This method will implicitly wait for 10 seconds
	 * @param driver
	 */
	public void addImplicitlywait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}


	/**5
	 * This method will wait for an element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {

		WebDriverWait wait= new WebDriverWait(driver ,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**6
	 * This method will wait for an element to be clickable
	 * @param driver
	 * @param element
	 */
	public void elementToBeClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**7
	 * This method will handle drop & down by index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index) {

		Select s= new Select(element);
		s.selectByIndex(index);

	}

	/**8
	 * * This method will handle drop & down by visibletext
	 * @param element
	 * @param visibleText
	 */
	public void handleDropDown(WebElement element, String visibleText) {

		Select s= new Select(element);
		s.selectByVisibleText(visibleText);

	}

	/**9
	 * This method will handle drop & down by value
	 * @param element
	 * @param value
	 */
	public void handleDropDown1(WebElement element, String value) {

		Select s= new Select(element);
		s.selectByValue(value);
	}

	/**10
	 * This method will perform mouse hovering action
	 * @param driver
	 * @param element
	 */
	public void mouseHoveringAction(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);
		act.moveToElement(element).perform();

	}

	/**11
	 * This method will perform mouse double click  action
	 * @param driver
	 * @param element
	 */
	public void mouseDoubleClickAction(WebDriver driver, WebElement element) {

		Actions act= new Actions(driver);
		act.doubleClick(element).perform();

	}

	/**12
	 * This method will perform mouse right click action
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);
		act.contextClick(element).perform();

	}

	/**13
	 * this method will perform mouse click & hold action
	 * @param driver
	 * @param element
	 */
	public void clickAndHoldAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();

	}

	/**14
	 * this method ´will perform mouse release action
	 * @param driver
	 * @param element
	 */
	public void releaseAction(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);
		act.release(element).perform();
	}

	/**15
	 * this method will perform mouse scroll action
	 * @param driver
	 * @param element
	 */
	public void scrollToElement(WebDriver driver, WebElement element) {
		Actions act= new Actions(driver);
		act.scrollToElement(element).perform();
	}
        
	/**16
	 * This method will handle Frame by Name or ID
	 * @param driver
	 * @param frameNameOrID
	 */
	public void handleFrrame(WebDriver driver, String frameNameOrID) {
        driver.switchTo().frame(frameNameOrID);
	}

	/**17
	 * This method will handle Frame by Index
	 * @param driver
	 * @param frameIndex
	 */
	public void handleFrame(WebDriver driver, int frameindex) {
        driver.switchTo().frame(frameindex);
	}
	
	/**18
	 * This method will handle Frame by Frame element
	 * @param driver
	 * @param frameElement
	 */
	public void handleFrame(WebDriver driver, String frameElement) {
        driver.switchTo().frame(frameElement);
	}

	/**19
	 *  this method will perform will accept alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**20
	 * this method will perform will cancel alert popup
	 * @param driver
	 */

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**21
	 * this method will capture  alert text  And return to caller
	 * @param driver
	 * @return Alert Text
	 */
	public String getAlertText(WebDriver driver){
		return driver.switchTo().alert().getText();

	}
	/**22
	 * this method will enter data in to alert popup
	 * @param driver
	 * @param data
	 */
	public void enterTextToAlert(WebDriver driver, String data) {
		driver.switchTo().alert().sendKeys(data);
	}
	/**23
	 * This method will capture Screenshot and return the absolute path to caller
	 * @param driver
	 * @param screenshotname
	 * @return absolute path 
	 * @throws IOException
	 */
	public String captureScreenshot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(".\\Screenshots\\"+screenshotname+".png");
		FileHandler.copy(src, destination); // import it from "selenium.io"

		return destination.getAbsolutePath(); // for external reports- its 3rd part tool , since its only aware of absolute path(complete path) , we using this method 
	}
}





