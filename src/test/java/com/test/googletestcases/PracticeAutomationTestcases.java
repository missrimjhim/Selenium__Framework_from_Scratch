package com.test.googletestcases;

import org.testng.annotations.Test;

import com.test.base.Pagefactory;

public class PracticeAutomationTestcases extends Pagefactory {
	@Test
	public void TC01_Positive_LogIn_test(){
		LoginPage().enterUsername("student");
		LoginPage().enterPassword("Password123");
		LoginPage().clickLogin();
		LoginPage().isSuccessMessageDisplayed();
		LoginPage().isUrlCorrect();
		LoginPage().isLogoutButtonDisplayed();
	}
	@Test
	public void TC02_Negative_username_test() {
		LoginPage().enterUsername("incorrectUser");
		LoginPage().enterPassword("Password123");
		LoginPage().clickLogin();
		LoginPage().isErrormessageDisplayed();
		
	}
	@Test
	public void TC03_Negative_password_test() {
		LoginPage().enterUsername("student");
		LoginPage().enterPassword("incorrectPassword");
		LoginPage().clickLogin();
		LoginPage().isInvalidPasswordDisplayed();
		
	}	
}
