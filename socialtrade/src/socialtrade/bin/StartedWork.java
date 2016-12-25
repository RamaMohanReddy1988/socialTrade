package socialtrade.bin;

import org.testng.annotations.Test;

public class StartedWork extends LoginPage{

	@SuppressWarnings("deprecation")
	@Test
	public void testStartingWorkTest() throws Throwable{
		waitForElement("//a[text()='View Advertisements']");
		selenium.click("//a[text()='View Advertisements']");
		Thread.sleep(60000);
		waitForElement("//div[@id='paidDiv']");
		waitForElement("//span[@title='Click Task']");
		
		while (selenium.isElementPresent("//b[@class='pending'][1]//following::span[2]")) {
			selenium.click("//b[@class='pending'][1]//following::span[2]");
			while(selenium.getAllWindowTitles().length>1){
				Thread.sleep(10000);
			}	
			selenium.setTimeout("5000");
		}
	}
	
}
