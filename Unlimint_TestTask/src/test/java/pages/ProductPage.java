package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class ProductPage extends BaseClass {

	WebDriver driver;

	//Below constructor is used to pass the driver.  

	public ProductPage(WebDriver ldriver ) {

		this.driver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	
	
	// Capturing all WebElement of this Page

	
	@FindBy(xpath="(//div[@class=\"swatch-option text\"])[3]")
	public WebElement sizeButton;
	
	
	@FindBy(xpath="(//div[@class=\"swatch-option color\"])[1]")
	public WebElement colorButton;
	
	
	@FindBy(xpath="//button[@id='product-addtocart-button']")
	public WebElement addToCartButton;
	
	
	@FindBy(xpath="//div[@class=\"minicart-wrapper\"]")
	public WebElement cartBuketOption;
	
	
	@FindBy(xpath="//a[normalize-space()='shopping cart']")
	public WebElement shoppingCartLink;
	
	
	
}
