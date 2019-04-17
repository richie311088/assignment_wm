package TestCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class FindTalent extends ScriptBase{
	@BeforeMethod
	public void before() {
	driver.navigate().to("http://dev.workmarket.com/login");
	}
	
	@Test
	public void test1() throws InterruptedException{
		LoginPage.setEmail(driver,"qa+candidatetest@workmarket.com");
		LoginPage.setPassword(driver, "candidate123");
		LoginPage.clickLogin(driver);
		HomePage.verify_homepage(driver);
		HomePage.click_find_talent(driver);
		HomePage.send_text_to_search(driver,"test");
		HomePage.verify_search_results(driver,"test");
	}
}
