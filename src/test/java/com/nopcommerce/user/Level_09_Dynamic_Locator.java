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

public class Level_09_Dynamic_Locator extends BaseTest {
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

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");

		homePage =	registerPage.clickToLogoutLink();
				
		loginPage =	homePage.openLoginPage();
							
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(validPassword);
	
		homePage = loginPage.clickToLoginButton();

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());		
				
		customerInforPage = homePage.openMyAccountPage();
		
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}
	@Test
	public void User_02_Switch_Page() {
		// Knowledge của Page Object:
		// Một page A khi chuyển qua page B thì phải viết hàm
		//(action: open/click/...: link/button/image/...) để mở page B đó lên
		
		//Customer Infor -> Address
		
	addressPage = customerInforPage.openAddressPage(driver);
		// Address -> My Product Review
	myProductReviewPage = addressPage.openMyProductReviewPage(driver);
	
		
		// My Product Review -> Reward Point
	rewardPointPage = myProductReviewPage.openRewardPoint(driver);
		
		//Reword Point -> Address
	addressPage = rewardPointPage.openAddressPage(driver);
		
		//Address -> Reward Point
	rewardPointPage = addressPage.openRewardPoint(driver);
		
		//Reward Point -> My Product Review
		
	myProductReviewPage =  rewardPointPage.openMyProductReviewPage(driver);
	
		// My Product Review -> Address
	addressPage = myProductReviewPage.openAddressPage(driver);
	
	customerInforPage =	addressPage.openCustomerInforPage(driver);
	
	
	myProductReviewPage =	customerInforPage.openMyProductReviewPage(driver);
	

	}
	
	@Test
	public void User_03_Dynamic_Page_01() {
		//My Product Review -> Reward Point
		rewardPointPage =  (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		// Reward Point -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		
		// Address -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		//  Reward Point -> My product review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		
		// My product review -> Customer Info
	customerInforPage=	(UserCustomerInforPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
		
		
		
	}
	
	@Test
	public void User_03_Dynamic_Page_02() {
		// Cách này k cần switch case nhưng ko có tính kết nối giữa các page 
		// Nhưng vẫn phải đảm bảo là New Page (Khởi tạo page) đó lên
		
		// Customer Info -> My Product Review
		customerInforPage.openPagesAtMyAccountByPageName(driver, "My product reviews");
		
		// Khởi tạo My Product Review Page
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		//My Product Review -> Reward Point
		myProductReviewPage.openPagesAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		
		// Reward Point -> Address
		rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
	
		
		// Address -> Reward Point
		addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		rewardPointPage= PageGeneratorManager.getUserRewardPointPage(driver);
	
	
		
		
	}



	@AfterClass
	public void afterClass() {
		driver.quit();

	}





}
