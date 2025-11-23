package com.test.utils;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {

    public int retryCount = 0;
    private final int maxRetryCount = 1;  // Retry failed test 1 more time

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test " + result.getName() + " | Retry count: " + retryCount);
            return true;  // Retry the test
        }

        return false;  // Do not retry further
    }
}
