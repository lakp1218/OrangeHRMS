package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeaveManagementPage {
    WebDriver driver;

    // Constructor
    public LeaveManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator
    By leaveMenu = By.xpath("//a[@href='#/leave/view_leave_list']");
    By applyLeaveButton = By.xpath("//*[@id='top_level_menu_item_menu_item_210']/a");
    By leaveTypeDropdown = By.xpath("//div[text()='Sick Leave - US']");
    By fromDate = By.id("leave.assign_fromDate");
    By toDate = By.id("leave.assign_toDate");
    By commentBox = By.id("leave.assign_comments");
    By applyButton = By.xpath("//*[@id='mount-vue-app']/div/div[2]/div/div[2]/form/div[13]/button/div");

    By myLeaveButton = By.id("menu_leave_viewMyLeaveList");
    By leaveStatusDropdown = By.id("leaveList_cmbSubunit");
    By searchButton = By.id("btnSearch");

    // Methods
    public void navigateToLeavePage() {
        driver.findElement(leaveMenu).click();
    }

    public void clickApplyLeave() {
        driver.findElement(applyLeaveButton).click();
    }

    public void applyLeave(String from, String to, String comment) throws InterruptedException {
        driver.findElement(leaveTypeDropdown).click();
        Thread.sleep(5000);
        driver.findElement(fromDate).click();
        driver.findElement(fromDate).clear();
        driver.findElement(fromDate).sendKeys(from);
        Thread.sleep(5000);
       /* driver.findElement(toDate).click();
        Thread.sleep(5000);
        driver.findElement(toDate).clear();
        Thread.sleep(5000);
        driver.findElement(toDate).sendKeys(to);*/
        driver.findElement(commentBox).click();
        driver.findElement(commentBox).sendKeys(comment);
        driver.findElement(applyButton).click();
    }

    public void viewMyLeaves() {
        driver.findElement(myLeaveButton).click();
    }

    public void searchLeave(String subUnit) {
        driver.findElement(leaveStatusDropdown).sendKeys(subUnit);
        driver.findElement(searchButton).click();
    }
}
