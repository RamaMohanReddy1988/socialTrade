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

@SuppressWarnings({ "deprecation" })
public class TodayTaskTest extends BaseClass{
	
	private String todayPoints;

	
	@Test
	public void testStartWorking() throws Exception{
		System.out.println("Clicking Advertisements-----");
		driver.findElement(By.xpath("//a[text()='View Advertisements']")).click();
		Thread.sleep(60000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(isElementPresent(By.id("paidclicks")));
		List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='row_self']"));
		System.out.println("total today task links are  : "+rows.size());
		while (Integer.valueOf(getTodayCompletedTaskPoints())<rows.size()) {
			Assert.assertTrue(isElementPresent(By.xpath("//span[@title='Click Task']")));
			clickingLinks();
			todayPoints=getTodayCompletedTaskPoints();
			System.out.println("Finally completed links are :-  "+todayPoints);
		}
		
		
	}
	
	public void clickingLinks() throws Exception{
		
		while (isElementPresent(By.xpath("//b[@class='pending'][1]//following::span[2]"))) {
			    int linkNum=Integer.valueOf(getTodayCompletedTaskPoints())+1;
				driver.findElement(By.xpath("//div[@id='ajax-content']//tbody/tr["+linkNum+"]/td[4]/span[2]/i")).click();
				System.out.println("clicking link number is  --> : "+linkNum);
				Thread.sleep(1000);
				System.out.println("  ");
				int i=1;
			while(driver.getWindowHandles().size()>1){
				System.out.println("----Still popup window is opened----");
				Thread.sleep(11000);
				i++;
				if (i>6) {
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
			int loop=1;
			while (!Integer.valueOf(getTodayCompletedTaskPoints()).equals(linkNum)) {
				System.out.println("--Still Link status is not change");
				Thread.sleep(5000);
				loop++;
				if (loop==10) {
					System.out.println("link is not working so need to refresh the link "+linkNum);
					i=1;
				}
				if (i==1) {
					break;
				}
			}
			if (i==1) {
				System.out.println("Link is responding after click. S, refreshing -----");
				driver.findElement(By.xpath("//div[@id='ajax-content']//tr["+linkNum+"]//i[@class='fa fa-refresh']")).click();
				Thread.sleep(10000);
			}
			linkNum++;
		}
	}
	
	public String getTodayCompletedTaskPoints(){
		return driver.findElement(By.id("paidPoints")).getText();
	}
	

}
