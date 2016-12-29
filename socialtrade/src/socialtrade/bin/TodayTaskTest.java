package socialtrade.bin;

/*
 * @Author
 * Rama Mohan Reddy
 */


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@SuppressWarnings({ "unused", "deprecation" })
public class TodayTaskTest extends BaseClass{
	
	private String todayPoints;

	
	@Test
	public void testStartWorking() throws Exception{
		driver.findElement(By.xpath("//a[text()='View Advertisements']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(isElementPresent(By.id("paidclicks")));
		todayPoints=getTodayCompletedTaskPoints();
		List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='row_self']"));
		System.out.println("total today task links are  : "+rows.size());
		while (Integer.valueOf(getTodayCompletedTaskPoints())<rows.size()) {
			Assert.assertTrue(isElementPresent(By.xpath("//span[@title='Click Task']")));
			clickingLinks();
			todayPoints=getTodayCompletedTaskPoints();
			submitRedeemPoints();
		}
		
		
	}
	
	public void clickingLinks() throws Exception{
		
		while (isElementPresent(By.xpath("//b[@class='pending'][1]//following::span[2]"))) {
			    int linkNum=Integer.valueOf(getTodayCompletedTaskPoints())+1;
				driver.findElement(By.xpath("//div[@id='ajax-content']//tbody/tr["+linkNum+"]/td[4]/span[2]/i")).click();
				System.out.println("clicking link number is  --> : "+linkNum);
				System.out.println("  ");
				int i=1;
			while(driver.getWindowHandles().size()>1){
				System.out.println("----Still popup window is opened----");
				Thread.sleep(11000);
				i++;
				if (i>5) {
					System.out.println("Alert message is present. So, navigating to child window to handle");
					String myWindow=driver.getWindowHandle();
					System.out.println("My window is :  "+myWindow);
					Set<String> windows=driver.getWindowHandles();
					for (String window : windows) {
						driver.switchTo().window(window);
					}
					driver.switchTo().alert().accept();
					driver.switchTo().window(myWindow);
				}
			}	
			if (i==1) {
				driver.findElement(By.xpath("//div[@id='ajax-content']//i[@class='fa fa-refresh']")).click();
			}
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			linkNum++;
		}
	}
	
	public String getTodayCompletedTaskPoints(){
		return driver.findElement(By.id("paidPoints")).getText();
	}
	
	public void submitRedeemPoints(){
		driver.findElement(By.xpath("//li[@id='redeem']/a[text()='Redeem  ePoints']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String getRedeemPoints=driver.findElement(By.id("lblcalredeemepoints")).getText();
		System.out.println("Pending redeem points are :- "+getRedeemPoints);
		while (Integer.valueOf(getRedeemPoints)>0) {
			driver.findElement(By.id("btncalredeemepoints")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("rdmepoints")).sendKeys(getRedeemPoints);
			driver.findElement(By.xpath("//div[@id='divredeemepoints']//input[@value='Submit']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
	}

}
