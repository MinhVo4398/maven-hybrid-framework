package com.nopcommerce.user;


import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_16_Share_Data_C extends BaseTest {
	//Declare
	private WebDriver driver;
	private  String  validPassword, emailAddress;

	private UserHomePageObject homePage ;
	private UserLoginPageObject loginPage;
	
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		// Khởi tạo homePage lên trước
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		validPassword = Common_01_Register_End_User.emailAddress;
		emailAddress = Common_01_Register_End_User.password;
		
		log.info("Pre-Condition - Step 01: Navigate to Login Page ");
		loginPage =	homePage.openLoginPage();
		
		log.info("Pre-Condition - Step 02: Set cookie and reload page ");
		loginPage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		for (Cookie cookie : Common_01_Register_Cookie.loggedCookies) {
			System.out.println("Cookie at C Class: "+ cookie);
		}
		
		loginPage.refreshCurrentPage(driver);
				
		
		log.info("Pre-Condition - Step 05: Verify 'My Account' link is displayed ");
		verifyTrue(homePage.isMyAccountLinkDisplayed());		

	
	}


	@Test
	public void Search_01_Empty_Data() {
		

	
	}
	@Test
	public void Search_02_Relative () {
		

	
	}
	@Test
	public void Search_03_Absolute_Product_Name() {
		

	
	}
	@Test
	public void Search_04_Parent_Category() {
		

	
	}
	@Test
	public void Search_05_Incorrect_Manufacturer() {
		

	
	}
	@Test
	public void Search_06_Correct_Manufacturer() {
		

	
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();

	}





}
