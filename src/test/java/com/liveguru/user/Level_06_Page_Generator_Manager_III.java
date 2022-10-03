package com.liveguru.user;

import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.RegisterPageObject;
import pageObjects.liveGuru.PageGeneratorManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_06_Page_Generator_Manager_III extends BaseTest {
	//Declare
	private WebDriver driver;

	//Declare + Init
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyDashboardPageObject myDashboardPage;
	
	private String projectPath = System.getProperty("user.dir"); // lấy ra đường dẫn

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
	}
		
	@Test
	public void User_01_Register_To_System() {
		homePage = PageGeneratorManager.getHomePage(driver);
		
		registerPage	= homePage.clickToRegisterLink();
		
		registerPage.inputToFirstNameTextbox("Auto");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailAddress("afc439881@gmail.com");
		registerPage.PasswordTextbox("1234556");
		registerPage.ConfirmPasswordTextbox("1234556");
		
		// registerpage nhấn click registerbutton qua myDashboardpage
		myDashboardPage	 =registerPage.clickToRegisterButton();
		
		
		Assert.assertEquals(myDashboardPage.getRegisterSuccessMessage(),"Thank you for registering with Main Website Store.");
	}
	
	@Test
	public void User_02_Login_To_System() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}





}
