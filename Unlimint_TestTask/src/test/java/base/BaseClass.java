package base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigDataProvider;
import utilities.Helper;

public class BaseClass {

	public static WebDriver driver;
	public static ConfigDataProvider Config;
	public static ConfigDataProvider CustomLogs;
	public static ConfigDataProvider LoggerERMessages;

	public static ExtentHtmlReporter extent;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentTest parentTest;
	public static ExtentTest child2;
	public String TestCaseDescription;

	public static SoftAssert softAssertion;
	public static boolean result;

	@BeforeSuite()
	public void SetupSuite() {


		// Define Object

		Config = new ConfigDataProvider("./Configuration/config.properties");	 
		CustomLogs = new ConfigDataProvider("./Configuration/customLogs.properties");
		LoggerERMessages= new ConfigDataProvider("./Configuration/loggerERMessages.properties");




		// Log message 1
		Reporter.log(CustomLogs.getDataFromPropertyFile("logMessage1"), true);




		// Setting Extent Report

		extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Unlimint_TestTask2" + ".html"));


		report = new ExtentReports();

		report.attachReporter(extent);
		extent.config().enableTimeline(true);


		
		// Log message 2

		Reporter.log(CustomLogs.getDataFromPropertyFile("logMessage2"), true);



		// Log message 3

		Reporter.log(CustomLogs.getDataFromPropertyFile("logMessage3"), true);



		// Initiate Browser through Web Driver Manager

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);


	}


	@BeforeClass()
	public void setup() {


		// Log message 4

		Reporter.log(CustomLogs.getDataFromPropertyFile("logMessage4"), true);


	}


	@AfterMethod()
	public void tearDownMethod(ITestResult result) throws Exception {


		// Capturing Test Method through iTestListner

		Reporter.log(result.getName() + " " + CustomLogs.getDataFromPropertyFile("logMessage6") +" " + result.getStatus(), true);



		// Check Test Case Method Status

		if (result.getStatus() == ITestResult.FAILURE)
		{

			// Log status in report

			logger.fail("<p style=\"color:red;\"><b>Test Failed</b></p>");


			try {


				// Capture Screen shot if test method fails

				logger.fail("Refer Below Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(Helper.CaptureScreenShot(driver)).build());

			} 

			catch (IOException e) 
			{

				// Log error in report

				System.out.println(e.getMessage());

			} 


			// Log error in report

			logger.fail(result.getThrowable());

		}


		else if (result.getStatus() == ITestResult.SUCCESS)   
		{

			// Log status in report

			logger.pass("<p style=\"color:green;\"><b>Test Passed</b></p>");         // Can add the screen shot as well.

		}

		else if (result.getStatus() == ITestResult.SKIP)   
		{

			// Log status in report

			logger.skip("<b>Test Skipped</b>");                  // Can add the screen shot as well.
		}		 

	}


	@AfterClass()
	public void tearDown() {

		// Log message 5

		Reporter.log(CustomLogs.getDataFromPropertyFile("logMessage5") + "\r\n"+ "\r\n", true);

	}	

	@AfterSuite()
	public void tearDown2() {


		//close browser

		driver.quit();


		//Set Report Name 

		extent.config().setReportName(LoggerERMessages.getDataFromPropertyFile("ExtentReportName"));



		//Set Report Title

		extent.config().setDocumentTitle(LoggerERMessages.getDataFromPropertyFile("ExtentReportPageTitle"));


		//flush extent report

		report.flush();

	}



}
