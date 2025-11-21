package com.test.pages_practiceAutomation;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import com.test.base.Initialization;
import com.test.utils.ReporterUtil;

public class LoginPage extends Initialization {
	
	

    // Locators using By
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton   = By.id("submit");
    private By errorMessage  = By.id("errorMsg");
    private By logoutButton = By.xpath("//a[text()='Log out']");
    private By successMessage= By.xpath("//strong[contains(text(),'Congratulations')]");
    
   

    // Actions
    public void enterUsername(String username) {
        WebElement userElement = driver.findElement(usernameField);
        userElement.clear();
        ReporterUtil.logStep("Enter Username");
        userElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passElement = driver.findElement(passwordField);
        passElement.clear();
        ReporterUtil.logStep("Enter Password");
        passElement.sendKeys(password);
    }

    public void clickLogin() {
    		 ReporterUtil.logStep("Click on submit button");
    		 driver.findElement(loginButton).click(); 
    }

    public String getErrorMessage() {
    	
        return driver.findElement(errorMessage).getText();
    }

    // Combined login method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    public void isUrlCorrect() {
        String currentUrl = driver.getCurrentUrl();
        
        if( currentUrl.contains("practicetestautomation.com/logged-in-successfully/")) {
        	ReporterUtil.logVerificationSuccess("Login Successful!!");
        }
        else {
        	ReporterUtil.logVerificationFailure("Couldn't Login!!");
        }
        	
    }

    // Assertion: Verify page contains expected text
    public void isSuccessMessageDisplayed() {
        String message = driver.findElement(successMessage).getText();
        if( message.contains("Congratulations") || message.contains("successfully logged in")) {
        	ReporterUtil.logVerificationSuccess("Message displayed!!");
        }
        else {
        	ReporterUtil.logVerificationFailure("Couldn't Login!!");
        }
    }

    // Assertion: Verify logout button is displayed
    public void isLogoutButtonDisplayed() {
        WebElement logout = driver.findElement(logoutButton);
        if( logout.isDisplayed()) {
        	ReporterUtil.logVerificationSuccess("Logout button is displayed!!");
        }
        else {
        	ReporterUtil.logVerificationFailure("Logout button is not displayed");
        }
    }
    
    //Assertion : Verify if invalid message is displayed upon entering wrong password
    public void isErrormessageDisplayed() {
        String message = driver.findElement(By.xpath("//div[text()='Your username is invalid!']")).getText();
        if( message.contains("Your username is invalid!")) {
        	ReporterUtil.logVerificationSuccess("Error Message displayed!!");
        }
        else {
        	ReporterUtil.logVerificationFailure("Error message not Displayed");
        }
    }
    
    //Assertion: Verify if invalid password message is displayed
    public void isInvalidPasswordDisplayed() {
        String message = driver.findElement(By.xpath("//div[text()='Your password is invalid!']")).getText();
        if( message.contains("Your password is invalid!")) {
        	ReporterUtil.logVerificationSuccess("Error Message displayed!!");
        }
        else {
        	ReporterUtil.logVerificationFailure("Error message not Displayed");
        }
    }
}
