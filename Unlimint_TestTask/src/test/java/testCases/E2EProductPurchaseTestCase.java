package testCases;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import pages.CheckoutPage;
import pages.CreateAnAccountPage;
import pages.LumaHomePage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import pojo.GetUser;
import utilities.APIResources;
import utilities.Assertionss;
import utilities.Helper;


@Test()
public class E2EProductPurchaseTestCase extends BaseClass {


	LumaHomePage homePage;
	CreateAnAccountPage createAccount;
	ProductPage productPage;
	ShoppingCartPage shoppingCartPage;
	CheckoutPage checkoutPage;


	@BeforeClass
	public void getFlowName() {

		
		// Set Parent Test / Test Suite Title in Report
		
		parentTest=report.createTest(LoggerERMessages.getDataFromPropertyFile("lumaTestCase"),LoggerERMessages.getDataFromPropertyFile("lumaTestCaseDescription"));

	}


	// E2E Product Purchase Test Case

	@Test()
	public void ValidateE2EProductPurchaseTestCase() throws Exception {



		// declare obeject of POM classes

		homePage = new LumaHomePage(driver);
		createAccount = new CreateAnAccountPage(driver);
		productPage = new ProductPage(driver);
		shoppingCartPage = new ShoppingCartPage(driver);
		checkoutPage = new CheckoutPage(driver);


		// object of enum
		APIResources resource;


		// soft assertion to validate the response
		softAssertion = new SoftAssert();


		// initial value of result variable
		result= true;



		// Set Test Case in Report

		logger= parentTest.createNode(LoggerERMessages.getDataFromPropertyFile("e2eProductPurchaseTestCase"));
		logger.info(LoggerERMessages.getDataFromPropertyFile("LoggerInfoMessge"));


		// logs for console and extent report
		Reporter.log("Calling random user api to get and set user data", true);    // console log
		logger.info("Calling random user api to get and set user data");          // info log in report


		
		// Call Get Random User API


		// Set base URL

		RestAssured.baseURI="https://randomuser.me";


		// get resource uri from enum

		resource=APIResources.valueOf("GetUserAPI");



		// call get user api and store/retrieve json response into java object

		GetUser gu= given().when()
				.get(resource.getResource()).as(GetUser.class);


		
		// Set and update required user data into config file everytime when get user api calls
		
		Config.setDataIntoPropertyFile("firstName", gu.getResults().get(0).getName().getFirst());
		Config.setDataIntoPropertyFile("lastName", gu.getResults().get(0).getName().getLast());
		Config.setDataIntoPropertyFile("email", gu.getResults().get(0).getEmail());
		Config.setDataIntoPropertyFile("streetAddress", gu.getResults().get(0).getLocation().getStreet().getName());
		Config.setDataIntoPropertyFile("city", gu.getResults().get(0).getLocation().getCity());
		Config.setDataIntoPropertyFile("zip", gu.getResults().get(0).getLocation().getPostcode());
		Config.setDataIntoPropertyFile("phoneNumber", gu.getResults().get(0).getPhone());

		
		Reporter.log("User data stored and updated in config file", true);


		
		
		// Open Test Site Page

		Helper.openTestSitePage(Config.getDataFromPropertyFile("LumaSiteURL"));



		//Check LUMA Site Home Page Opened Successfully


		Assertionss.validatePageSourceContainsText(driver, "This is a demo store");



		// All Below Are Test Steps -------------------------------------------->> 



		// Click home page header create an account button

		Helper.clickPageLinkOption(homePage.createAcccountLink, "Create account header link");



		// Wait for page to load completely

		Helper.WaitForPageLoadTime(driver);



		// Test Steps : Create account with user data


		// Enter First Name

		createAccount.enterValueInTextField(createAccount.firstNameField, "First Name", Config.getDataFromPropertyFile("firstName"));



		// Enter Last Name

		createAccount.enterValueInTextField(createAccount.lastNameField, "Last Name", Config.getDataFromPropertyFile("lastName"));




		// Enter Email

		createAccount.enterValueInTextField(createAccount.emailField, "Email", Config.getDataFromPropertyFile("email"));



		// Enter Password

		createAccount.enterValueInTextField(createAccount.passwordField, "Password", Config.getDataFromPropertyFile("password"));




		// Enter Confirm Password

		createAccount.enterValueInTextField(createAccount.confirmPasswordField, "Confirm Password", Config.getDataFromPropertyFile("confirmPassword"));




		// Click Create an Account

		Helper.clickPageLinkOption(createAccount.createAccountButton, "Create account Button");




		// Now click LUMA Site Logo

		Helper.clickPageLinkOption(homePage.homePageLogoLUMA, "LUMA Logo" );



		// Click at First Product under HOT Sellers

		Helper.clickPageLinkOption(homePage.firstProductListed, "First Product Under Hot Sellers");




		// Select Product Size

		Helper.clickPageLinkOption(productPage.sizeButton, "Size Button");




		// Select Product Color

		Helper.clickPageLinkOption(productPage.colorButton, "Color Button");




		// Click Add To Cart

		Helper.clickPageLinkOption(productPage.addToCartButton, "Add To Cart Button");



		// Click On Shopping Cart Option

		Helper.clickPageLinkOption(productPage.shoppingCartLink, "Go To Shopping Cart Option");




		// Click Proceed to Checkout Option

		Helper.clickPageLinkOption(shoppingCartPage.proceedToCheckOutOptionButton, "Proceed To Check Out Option Button");



		// Enter Street Address at checkout page Address Fields

		checkoutPage.enterValueInTextField(checkoutPage.streetAddressField, "Street Address", Config.getDataFromPropertyFile("streetAddress"));



		// Enter City at checkout page Address Fields

		checkoutPage.enterValueInTextField(checkoutPage.cityField, "City Field", Config.getDataFromPropertyFile("city"));


		// Select State at checkout page Address Fields

		Helper.selectByValueDropDown(checkoutPage.stateDropDown, "12");


		// Enter Zip Code at checkout page Address Fields

		checkoutPage.enterValueInTextField(checkoutPage.zipCodeField, "Zip Code", Config.getDataFromPropertyFile("zip"));


		// Enter Phone Number at checkout page Address Fields

		checkoutPage.enterValueInTextField(checkoutPage.phoneNumberField, "Phone Number", Config.getDataFromPropertyFile("phoneNumber"));



		// Select Shipping Method checkout page

		Helper.clickPageLinkOption(checkoutPage.shippingMethodRadio, "Shipping Method");



		// Click Next Button at checkout page

		Helper.clickPageLinkOption(checkoutPage.nextButton, "Next Button");



		// Click on place order at checkout Page and wait for page to be loaded completely

		Helper.clickPageLinkOption(checkoutPage.placeOrderButton, "Place Order Button");




		// Verify success URL and thank you message received with an order number

		Helper.ExplicitWaitForURLContains(driver, Config.getDataFromPropertyFile("expSuccessURL"));
		Assertionss.validatePageSourceContainsText(driver, "Thank you for your purchase");

		

		//Capture Order Number in Report
		logger.pass("Order Paced With Order Number: "  + checkoutPage.orderNumber.getText());

		

		// Validate assertion response
		softAssertion.assertAll();

	}

}
