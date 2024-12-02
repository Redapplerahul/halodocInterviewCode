package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Utility {


    public static ExtentReports reports;
    public static ExtentTest test;
    public static ExtentSparkReporter sparkReporter;


    @BeforeTest
    public void beforeTest() {

        reports = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("spark/spark.html")
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST})
                .apply();

        reports.attachReporter(sparkReporter);


        ExtentSparkReporterConfig extentSparkReporterConfig = sparkReporter.config();

        extentSparkReporterConfig.setTheme(Theme.DARK);
        extentSparkReporterConfig.setReportName("API AUTOMATION TESTCASES");
        extentSparkReporterConfig.setDocumentTitle("HALODOC Automation Tests");


    }


    @AfterTest
    public void afterTest() {

        reports.flush();
    }
}
