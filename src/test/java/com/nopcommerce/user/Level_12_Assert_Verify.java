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

public class Level_12_Assert_Verify extends BaseTest {
	//Declare
	private WebDriver driver;

	private  String  firstName, lastName, validPassword, emailAddress;

	//Declare + Init
	private 	UserHomePageObject homePage ;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	
	
	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		// Khởi tạo homePage lên trước
		homePage = PageGeneratorManager.getUserHomePage(driver);
	
		firstName ="Automation";
		lastName ="FC";		
		emailAddress = "afc"+ generateFakeNumber()+ "@mail.vn";
		validPassword ="123456";	
		
	
	}

	@Test
	public void User_01_Register_Login() {
		
		registerPage= homePage.openRegisterPage(); // B= A.action
	
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		System.out.println(emailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);

		registerPage.clickToRegisterButton();

//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed....");

		homePage =	registerPage.clickToLogoutLink();
				
		loginPage =	homePage.openLoginPage();
							
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(validPassword);
	
		homePage = loginPage.clickToLoginButton();

//		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());		
		verifyFalse(homePage.isMyAccountLinkDisplayed());		 // check xem co loi ko
		
				
		customerInforPage = homePage.openMyAccountPage();
		
	// 	Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		verifyFalse(customerInforPage.isCustomerInforPageDisplayed());   // check xem co loi ko
	}
	



	@AfterClass
	public void afterClass() {
		driver.quit();

	}





}
