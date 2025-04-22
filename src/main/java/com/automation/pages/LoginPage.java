package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    private By usernameField = By.name("txtUsername");
    private By passwordField = By.name("txtPassword");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMsgForUserNameField = By.xpath("//span[@id='txtUsername-error']");
    private By  errorMsgForPasswordField = By.xpath("//span[@id='txtPassword-error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessageForUserName() {
        return driver.findElement(errorMsgForUserNameField).getText();
    }

    public String getErrorMessageForPassword() {
        return driver.findElement(errorMsgForPasswordField).getText();
    }



    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
