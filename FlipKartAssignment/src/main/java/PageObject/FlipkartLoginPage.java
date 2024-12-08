package PageObject;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FlipkartLoginPage{
	WebDriver driver;
	public WebElement LoginLink = driver.findElement(By.xpath("//a[text()='Login']"));
	public WebElement Fusername = driver.findElement(By.xpath("//input[@type='text' and @class ='r4vIwl BV+Dqf' ]"));
	///private WebElement ReqOTP = driver.findElement(By.xpath("//button[@class='QqFHMw twnTnD _7Pd1Fp']"));
	
	public FlipkartLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	

	
	public void loginWithValidCredentials(LinkedHashMap<String , String> data) {
		
		
	}
	
	
public void FlipkartLogin() {
	LoginLink.click();
	
}
}
