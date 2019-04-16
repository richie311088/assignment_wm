package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThankYouPage {
	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	static By nextPage = By.id("campaign_landing");
	public static void verifyOnNextPage(WebDriver driver) {
		WebElement element = driver.findElement(nextPage);
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));	  }
}
