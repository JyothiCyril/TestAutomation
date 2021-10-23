package com.qa.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {


	public ExtentHtmlReporter htmlReporter;
	public ExtentReports xReports;
	public ExtentTest xTest;

	//LoC for Configuration of extent reports
	public void onStart(ITestContext testContext) {
		String DateStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());		
		String repName = "Test-Automation"+DateStamp+".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+repName);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setAutoCreateRelativePathMedia(true);

		xReports = new ExtentReports();
		xReports.attachReporter(htmlReporter); // format of the report to be attached as extent report
		xReports.setSystemInfo("HostName", "localhost");
		xReports.setSystemInfo("OS", "Windows");
		xReports.setSystemInfo("Tester", "JyothiCyril");


	}

	// LoC for terminating extent classes appropriately. and capture the results
	public void onFinish(ITestContext testContext) {

		xReports.flush();

	}


	// method to read the results of methods annotated as @Test when passed and executed via from testng.xml file
	// It has make an entry in the extent report.

	public void onTestSuccess(ITestResult tr) {

		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.PASS, "Test is Passed");
		xTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));		

	}


	// method to read the results of methods annotated as @Test when faile and executed via from testng.xml file
	// It has make an entry in the extent report.
	public void onTestFailure(ITestResult tr) {
		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.FAIL, "Test is Failed");
		xTest.log(Status.FAIL, tr.getThrowable());
		xTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		String SSPath = System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		File file = new File(SSPath);
		if(file.exists()) {
			try {
				xTest.fail("Screenshot for test failed is : " + xTest.addScreenCaptureFromPath(SSPath));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}



	}

	// method to read the results of methods annotated as @Test when skipped and executed via from testng.xml file
	// It has make an entry in the extent report.

	public void onTestSkipped(ITestResult tr) {
		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.SKIP, "Test is Skipped");
		xTest.log(Status.SKIP, tr.getThrowable());
		xTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));	

	}

}
