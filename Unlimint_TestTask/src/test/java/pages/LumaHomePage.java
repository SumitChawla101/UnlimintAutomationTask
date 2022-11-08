package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;


public class LumaHomePage extends BaseClass{

	WebDriver driver;
	
	
	
	//Below constructor is used to pass the driver.  

	public LumaHomePage(WebDriver ldriver ) {

		this.driver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	
	// Capturing all WebElement of this Page

	@FindBy(xpath="(//a[contains(text(),'Create an Account')])[1]")
	public WebElement createAcccountLink;
	
	
	@FindBy(xpath="(//span[@class=\"product-image-container\"])[1]")
	public WebElement firstProductListed;
	
	
	
	@FindBy(xpath="//a[@aria-label='store logo']//img")
	public WebElement homePageLogoLUMA;
	
	
	
	
	// We can add all Action Methods below
	
	


}
