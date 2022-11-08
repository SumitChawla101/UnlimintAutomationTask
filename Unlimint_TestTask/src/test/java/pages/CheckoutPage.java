package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Assertionss;

public class CheckoutPage extends BaseClass {


	WebDriver driver;

	
	//Below constructor is used to pass the driver.  
	
	
	public CheckoutPage(WebDriver Idriver) {

		this.driver=Idriver;

		PageFactory.initElements(Idriver, this);

	}


	// Capturing all WebElement of this Page
	
	@FindBy(xpath="//input[@name='firstname']")
	public WebElement firstNameField;
	
	
	@FindBy(xpath="//input[@name='lastname']")
	public WebElement lastNameField;
	
	
	@FindBy(xpath="//input[@name='company']")
	public WebElement companyField;
	
	
	@FindBy(xpath="//div[@name='shippingAddress.street.0']//input")
	public WebElement streetAddressField;
	
	
	@FindBy(xpath="//div[@name='shippingAddress.city']//input")
	public WebElement cityField;

	
	
	@FindBy(xpath="(//select[@class='select'])[1]")
	public WebElement stateDropDown;
	
	
	@FindBy(xpath="//div[@name='shippingAddress.postcode']//input")
	public WebElement zipCodeField;
	
	
	@FindBy(xpath="//div[@name='shippingAddress.country_id']//select[@class='select']")
	public WebElement countryDropDown;
	
	
	@FindBy(xpath="//div[@name='shippingAddress.telephone']//input")
	public WebElement phoneNumberField;
	
	
	@FindBy(xpath="//table[@class='table-checkout-shipping-method']//tr[1]//td[1]")
	public WebElement shippingMethodRadio;
	
	
	@FindBy(xpath="//button[@class='button action continue primary']")
	public WebElement nextButton;
	
	
	@FindBy(xpath="(//div[@class='payment-method-content']//div[@class='primary']//button[1])[2]")
	public WebElement placeOrderButton;
	
	
	@FindBy(xpath="//span[@class='base']")
	public WebElement thsnkYouMessage;
	
	
	@FindBy(xpath="//a[@class='order-number']")
	public WebElement orderNumber;
	
	
	
	//Enter Text In Address Fields
	
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
