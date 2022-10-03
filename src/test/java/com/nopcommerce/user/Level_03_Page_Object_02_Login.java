package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_Page_Object_02_Login {
	//Declare
	private WebDriver driver;

	private  String  firstName, lastName, validPassword, invalidPassword, existingEmail, invalidEmail, notFoundEmail;

	//Declare + Init
	private 	UserHomePageObject homePage ;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String projectPath = System.getProperty("user.dir"); // lấy ra đường dẫn

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Driver có ID rồi


		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage= new UserHomePageObject(driver);


		firstName ="Automation";
		lastName ="FC";
		validPassword ="123456";
		invalidPassword ="654321";
		existingEmail = "afc"+ generateFakeNumber()+ "@mail.vn";
		invalidEmail ="afc@afc.com@.vn";
		notFoundEmail = "afc"+ generateFakeNumber()+ "@mail.com";

		System.out.println("Pre-condition- Step 01: Click to Register link");
		homePage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);


		System.out.println("Pre-condition - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);


		System.out.println("Pre-condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displayed");


		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");


		System.out.println("Pre-condition - Step 05: Click Log out link");
		registerPage.clickToLogoutLink();

		//click logout thì business nó sẽ quay về trang HomePage
		homePage= new UserHomePageObject(driver);
	}

	@Test
	public void Login_01_Emtpy_Data() {
		System.out.println("HomePage : Step 01 Click login Page");
		homePage.openLoginPage();

		//Từ trang Home -> Click login link -> Qua Trang Login
		loginPage = new UserLoginPageObject(driver);

		System.out.println("LoginPage: Step 02 Click login button");
		loginPage.clickToLoginButton();

		System.out.println("LoginPage: Step 03 Verify Please enter your email");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(),"Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("HomePage : Step 01 Click login Page");
		homePage.openLoginPage();

		//Từ trang Home -> Click login link -> Qua Trang Login
		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login Page: Step 02 Input Invalid Email and Corect Password");
		loginPage.inputToEmailTextbox(invalidEmail);


		System.out.println("Login Page Step 03: Click Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page: Step 04 Verify wronng email");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(),"Wrong email");



	}

	@Test
	public void Login_03_Email_Not_Found() {
		System.out.println("HomePage : Step 01 Click login Page");
		homePage.openLoginPage();
		
		// Từ trang Home -> Click Login link -> Qua Trang Login
		loginPage = new UserLoginPageObject(driver);


		System.out.println("Login Page: Step 02 Input Invalid Email ");
		loginPage.inputToEmailTextbox(notFoundEmail);


		System.out.println("Login Page Step 03: Click Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page Step 04:  Verify");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");


	}
	@Test
	public void Login_04_Existing_Email_Empty_Pasword() {
		System.out.println("HomePage : Step 01 Click login Page");
		homePage.openLoginPage();
		
		// Từ trang Home -> Click Login link -> Qua Trang Login
		loginPage = new UserLoginPageObject(driver);
		
		System.out.println("Login Page: Step 02 Input Existing Email and No input Password");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("");

		System.out.println("Login Page Step 03: Click Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page Step 04:  Verify");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");


	}
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("HomePage : Step 01 Click login Page");
		homePage.openLoginPage();
		
		// Từ trang Home -> Click Login link -> Qua Trang Login
		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login Page: Step 02 Input Existing Email and No input Password");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(invalidPassword);


		System.out.println("Login Page Step 03: Click Login button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page Step 04:  Verify");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Email_Password() {
		System.out.println("HomePage : Step 01 Click login Page");
		homePage.openLoginPage();
		
		// Từ trang Home -> Click Login link -> Qua Trang Login
		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login Page: Step 02 Input Existing Email and No input Password");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(validPassword);


		System.out.println("Login Page Step 03: Click Login button");
		loginPage.clickToLoginButton();

		//Login thành công -> qua trang HomePage
		homePage = new UserHomePageObject(driver);

		System.out.println("HomePahe - Step 04: Verify My account link  hiển thị");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());



	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public int generateFakeNumber() {
		Random rd = new Random();
	return	rd.nextInt(9999);

	}



}
