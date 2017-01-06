package socialtrade.bin;

/*
 * @Author
 * Rama Mohan Reddy
 */

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	WebDriver driver = new FirefoxDriver();
	
	private String USERNAME="61828241";
	private String PASSWORD="ramamohan@143";
	
	
		
	@BeforeClass
	public void testLogin() throws Throwable{
		System.out.println("Navigating to social trade loginPage");
		driver.get("https://www.socialtrade.biz/");
		System.out.println("Login with username and password "+USERNAME+" / "+PASSWORD+"");
		driver.findElement(By.id("txtEmailID")).sendKeys(USERNAME);
		driver.findElement(By.id("txtPassword")).sendKeys(PASSWORD);
		if (driver.findElement(By.id("popup")).isDisplayed()) {
			System.out.println("Popup is displayed. So, Closing");
			driver.findElement(By.xpath("//div[@id='popup']/img[@class='close-image']")).click();
		}
		Thread.sleep(2000);
		System.out.println("Clicking 'Login' button");
		driver.findElement(By.id("CndSignIn")).click();
		System.out.println("Loin successfully ----");
		if (driver.findElement(By.id("popup")).isDisplayed()) {
			System.out.println("Popup is displayed. So, Closing");
			driver.findElement(By.xpath("//img[@class='close-image']")).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
		Assert.assertTrue(isElementPresent(By.xpath("//span[text()='Sign Out']")));
		
	}
	
	@AfterClass
	public void tearDown(){
		driver.manage().deleteAllCookies();
		driver.close();
	}

	public boolean isElementPresent(By by) {
		  try {
		    driver.findElement(by);
		    return true;
		  } catch (NoSuchElementException e) {
		    return false;
		  }
		}
}
