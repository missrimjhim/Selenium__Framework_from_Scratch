package com.test.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.xml.XmlSuite; 
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
public class ReporterUtil implements IReporter {

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
    
   

        @Override
        public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
            StringBuilder reportBuilder = new StringBuilder();

            // HTML Header
            reportBuilder.append("<html><head><title>Custom TestNG Report</title></head><body>");
            reportBuilder.append("<h1>Automation Execution Report</h1>");
            reportBuilder.append("<table border='1' style='border-collapse:collapse;width:100%'>");
            reportBuilder.append("<tr><th>Test Name</th><th>Status</th><th>Screenshot</th></tr>");

            // Iterate through suites
            for (ISuite suite : suites) {
                Map<String, ISuiteResult> suiteResults = suite.getResults();

                for (ISuiteResult sr : suiteResults.values()) {
                    ITestContext tc = sr.getTestContext();

                    // Failed tests
                    for (ITestResult result : tc.getFailedTests().getAllResults()) {
                        String screenshotPath = (String) result.getAttribute("screenshotPath");

                        reportBuilder.append("<tr>");
                        reportBuilder.append("<td>").append(result.getName()).append("</td>");
                        reportBuilder.append("<td style='color:red'>FAILED</td>");
                        

                        if (screenshotPath != null) {
                            reportBuilder.append("<td><a href='")
                                         .append(screenshotPath)
                                         .append("'>View Screenshot</a></td>");
                        } else {
                            reportBuilder.append("<td>No Screenshot</td>");
                        }
                        reportBuilder.append("</tr>");
                    }

                    // Passed tests
                    for (ITestResult result : tc.getPassedTests().getAllResults()) {
                        reportBuilder.append("<tr>");
                        reportBuilder.append("<td>").append(result.getName()).append("</td>");
                        reportBuilder.append("<td style='color:green'>PASSED</td>");
                        reportBuilder.append("<td>-</td>");
                        reportBuilder.append("</tr>");
                    }
                }
            }

            reportBuilder.append("</table></body></html>");

            // Write to file
            try {
                File reportFile = new File(outputDirectory + File.separator + "custom-emailable-report.html");
                FileWriter writer = new FileWriter(reportFile);
                writer.write(reportBuilder.toString());
                writer.close();
                System.out.println("Custom report generated at: " + reportFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
}

