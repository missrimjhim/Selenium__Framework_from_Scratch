package com.test.googletestcases;

import java.util.Map;

import org.testng.annotations.Test;

import com.test.base.Pagefactory;

public class PracticeAutomationTestcases extends Pagefactory {
	@Test
	public void TC01_Positive_LogIn_test(){
		System.out.println("TEST STARTED");
		Map<String, String> testdata=excel_Reader().getTestData("Sheet1", "TC01_Positive LogIn test");
		LoginPage().enterUsername(testdata.get("Username"));
		LoginPage().enterPassword(testdata.get("Password"));
		LoginPage().clickLogin();
		LoginPage().isSuccessMessageDisplayed();
		LoginPage().isUrlCorrect();
		LoginPage().isLogoutButtonDisplayed();
	}
	@Test
	public void TC02_Negative_username_test() {
		Map<String, String> testdata=excel_Reader().getTestData("Sheet1", "TC02_Negative username test");
		LoginPage().enterUsername(testdata.get("Username"));
		LoginPage().enterPassword(testdata.get("Password"));
		LoginPage().clickLogin();
		LoginPage().isErrormessageDisplayed();
	}
	
	@Test
	public void TC03_Negative_password_test() {
		Map<String, String> testdata=excel_Reader().getTestData("Sheet1", "TC03_Negative password test");
		LoginPage().enterUsername(testdata.get("Username"));
		LoginPage().enterPassword(testdata.get("Password"));
		LoginPage().clickLogin();
		LoginPage().isInvalidPasswordDisplayed();
		
	}	
}
