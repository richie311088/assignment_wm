package TestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ScriptBase {
    protected WebDriver driver=null;

    @BeforeMethod
    public void setUp() throws InterruptedException{

        System.out.println("****starting the browseR*****");

        System.setProperty("webdriver.chrome.driver", ".\\src\\chromedriver.exe");
        
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}