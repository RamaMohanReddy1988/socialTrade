package socialtrade.bin;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TodayTaskTest extends BaseClass{

	@Test
	public void testStartWorking() throws Exception{
		driver.findElement(By.xpath("//a[text()='View Advertisements']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(isElementPresent(By.id("paidclicks")));
		Assert.assertTrue(isElementPresent(By.xpath("//span[@title='Click Task']")));
		clickingLinks();
		String todayPoints=getTodayCompletedTaskPoints();
		
		
	}
	
	public void clickingLinks() throws Exception{
		int i=1;
		while (isElementPresent(By.xpath("//b[@class='pending'][1]//following::span[2]"))) {
				driver.findElement(By.xpath("//b[@class='pending'][1]//following::span[2]")).click();
				System.out.println("clicking link number is  --> : "+i);
				System.out.println("  ");
			while(driver.getWindowHandles().size()>1){
				System.out.println("----still window opened----");
				Thread.sleep(10000);
			}	
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			i++;
		}
	}
	
	public String getTodayCompletedTaskPoints(){
		return driver.findElement(By.id("paidPoints")).getText();
	}
	
	public void submitRedeemPoints(){
		driver.findElement(By.xpath("//li[@id='redeem']/a[text()='Redeem  ePoints']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String getRedeemPointss=driver.findElement(By.id("lblcalredeemepoints")).getText();
		driver.findElement(By.id("btncalredeemepoints")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("rdmepoints")).sendKeys(getRedeemPointss);
	}

}
