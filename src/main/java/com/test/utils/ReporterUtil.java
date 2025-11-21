package com.test.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Reporter;
public class ReporterUtil {

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";

    // Method to log test steps (black)
    public static void logStep(String stepDescription) {
        System.out.println(BLACK + "[STEP] " + getTimestamp() + " - " + stepDescription + RESET);
        Reporter.log(BLACK + "[STEP] " + getTimestamp() + " - " + stepDescription + RESET);
    }

    // Method to log verification success (green)
    public static void logVerificationSuccess(String message) {
        System.out.println(GREEN + "[VERIFICATION SUCCESS] " + getTimestamp() + " - " + message + RESET);
        Reporter.log(GREEN + "[VERIFICATION SUCCESS] " + getTimestamp() + " - " + message + RESET);
    }

    // Method to log verification failure (red)
    public static void logVerificationFailure(String message) {
        System.err.println(RED + "[VERIFICATION FAILURE] " + getTimestamp() + " - " + message + RESET);
        Reporter.log(RED + "[VERIFICATION FAILURE] " + getTimestamp() + " - " + message + RESET);
    }

    // Utility method to add timestamps
    private static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}

