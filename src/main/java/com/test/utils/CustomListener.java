package com.test.utils;


import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("<font color='black'>[START] Test started: " 
                     + result.getName() + "</font>", true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("<font color='green'>[PASS] Test passed: " 
                     + result.getName() + "</font>", true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("<font color='red'>[FAIL] Test failed: " 
                     + result.getName() + "</font>", true);

        // Optional: log exception details
        Reporter.log("<font color='red'>Reason: " 
                     + result.getThrowable() + "</font>", true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("<font color='orange'>[SKIP] Test skipped: " 
                     + result.getName() + "</font>", true);
    }
}

