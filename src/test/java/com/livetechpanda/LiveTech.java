package com.livetechpanda;


import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObects.techpanda.admin.AdminCustomerPageObject;
import pageObects.techpanda.admin.AdminLoginPageObject;
import pageObjects.techpanda.user.UserAccountDashboardPageObject;
import pageObjects.techpanda.user.UserHomePageObject;
import pageObjects.techpanda.user.UserLoginPageObject;
import pageObjects.techpanda.user.PageGenerator;
import pageObjects.techpanda.user.UserRegisterPageObject;



public class LiveTech extends BaseTest {
	private String firstName, lastName, emailAddress, passWord, confirmPassword;	
		
	
	//Declare
	private WebDriver driver;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	UserRegisterPageObject registerPage;
	UserAccountDashboardPageObject accountDashboardPage;
	AdminLoginPageObject adminLoginPage;
	AdminCustomerPageObject adminCustomerPage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		emailAddress = "afc"+ generateFakeNumber() + "@mail.com";
		firstName = "Jonny1";
		lastName ="DeepFake1";
		passWord ="123456789";
		confirmPassword ="123456789";
		
		driver = getBrowserDriver(browserName, appUrl);
		//driver lấy xong ở hàm trên map vô getHomePage
		
		homePage = PageGenerator.getHomePage(driver);
	}

	@Test
	public void TC_01_Register_Account() {
			//homePage click To MyAccountLink -> Login Page
		loginPage = homePage.openMyAccountLink();
		
		//loginPage click CreateAnAccountButton -> RegisterPage
		registerPage =	loginPage.clickToCreateAnAccountButton();
		
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.sleepInSecond(2);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.sleepInSecond(2);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.sleepInSecond(2);
		registerPage.inputToPasswordTextBox("123456789");
		registerPage.sleepInSecond(2);
		registerPage.inputToConfirmPasswordTextBox("123456789");
		registerPage.sleepInSecond(2);
		
		//registerPage click to registerButton -> AccountDashboardPage
		accountDashboardPage	 = registerPage.clickToRegisterButton();
		Assert.assertEquals(accountDashboardPage.getSuccessMessageText(), "Thank you for registering with Main Website Store.");
		
		
	}
	@Test
	public void TC_02_Login_To_Admin_Page() {
		String userName = "user01";
		String password ="guru99com";
		
		accountDashboardPage.openPageUrl(driver, GlobalConstants.ADMIN_LIVETECH_PANDA);
		// open ra trang Admin -> Khởi tạo trang Admin Login ra
		adminLoginPage = PageGenerator.getAdminLoginPage(driver);
		
		adminLoginPage.inputToUserNameTextBox(userName);
		adminLoginPage.inputToPasswordTextBox(password);
		
		// adminloginPage click Login --> AdminCustomerPage 
		adminCustomerPage =	adminLoginPage.openCustomerPage();
		
		
	}
	
	@Test
	public void TC_03_Handle_Table_In_Admin_CustomerPage() {
		adminCustomerPage.closePopUp();
		adminCustomerPage.enterToHeaderTextBoxByLabel("Email",emailAddress);
		adminCustomerPage.sleepInSecond(3)	;
		adminCustomerPage.clickToSearchButton();
		adminCustomerPage.sleepInSecond(3)	;
		
		Assert.assertTrue(adminCustomerPage.checkNameAndEmailDisplay(firstName, emailAddress));
		adminCustomerPage.tickToCheckBox(firstName);
		adminCustomerPage.actionByName("Delete");
		adminCustomerPage.clickToSubmitButton();
		adminCustomerPage.clickToAcceptAlert();
		
		Assert.assertTrue(adminCustomerPage.checkTotalRecordsMessageDisplay());
	}


	@AfterClass
	public void afterClass() {
	//driver.quit();

	}

	public void generateFakeEmail() {
		Random rd =new Random();
	}



}
