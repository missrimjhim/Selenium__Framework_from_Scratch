package com.test.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.test.utils.ReadConfig;

public class Initialization {
	public static WebDriver driver;
	ReadConfig config=new ReadConfig();
	@BeforeMethod
	public void setup() {
		String browser= config.getProperty("browser");
		String executionType= config.getProperty("executiontype");
		String url= config.getProperty("url");
		
		switch(browser) {
		case "chrome":
			if(executionType.equalsIgnoreCase("true")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
				driver=new ChromeDriver();	
				break;
			}
			
		case "edge":
			if(executionType.equalsIgnoreCase("true")) {
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\Drivers\\msedgedriver.exe");
				driver=new EdgeDriver();
				break;
			}
			default:
				throw new IllegalArgumentException("Browser Not Supported:"+ browser);
		}
		Reporter.getCurrentTestResult().getTestContext().setAttribute("driver", driver);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		
	}
	@AfterMethod
	public void tear_down() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
