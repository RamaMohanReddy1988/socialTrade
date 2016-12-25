package socialtrade.bin;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class LoginPage {

	Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "https://socialtrade.biz/");
	
	
	@BeforeClass
	public void testLogin() throws Throwable{
		selenium.start();
		selenium.windowMaximize();
		selenium.open("https://socialtrade.biz/");
		Thread.sleep(30000);
		waitForElement("txtEmailID");
		waitForElement("txtPassword");
		selenium.type("txtEmailID", "61828241");
		selenium.type("txtPassword", "ramamohan@143");
		selenium.click("CndSignIn");
		waitForElement("//a[@class='dropdown-toggle']");
		selenium.click("//a[@class='dropdown-toggle']");
		waitForElement("//span[text()='Sign Out']");
		Assert("Login is not successful----", selenium.isElementPresent("//span[text()='Sign Out']"));
	}
	
	//public void submitRedeemPoints(){
		//seleniu
	//}
	
	@AfterClass
	public void tearDown(){
		selenium.deleteAllVisibleCookies();
		selenium.close();
		selenium.stop();
	}
	
	public boolean Assert(String string, boolean elementPresent) {
		if (elementPresent) {
			return true;
		}
		else
			return false;
		
	}

	public void waitForElement(String element) throws Throwable{
		for (int i = 0; i < 100; i++) {
			if (selenium.isElementPresent(element)) {
				break;
			}
			Thread.sleep(800);
		}
	}

	

}
