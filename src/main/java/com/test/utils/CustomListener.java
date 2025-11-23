package com.test.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;



public class CustomListener implements ITestListener {
	public CustomListener() {
        Reporter.setEscapeHtml(false);   // ✔ Needed for HTML logs
    }

    @Override
    public void onTestStart(ITestResult result) {
    	Reporter.setCurrentTestResult(result);
        Reporter.log("<font color='black'>[START] Test started: "
                + result.getName() + "</font>", true);
        
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	Reporter.setCurrentTestResult(result);
        Reporter.log("<font color='green'>[PASS] Test passed: "
                + result.getName() + "</font>", true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	Reporter.setCurrentTestResult(result);
    	

        Reporter.log("<font color='red'>[FAIL] Test failed: "
                + result.getName() + "</font>", true);

        Reporter.log("<font color='red'>Reason: "
                + result.getThrowable() + "</font>", true);
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        if (driver != null) {

            // 1️⃣ SAVE File Screenshot using your utility
            String filePath = TakeScreenshotUtil.captureScreenshot(driver, result.getName());

            Reporter.log("<br><a href='" + filePath + "' target='_blank'>Open Saved Screenshot</a><br>", true);

            // 2️⃣ ALSO capture Base64 screenshot (for HTML embed)
            String base64Screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BASE64);

            // Embed directly in TestNG HTML
            String imgTag =
                    "<br><a href=\"data:image/png;base64," + base64Screenshot + "\" target=\"_blank\">" +
                            "<img src=\"data:image/png;base64," + base64Screenshot + "\" height='220' width='350'/>" +
                    "</a><br>";

            Reporter.log(imgTag, true);
        } else {
            Reporter.log("<font color='red'>Driver is NULL — screenshot not captured.</font><br>", true);
        }
        
    	
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	Reporter.setCurrentTestResult(result);
        Reporter.log("<font color='orange'>[SKIP] Test skipped: "
                + result.getName() + "</font>", true);
    }
}
