package com.automation.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

        WebDriver driver;

        private By dashboardHeader = By.xpath("//a[@href='#/dashboard']");

        public DashboardPage(WebDriver driver) {
            this.driver = driver;
        }

        public boolean isDashboardDisplayed() {
            return driver.findElement(dashboardHeader).isDisplayed();
        }

    }


