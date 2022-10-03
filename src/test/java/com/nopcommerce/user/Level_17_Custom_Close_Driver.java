package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_17_Custom_Close_Driver extends BaseTest {
	//Declare
	private WebDriver driver;

	private  String  firstName, lastName, validPassword, emailAddress;

	//Declare + Init
	private 	UserHomePageObject homePage ;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;


	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		// Khởi tạo homePage lên trước
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		//Pre-Condition
		firstName ="Automation";
		lastName ="FC";		
		emailAddress = "afc"+ generateFakeNumber()+ "@mail.vn";
		validPassword ="123456";	
		
				// Register
				log.info("Pre-Condition - Step 01: Navigate to 'Register' page");		
				registerPage= homePage.openRegisterPage(); 
				
				log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
				registerPage.inputToFirstNameTextbox(firstName);
				
				log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
				registerPage.inputToLastNameTextbox(lastName);
				
				log.info("Pre-Condition - Step 04: Enter to Email textbox with value is '" + firstName + "'");
				registerPage.inputToEmailTextbox(emailAddress);
				
				log.info("Pre-Condition - Step 05: Enter to Password textbox with value is '" + firstName + "'");
				registerPage.inputToPasswordTextbox(validPassword);
				
				log.info("Pre-Condition - Step 06: Enter to Confirmpassword textbox with value is '" + firstName + "'");
				registerPage.inputToConfirmPasswordTextbox(validPassword);
				
				log.info("Pre-Condition - Step 07: Click to 'Register' button ");
				registerPage.clickToRegisterButton();


				log.info("Pre-Condition - Step 08:  Verify Register success message is displayed");
				Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed....");
		
				
				log.info("Pre-Condition - Step 09: Click to 'Log out' link ");
				homePage =	registerPage.clickToLogoutLink();
				
				// Login
				
				log.info("Pre-Condition - Step 10: Navigate to Login Page ");
				loginPage =	homePage.openLoginPage();
				
				log.info("Pre-Condition - Step 11: Enter to Email textbox with value is '" + validPassword +"' ");
				loginPage.inputToEmailTextbox(emailAddress);
				
				log.info("Pre-Condition - Step 12: Enter to Email textbox ");
				loginPage.inputToPasswordTextbox(validPassword);
				
				log.info("Pre-Condition - Step 13: Click to Login button ");
				homePage = loginPage.clickToLoginButton();
	
	}

	@Test
	public void Search_01_Name() {
		


				
	
	}
	

	@Test
	public void Search_02_Address() {
		
	}

	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}





}
