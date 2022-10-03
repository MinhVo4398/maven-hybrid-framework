package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nop.commerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import ultilities.DataHelper;

public class Level_20_Manage_Data_Part_3 extends BaseTest {
	//Declare
	private WebDriver driver;
	private DataHelper dataFaker;

	private  String  firstName, lastName, validPassword, emailAddress;
	private String date, month, year;

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
		// Khởi tạo dataFaker
		dataFaker = DataHelper.getDataHelper();
		emailAddress = UserData.Register.EMAIL_ADDRESS + generateFakeNumber() + "@fakemail.com";
		
		

	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register' page");		
		registerPage= homePage.openRegisterPage(); 
		
		registerPage.clickToRadioButtonByLabel(driver,"Female");
		
		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + UserData.Register.FIRST_NAME + "'");
		registerPage.inputToTextboxByID(driver,"FirstName",UserData.Register.FIRST_NAME);
		
		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + UserData.Register.LAST_NAME + "'");
		registerPage.inputToTextboxByID(driver,"LastName",UserData.Register.LAST_NAME);
		
		log.info("Register - Step 04: Enter date, month , year");
		registerPage.selectDropdownByName(driver,"DateOfBirthDay", UserData.Register.DATE);
		registerPage.selectDropdownByName(driver,"DateOfBirthMonth", UserData.Register.MONTH);
		registerPage.selectDropdownByName(driver,"DateOfBirthYear", UserData.Register.YEAR);
		
		log.info("Register - Step 05: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToTextboxByID(driver,"Email",emailAddress);
		
		log.info("Register - Step 06: Tick to checkbox Newsletter");
		registerPage.clickToCheckboxByLabel(driver,"Newsletter");
		
		log.info("Register - Step 07: Enter to Password textbox with value is '" + UserData.Register.PASSWORD + "'");
		registerPage.inputToTextboxByID(driver,"Password",UserData.Register.PASSWORD);
	
		log.info("Register - Step 08: Enter to Confirmpassword textbox with value is '" + UserData.Register.PASSWORD + "'");	
		registerPage.inputToTextboxByID(driver,"ConfirmPassword",UserData.Register.PASSWORD);
		
		registerPage.sleepInSecond(5);
		
	
		
		log.info("Register - Step 09: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register - Step 10:  Verify Register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	
				
	
	}
	

	@Test
	public void User_02_Login() {
		
		
		log.info("Login - Step 01: Navigate to Login Page ");
		
		homePage =	registerPage.clickToLogoutLink();
		loginPage =	homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email textbox with value is '" + validPassword +"' ");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		
		
		log.info("Login - Step 03: Enter to Password textbox ");
		loginPage.inputToTextboxByID(driver, "Password", UserData.Register.PASSWORD);
		
		log.info("Login - Step 04: Click to Login button ");
		
		loginPage.clickToButtonByText(driver,"Log in");  
		homePage = PageGeneratorManager.getUserHomePage(driver); // Từ login chuyển qua homePage => khởi tạo HomePage lên


		log.info("Login - Step 05: Verify 'My Account' link is displayed ");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());		
		
		
	}
	
	@Test
	
	public void User_03_My_Account() {
		log.info("My Account - Step 01: Navigate to 'My Account' page ");		
		customerInforPage = homePage.openMyAccountPage();
		
	
		log.info("My Account - Step 02: Verify 'Customer Infor' page is displayed ");
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());   
		
		log.info("My Account - Step 03: Verify 'First Name ' value is displayed ");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "FirstName"), UserData.Register.FIRST_NAME);
		
		
		log.info("My Account - Step 04: Verify 'Last Name ' value is displayed ");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "LastName"), UserData.Register.LAST_NAME);
		
		log.info("My Account - Step 05: Verify 'Email ' value is displayed ");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "Email"), emailAddress);
	}
	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		//closeBrowserAndDriver();
	}






}
