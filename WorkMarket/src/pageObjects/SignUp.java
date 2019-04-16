package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUp {
	static By buttonJoinAsAnIndividual = By.xpath("//span[contains(text(),'Join as an individual')]");
	static By inputFirstName = By.id("firstname");
	static By inputLastName = By.id("lastname");
	static By inputEmail = By.id("email");
	static By inputPassword = By.id("password");
	static By inputCheckAgreement = By.xpath("//input[@type='checkbox']");
	static By buttonRegister = By.xpath("//span[contains(text(),'Register')]");
	static By error_block = By.xpath("//div[@data-component-identifier='wm-message-banner-text']//p");
	static By password_error = By.xpath("//div[contains(text(),'Please enter a valid password')]");
	static By valid_name = By.xpath("//div[contains(text(),'Please enter a valid first name')]");
	static By valid_lastname = By.xpath("//div[contains(text(),'Please enter a valid last name')]");
    static By valid_email = By.xpath("//div[contains(text(),'Please enter a valid  email')]");
  public static void setFirstName(WebDriver driver,String strFirstName) {
	  driver.findElement(inputFirstName).sendKeys(strFirstName);
  }
  
  public static void setLastName(WebDriver driver,String strLastName) {
	  driver.findElement(inputLastName).sendKeys(strLastName);
  }
  
  public static void setEmail(WebDriver driver,String strEmail) {
	  driver.findElement(inputEmail).sendKeys(strEmail);
  }
  
  public static void setPassword(WebDriver driver,String strPassword) {
	  driver.findElement(inputPassword).sendKeys(strPassword);
  }
  
  public static void clickRegister(WebDriver driver) {
	  driver.findElement(buttonRegister).click();
  }
  
  public static void clickCheckAgreement(WebDriver driver) {
	  driver.findElement(inputCheckAgreement).click();
  }
  
  public static void clickSetupAsIndividual(WebDriver driver) {
	 driver.findElement(buttonJoinAsAnIndividual).click();
	 }
  
  public static String checkErrorMessage(WebDriver driver,String field) { 
	 String errorMessage=null;
	 switch(field) { 
	 case "email_repeat": 
		 errorMessage = driver.findElement(error_block).getText();
		 break;
	 case "password_simple":
		 errorMessage =  driver.findElement(error_block).getText();
		 break;
	 case "password_error":
		 errorMessage =  driver.findElement(password_error).getText();
		 break;
	 case "valid_name":
		 errorMessage =  driver.findElement(valid_name).getText();
		 break;
	 case "valid_lastname":
		 errorMessage =  driver.findElement(valid_lastname).getText();
		 break;
	 case "registration_error":
		 errorMessage =  driver.findElement(error_block).getText();
		 break;
	 case "valid_email":
		 errorMessage =  driver.findElement(valid_email).getText();
		 break;
	default: 
         System.out.println("no match"); 
	 }
	 return errorMessage;
	 }
}
