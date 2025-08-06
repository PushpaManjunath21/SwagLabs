package hard_code;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddToCart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 WebDriver driver = new ChromeDriver();
		 driver.manage().window().fullscreen();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 driver.get("https://www.saucedemo.com/");
		 
		 
		 driver.findElement(By.id("user-name")).sendKeys("standard_user");
		 driver.findElement(By.id("password")).sendKeys("secret_sauce");
		 driver.findElement(By.id("login-button")).click();
		 Thread.sleep(1000);
		 
		 	//driver.findElement(By.linkText("Sauce Labs Backpack")).click();
		 	
		 	driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("add-to-cart")).click();
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("shopping_cart_container")).click();
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("checkout")).click();
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("first-name")).sendKeys("Pushpa");
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("last-name")).sendKeys("Manjunath");
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("postal-code")).sendKeys("850055");
		 	driver.findElement(By.id("continue")).click();
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("finish")).click();
		 	Thread.sleep(1000);
		 	driver.findElement(By.id("back-to-products")).click();
		 	Thread.sleep(1000);

	}

}
