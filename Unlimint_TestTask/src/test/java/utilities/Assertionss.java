package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class Assertionss extends BaseClass {


	public static boolean validateElementDisplayed(WebDriver driver, WebElement element ) { 
		
		
		// Use this method to check element displaying on the page


		boolean result=false;


		try {	

			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed()){
				result=true;
			}
		}

		catch (Exception e)  {
			
			
		}

		
		return result;
		
	}

	
	
	
	// Use this method to validate page URL

	public static boolean validateURLContains(WebDriver driver, String expURL) {       

		boolean result= false;

		try {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		result=wait.until(ExpectedConditions.urlContains(expURL));
		
		}
		
		catch(Exception e) {
			
			
		}

		return result;
	}
	
	
	
	
	// Validate Page Source Contains

		public static boolean validatePageSourceContainsText(WebDriver driver, String expText) {       

			boolean result= false;
			
			if (driver.getPageSource().contains(expText)) {

				logger.pass("Expected Text is present: "  + expText + " || " + " Page URL is : "+ driver.getCurrentUrl());
			}

			else {

				result = false;
				softAssertion.assertTrue(result, "Asserion Failed : Expected Text Not present.");
				logger.fail("Expected Text not present: "  +expText+ " || " + " Page URL is : "+ driver.getCurrentUrl());
			}

			return result;
		}
		
		
	
}
