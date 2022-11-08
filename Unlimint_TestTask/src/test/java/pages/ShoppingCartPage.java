package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class ShoppingCartPage extends BaseClass {
	
	
	WebDriver driver;
	
	
	
	public ShoppingCartPage(WebDriver Idriver) {
		
		this.driver=Idriver;
		
		PageFactory.initElements(Idriver, this);
		
	}

	
	@FindBy(xpath="//button[@data-role='proceed-to-checkout']")
	public WebElement proceedToCheckOutOptionButton;
	

}
