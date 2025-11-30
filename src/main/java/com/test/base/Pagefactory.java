package com.test.base;

import com.test.pages_practiceAutomation.LoginPage;
import com.test.utils.excel_Reader;
public class Pagefactory extends Initialization{
	
	public LoginPage LoginPage() {
		return new LoginPage();
	}
	
	public excel_Reader excel_Reader() {
		return new excel_Reader();
	}
	
}
