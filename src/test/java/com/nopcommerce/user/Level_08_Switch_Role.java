package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.admin.nopCommerce.AdminDashboardPageObject;
import pageObject.admin.nopCommerce.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_08_Switch_Role extends BaseTest {
	//Declare
	private WebDriver driver;

	private  String  userPassword, userEmailAddress, adminEmailAddress, adminPassword;

	//Declare + Init
	private UserHomePageObject userHomePage ;
	
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomerInforPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;



	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
			
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userEmailAddress = "faker4398@gmail.com";
		userPassword ="123456";	
		
		adminEmailAddress ="admin@yourstore.com";
		adminPassword ="admin";
	}

	@Test
	public void Role_01_User_To_Admin() {
		//Home Page -> Login(User)
		userLoginPage =	userHomePage.openLoginPage();
		 
		//Login as User role
		
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);				
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());	
		
		//Home Page -> Customer Infor
		userCustomerInforPage	=userHomePage.openMyAccountPage();
		
		//Customer Infor > click Logout > Home Page
	userHomePage = userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
	
		// User Home Page -> Open Admin Page -> Login Page (Admin)
		userHomePage.openPageUrl(driver,GlobalConstants.ADMIN_TESTING_URL );
		//open ra trang AdminLogin Page -> Khoi tao len 
		adminLoginPage=PageGeneratorManager.getAdminLoginPage(driver);
		
		//Login as Admin Role
		adminDashboardPage	= adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());	
		
		//Dashboard Page -> Click Logout -> Login Page (Admin)
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
								
			
	}

	@Test
	public void Role_02_Admin_To_User() {
		// Login Page (Admin) -> open Portal url -> Home Page (User)
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_TESTING_URL);
		
		// Mở ra trang HomePage (user) khởi tạo nó lên 
		userHomePage  = PageGeneratorManager.getUserHomePage(driver);
		
		//Home Page -> Login(User)
		
		userLoginPage =	userHomePage.openLoginPage();
		
	//Login as User role
		
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);				
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());	
		
	}


	

	@AfterClass
	public void afterClass() {
		driver.quit();

	}





}
