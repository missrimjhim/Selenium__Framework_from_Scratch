package com.test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class TakeScreenshotUtil {

    // Method to capture screenshot and return file path
    public static String captureScreenshot(WebDriver driver, String screenshotName) {

        // Create timestamp for unique file name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Build file path (cross-platform)
        String folderPath = System.getProperty("user.dir") 
                + File.separator + "screenshots";

        String filePath = folderPath 
                + File.separator + screenshotName + "_" + timestamp + ".png";

        // Ensure screenshots folder exists
        File screenshotDir = new File(folderPath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        // Capture screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }

        return filePath; // Return path for embedding in reports
    }
}
