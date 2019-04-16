package TestCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindTalent extends ScriptBase{
	@BeforeTest
	public void before() {
	driver.navigate().to("http://dev.workmarket.com/login");
	}
	
	@Test
	public void test1() throws InterruptedException{
	
		
	}
}
