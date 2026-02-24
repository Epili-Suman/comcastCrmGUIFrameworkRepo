package com.comcast.demowebshop.listenerutility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.baseutility.BaseClass;

public class ListenerImp implements ITestListener,ISuiteListener
{
	ExtentReports report;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) 
	{
		ExtentSparkReporter spark = new ExtentSparkReporter("AdvanceReport/Report.html");
		spark.config().setDocumentTitle("Advanced Reports");
		spark.config().setReportName("Vtiger Report");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Laptop", "Asus");
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "Chrome");
	}

	@Override 
	public void onFinish(ISuite suite)
	{
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		String testCase=result.getMethod().getMethodName();
//		Reporter.log(testCase+" Execution started");
		
		test = report.createTest(testCase);
		test.log(Status.INFO, testCase+"Execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		String testCase=result.getMethod().getMethodName();
//		Reporter.log(testCase+" Execution success");
		
		test.log(Status.PASS, testCase+" Execution success");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String testCase=result.getMethod().getMethodName();
//		Reporter.log(testCase+" Execution failed");
		
		test.log(Status.FAIL, testCase+" Execution failed");
		TakesScreenshot ss = (TakesScreenshot) BaseClass.sdriver;
		String srs = ss.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(srs);
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		String testCase=result.getMethod().getMethodName();
//		Reporter.log(testCase+" Execution skipped");
		
		test.log(Status.SKIP, testCase+" Execution skipped");
	}
	
}