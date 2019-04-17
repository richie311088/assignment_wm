package TestCase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.ThankYouPage;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pageObjects.SignUp;

public class SignUpTest extends ScriptBase {
@BeforeMethod
public void before() {
driver.navigate().to("https://dev.workmarket.com/register/campaign/10081C503B209A0C8E7F05FDCC1AA98D4C904DEEF5F73265CAE38C744E7EAD3E");
}
@Test
//Test to validate all fields are working fine and user lands on next page
    public void test1() throws InterruptedException{
    	SignUp.clickSetupAsIndividual(driver);
        SignUp.setFirstName(driver,"Hello");
        SignUp.setLastName(driver,"Hello");
        SignUp.setEmail(driver,getSaltString()+"@gmail.com");
        SignUp.setPassword(driver,getSaltString());
        SignUp.clickCheckAgreement(driver);
        SignUp.clickRegister(driver);
        ThankYouPage.verifyOnNextPage(driver);
        Assert.assertEquals(ThankYouPage.getTitle(driver),"Work Market | Thank you");
	}

@Test
//Test to validate same email should throw error
	public void test2() throws InterruptedException{
		String email = getSaltString()+"@gmail.com";
		SignUp.clickSetupAsIndividual(driver);
	    SignUp.setFirstName(driver,"Hello");
	    SignUp.setLastName(driver,"Hello");
	    SignUp.setEmail(driver,email);
	    SignUp.setPassword(driver,getSaltString());
	    SignUp.clickCheckAgreement(driver);
	    SignUp.clickRegister(driver);
	    ThankYouPage.verifyOnNextPage(driver);
	    driver.navigate().back();
	    SignUp.clickSetupAsIndividual(driver);
	    SignUp.setFirstName(driver,"Hello");
	    SignUp.setLastName(driver,"Hello");
	    SignUp.setEmail(driver,email);
	    SignUp.setPassword(driver,getSaltString());
	    SignUp.clickCheckAgreement(driver);
	    SignUp.clickRegister(driver);
	    Assert.assertEquals(SignUp.checkErrorMessage(driver,"email_repeat"),"The email address " + email + " is already being used.");
}

@Test
//Test to validate when less than 8 characters are passed to password field
	public void test3() throws InterruptedException{
	//String password = getSaltString();
	String password = "ABCDS";
	String email = getSaltString()+"@gmail.com";
	SignUp.clickSetupAsIndividual(driver);
    SignUp.setFirstName(driver,"Hello");
    SignUp.setLastName(driver,"Hello");
    SignUp.setEmail(driver,email);
    SignUp.setPassword(driver,password);
    passwordMatch(password);
}

@Test
//Test to validate error is thrown when repeated passwords are entered
public void test4() throws InterruptedException{
	//String password = getSaltString();
	String password = "AAAAAAAAA";
	String email = getSaltString()+"@gmail.com";
	SignUp.clickSetupAsIndividual(driver);
    SignUp.setFirstName(driver,"Hello");
    SignUp.setLastName(driver,"Hello");
    SignUp.setEmail(driver,email);
    SignUp.setPassword(driver,password);
    SignUp.clickCheckAgreement(driver);
    SignUp.clickRegister(driver);
    Assert.assertEquals(SignUp.checkErrorMessage(driver,"password_simple"),"Your password entered is not allowed because it is too simple");
}

@Test
//Test to validate first name field is required
public void test5() throws InterruptedException{
	SignUp.clickSetupAsIndividual(driver);
    SignUp.setFirstName(driver,"H");
    driver.findElement(By.id("firstname")).sendKeys(Keys.BACK_SPACE);
    SignUp.setLastName(driver,"Hello");
    Assert.assertEquals(SignUp.checkErrorMessage(driver,"valid_name"),"Please enter a valid first name");
}

@Test
//Test to validate last name field is required
public void test6() throws InterruptedException{
	SignUp.clickSetupAsIndividual(driver);
	SignUp.setFirstName(driver,"Hello");
	SignUp.setLastName(driver,"H");
	driver.findElement(By.id("lastname")).sendKeys(Keys.BACK_SPACE);
	driver.findElement(By.id("email")).sendKeys(Keys.TAB);
	Assert.assertEquals(SignUp.checkErrorMessage(driver,"valid_lastname"),"Please enter a valid last name");
}

@Test
//Test to validate error is thrown when more than 50 characters are typed in First Name Field
public void test7() {
	SignUp.clickSetupAsIndividual(driver);
	SignUp.setFirstName(driver,"HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloH");
	SignUp.setLastName(driver,"Hello");
    SignUp.setEmail(driver,getSaltString()+"@gmail.com");
    SignUp.setPassword(driver,getSaltString());
    SignUp.clickCheckAgreement(driver);
    SignUp.clickRegister(driver);
    Assert.assertEquals(SignUp.checkErrorMessage(driver,"registration_error"),"There was a problem submitting your registration. Please try again.");
}

@Test
//Test to validate error is thrown when more than 50 characters are typed in Last Name Field
public void test8() {
	SignUp.clickSetupAsIndividual(driver);
	SignUp.setFirstName(driver,"Hello");
	SignUp.setLastName(driver,"HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloH");
    SignUp.setEmail(driver,getSaltString()+"@gmail.com");
    SignUp.setPassword(driver,getSaltString());
    SignUp.clickCheckAgreement(driver);
    SignUp.clickRegister(driver);
    Assert.assertEquals(SignUp.checkErrorMessage(driver,"registration_error"),"There was a problem submitting your registration. Please try again.");
}

@Test
//Test to validate error is thrown when email field is empty
public void test9() {
	SignUp.clickSetupAsIndividual(driver);
	SignUp.setFirstName(driver,"Hello");
	SignUp.setLastName(driver,"Hello");
    SignUp.setEmail(driver,"r");
    driver.findElement(By.id("email")).sendKeys(Keys.BACK_SPACE);
    driver.findElement(By.id("email")).sendKeys(Keys.TAB);
    Assert.assertEquals(SignUp.checkErrorMessage(driver,"valid_email"),"Please enter a valid email");
}

@Test
//Test to validate error is thrown when email field pattern is not correct
public void test10() {
	String email = "hello#123";
	SignUp.clickSetupAsIndividual(driver);
	SignUp.setFirstName(driver,"Hello");
	SignUp.setLastName(driver,"Hello");
	SignUp.setEmail(driver,email);
	String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[@])(?=.*[A-Z]).{8,}";
	driver.findElement(By.id("email")).sendKeys(Keys.TAB);
	if(email.matches(pattern)) {
		System.out.println("Email is correct");
	}
	else {
		Assert.assertEquals(SignUp.checkErrorMessage(driver,"valid_email"),"Please enter a valid email");
	}
}

@Test
//Test to validate registration is successful when special characters are added to all fields
public void test11() {
	SignUp.clickSetupAsIndividual(driver);
	SignUp.setFirstName(driver,"Hello#");
	SignUp.setLastName(driver,"He@llo");
	SignUp.setEmail(driver,getSaltString()+"@gmail.com");
	SignUp.setPassword(driver,getSaltString());
	SignUp.clickCheckAgreement(driver);
	SignUp.clickRegister(driver);
	ThankYouPage.verifyOnNextPage(driver);
	Assert.assertEquals(ThankYouPage.getTitle(driver),"Work Market | Thank you");
}

@Test
//Test to validate when numbers are added to the first and last name fields, registration is successful
public void test12() {
	SignUp.clickSetupAsIndividual(driver);
	SignUp.setFirstName(driver,"123456745");
	SignUp.setLastName(driver,"3434234");
	SignUp.setEmail(driver,getSaltString()+"@gmail.com");
	SignUp.setPassword(driver,getSaltString());
	SignUp.clickCheckAgreement(driver);
	SignUp.clickRegister(driver);
	ThankYouPage.verifyOnNextPage(driver);
	Assert.assertEquals(ThankYouPage.getTitle(driver),"Work Market | Thank you");
}

public String getSaltString() {
    String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890#$%^&+=";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 10) { // length of the random string.
        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
        salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;
}

public void passwordMatch(String password) {
	String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";
	if(password.matches(pattern)) {
		SignUp.clickCheckAgreement(driver);
	    SignUp.clickRegister(driver);
	    ThankYouPage.verifyOnNextPage(driver);
        Assert.assertEquals(ThankYouPage.getTitle(driver),"Work Market | Thank you");
	}
	else {
		SignUp.clickCheckAgreement(driver);
	    SignUp.clickRegister(driver);
	    Assert.assertEquals(SignUp.checkErrorMessage(driver,"password_error"),"Please enter a valid password");
	}
}

}