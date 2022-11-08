package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Assertionss;

public class CreateAnAccountPage extends BaseClass {
	
	WebDriver driver;
	
	
	//Below constructor is used to pass the driver.  
	
	public CreateAnAccountPage(WebDriver ldriver) {
		
		this.driver=ldriver;
		PageFactory.initElements(ldriver, this);
		
	}
	
	
	// Capturing all WebElement of this Page

	@FindBy(xpath="//input[@id='firstname']")
	public WebElement firstNameField;

	
	@FindBy(xpath="//input[@id='lastname']")
	public WebElement lastNameField;
	
	
	@FindBy(xpath="//input[@id='email_address']")
	public WebElement emailField;
	
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement passwordField;
	
	
	@FindBy(xpath="//input[@id='password-confirmation']")
	public WebElement confirmPasswordField;
	
	
	
	@FindBy(xpath="//button[@title='Create an Account']")
	public WebElement createAccountButton;
	
	
	
	
	// Page Action Methods
	
	
	
	//Enter Text In Registration Fields
	
	public void enterValueInTextField(WebElement element , String elementName, String textPassed) {
		
		if (Assertionss.validateElementDisplayed(driver, element)) {

			element.sendKeys(textPassed);
			
			logger.pass("Text Entered in " + elementName + " : " + textPassed );

		}

		else {

			result = false;
			softAssertion.assertTrue(result, "Asserion Failed : Element Not Present");
			logger.fail( elementName + " Not Found");
		}
		

	}

}
