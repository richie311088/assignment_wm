package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	static By inputemail = By.id("login-email");
	static By inputpassword = By.id("login-password");
	static By login_button = By.id("login_page_button");
	
	public static void setEmail(WebDriver driver,String email) {
		  driver.findElement(inputemail).sendKeys(email);
	  }
	  
	  public static void setPassword(WebDriver driver,String password) {
		  driver.findElement(inputpassword).sendKeys(password);
	  }
	  
	  public static void clickLogin(WebDriver driver) {
		  driver.findElement(login_button).click();
	  }
}
