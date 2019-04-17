package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

	static By find_talent = By.xpath("//div[contains(text(),'Find Talent')]");
	static By search_box = By.id("input-text");
	static By workmarket = By.id("outer-container");
	static By results = By.cssSelector("a.profile-card--name.open-user-profile-popup");
	static By container = By.id("search_results");
	
	public static void verify_homepage(WebDriver driver) {
		WebElement element = driver.findElement(workmarket);
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));	
		Assert.assertEquals(driver.getTitle(),"Home - Work Market");
	}
	
	public static void click_find_talent(WebDriver driver) {
		driver.findElement(find_talent).click();
	}
	
	public static void send_text_to_search(WebDriver driver, String text) {
		driver.findElement(search_box).sendKeys(text + "\n");
	}
	
	public static void verify_search_results(WebDriver driver,String test) throws InterruptedException {
		WebElement element = driver.findElement(container);
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));	
		List<WebElement> allProducts = driver.findElements(results);
		int count = 0;
		for (WebElement product: allProducts) {
			if(product.getText().toLowerCase().contains(test)){
			count++;
			if(count == allProducts.size()) {
				System.out.println("Test Case is passed");
			}
			}
			else{
				System.out.println("Results which did not match:" + product.getText());
				}
		}
	}
}
