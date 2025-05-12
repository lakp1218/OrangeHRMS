import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    Properties prop;

   private void readProperties(){
       prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("/Users/lakshmi/IdeaProjects/OrangeHRMS/src/test/java/testing.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
   }
    @BeforeMethod
    public void setUp() {

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        readProperties();
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }
    /* Login page functionality Test cases*/
    // This test case is not applicable for further releases
    @Test(enabled = true)
    public void validLoginTest() {
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#/dashboard']")));
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed");
    }

    @Test(priority = 2)
    public void invalidUsernameTest() {
        loginPage.login("InvalidUser", "admin123");
        Assert.assertEquals(loginPage.getErrorMessageForUserName(), "Invalid credentials");
    }

    @Test(priority = 3)
    public void emptyUsernameTest() {
        loginPage.login("", prop.getProperty("password"));
        Assert.assertEquals(loginPage.getErrorMessageForUserName(), "Username cannot be empty");
    }

    @Test(priority = 4)
    public void emptyPasswordTest() {
        loginPage.login("Admin", "");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(loginPage.getErrorMessageForPassword(), "Password cannot be empty");
    }

    @Test(priority = 5)
    public void emptyFieldsTest() {
        loginPage.login("", "");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(loginPage.getErrorMessageForUserName(), "Username cannot be empty");
        Assert.assertEquals(loginPage.getErrorMessageForPassword(), "Password cannot be empty");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

