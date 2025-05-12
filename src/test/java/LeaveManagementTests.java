
import com.automation.pages.DashboardPage;
import com.automation.pages.LeaveManagementPage;
import com.automation.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LeaveManagementTests  {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    Properties prop;
    LeaveManagementPage leavePage;

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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        readProperties();
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        leavePage = new LeaveManagementPage(driver);

        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#/dashboard']")));
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed");
      }

    @Test
    public void applyForLeaveTest() throws InterruptedException {
        leavePage.navigateToLeavePage();
        Thread.sleep(8000);
        leavePage.clickApplyLeave();
        Thread.sleep(8000);
        leavePage.applyLeave("2025-05-20", "2025-05-20", "Family function");
    }

    @Test
    public void viewAndSearchLeavesTest() {
        LeaveManagementPage leavePage = new LeaveManagementPage(driver);
        leavePage.navigateToLeavePage();
        leavePage.viewMyLeaves();
        leavePage.searchLeave("Engineering");
    }
}
