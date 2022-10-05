package com.nopcommerce.user;


import com.nop.commerce.data.UserDataMaper;
import commons.BaseTest;
import commons.PageGeneratorManager;
//import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;
//import ultilities.Environment;

public class Level_22_Multiple_Evironment_Owner extends BaseTest {
	//Declare
	private WebDriver driver;
	UserDataMaper userData;

	private  String   emailAddress;
	//Environment environment;
	

	//Declare + Init
	private 	UserHomePageObject homePage ;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	
	
	

	@Parameters({"browser", "environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		//ConfigFactory.setProperty("env",environmentName );
		// Tao ra instance
	//	environment = ConfigFactory.create(Environment.class);
		// Goi ham nay no se lấy appUrl trong file dev properties
		//driver = getBrowserDriver(browserName, environment.appUrl());
	//	System.out.println(environment.appUrl());
	//	System.out.println(environment.appPassword());
	//	System.out.println(environment.appUserName());
		//System.out.println(environment.dbHostname());
	//	System.out.println(environment.dbPassword());
	//	System.out.println(environment.dbUsername());

		// Khởi tạo homePage lên trước
		homePage = PageGeneratorManager.getUserHomePage(driver);
		userData = UserDataMaper.getUserData();
		// Khởi tạo dataFaker
	
		emailAddress =  userData.getEmailAddress()+ generateFakeNumber() + "@fakemail.com";


	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register' page");		
		registerPage= homePage.openRegisterPage(); 
		
		registerPage.clickToRadioButtonByLabel(driver,"Female");
		
		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + userData.getFirstName() + "'");
		registerPage.inputToTextboxByID(driver,"FirstName",userData.getFirstName());
		
		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + userData.getLastName() + "'");
		registerPage.inputToTextboxByID(driver,"LastName",userData.getLastName());
		
		log.info("Register - Step 04: Enter date, month , year");
		registerPage.selectDropdownByName(driver,"DateOfBirthDay", userData.getDate());
		registerPage.selectDropdownByName(driver,"DateOfBirthMonth",userData.getMonth() );
		registerPage.selectDropdownByName(driver,"DateOfBirthYear", userData.getYear() );
		
		log.info("Register - Step 05: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToTextboxByID(driver,"Email",emailAddress);
		
		log.info("Register - Step 06: Tick to checkbox Newsletter");
		registerPage.clickToCheckboxByLabel(driver,"Newsletter");
		
		log.info("Register - Step 07: Enter to Password textbox with value is '" + userData.getPassword() + "'");
		registerPage.inputToTextboxByID(driver,"Password",userData.getPassword());
	
		log.info("Register - Step 08: Enter to Confirmpassword textbox with value is '" + userData.getPassword() + "'");	
		registerPage.inputToTextboxByID(driver,"ConfirmPassword",userData.getPassword());
		
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
		
		log.info("Login - Step 02: Enter to Email textbox with value is '" + emailAddress +"' ");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		
		
		log.info("Login - Step 03: Enter to Password textbox ");
		loginPage.inputToTextboxByID(driver, "Password", userData.getPassword());
		
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
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "FirstName"), userData.getFirstName());
		
		
		log.info("My Account - Step 04: Verify 'Last Name ' value is displayed ");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "LastName"), userData.getLastName());
		
		log.info("My Account - Step 05: Verify 'Email ' value is displayed ");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "Email"), emailAddress);
	}
	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		//closeBrowserAndDriver();
	}






}
