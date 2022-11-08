package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class Helper extends BaseClass {


	static int Page_Load_Time=180;
	static int Implicit_Wait=60;



	//Helper class contains common lib functions.

	

	// Open any page

		public static void openTestSitePage(String url) {

			driver.get(url);

		}
		
		
		
		
	// Click Action if element displayed

	public static void clickPageLinkOption(WebElement element, String ElementName) {

		if (Assertionss.validateElementDisplayed(driver, element)) {

			javaScriptClickMehtod(driver, element);			
			
			//element.click();

			logger.pass(ElementName + " Clicked");

		}

		else {

			result = false;
			softAssertion.assertTrue(result, "Asserion Failed : Element Not Present");
			logger.fail(ElementName + " Not Found");
		}

	}

	
	

	// Below method can be call to take screen-shot (with current date and time) after test cases (pass/fail/skip).

	public static String CaptureScreenShot(WebDriver driver) {


		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);


		String ScreenShotPath=System.getProperty("user.dir")+"\\Screenshots\\"+ "UnlimintTest_" + getCurrentDateTime() + ".png";

		try 
		{
			FileHandler.copy(src, new File(ScreenShotPath));
			System.out.println("Screen-shot Captured");

		} catch (IOException e) {

			System.out.println("Unable to capture Screen-shot>>" + e.getMessage());

		}

		//System.out.println(ScreenShotPath);
		return ScreenShotPath;

	}


	public static String getCurrentDateTime() {

		DateFormat customformat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customformat.format(currentDate);

	}




	// Call this method if page load time is required

	public static void WaitForPageLoadTime(WebDriver driver) {                     

		driver.manage().timeouts().pageLoadTimeout(Page_Load_Time, TimeUnit.SECONDS); 


	}


	// Call this method to get value from drop down

	public static void selectByValueDropDown(WebElement element, String value) {                     

		Select slct = new Select(element);
		slct.selectByValue(value); 
	}



	// Use this method for explicitly wait for an element to be clickable

	public static void ExplicitWaitForElementClickaablae(WebDriver driver , WebElement element) {   


		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);  
			//		wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
		
		}

		catch(Exception e) {

			System.out.println(e);

		}


	}

	
	// Use this method for to check URL contains expected url
	
	public static void ExplicitWaitForURLContains(WebDriver driver , String expuUrl) {   


		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);  
			wait.until(ExpectedConditions.urlContains(expuUrl));
			
		
		}

		catch(Exception e) {

			System.out.println(e);

		}


	}
	
	

	
	// Call this method if direct click not working

	public static void javaScriptClickMehtod(WebDriver Wdriver, WebElement element) {  


		JavascriptExecutor executor = (JavascriptExecutor) Wdriver;
		executor.executeScript("arguments[0].click()", element);

	}


}
