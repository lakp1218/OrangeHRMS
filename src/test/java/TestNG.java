import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class TestNG {
    WebDriver driver;
    // Saving the expected title of the Webpage
    String title = "ToolsQA - Demo Website For Automation";


    @Test(groups = { "demo" }, priority = 1)
    public void starting_point(){
        driver = new ChromeDriver();
        System.out.println("This is the starting point of the test");
        //Initialize Chrome Driver
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/");
    }


    @Test(groups = { "demo" }, priority = 2)
    public void checkTitle() {
        String testTitle = "DEMOQA";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, testTitle);
    }

   // @Test(groups = { "demo2" }, priority = 3)
    public void click_element() {
        driver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[1]/ul/li[1]/a")).click();
        System.out.println("Home Page heading is displayed");
    }
}
