
import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp() {

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://automation1218-trials7161.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }
    /* Login page functionality Test cases*/
    // This test case is not applicable for further releases
    @Test(enabled = false)
    public void validLoginTest() {
        loginPage.login("Admin", "s1@3AsGuOF");
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed");
    }

    //@Test(priority = 2)
    public void invalidUsernameTest() {
        loginPage.login("InvalidUser", "admin123");
        Assert.assertEquals(loginPage.getErrorMessageForUserName(), "Invalid credentials");
    }

    @Test(priority = 3)
    public void emptyUsernameTest() {
        loginPage.login("", "admin123");
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

