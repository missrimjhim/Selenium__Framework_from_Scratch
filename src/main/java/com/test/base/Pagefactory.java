package com.test.base;

import com.test.pages_practiceAutomation.LoginPage;
public class Pagefactory extends Initialization{
	
	public LoginPage LoginPage() {
		return new LoginPage();
	}
	
}
