package com.saurcelab.sort;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

import pageObjects.sauceLab.LoginPageObject;
import pageObjects.sauceLab.PageGeneratorManager;
import pageObjects.sauceLab.ProductPageObject;

public class Level_19_Asc_Desc extends BaseTest {
	//Declare
	private WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;
	String username ="standard_user";
	String password ="secret_sauce";
			


	@Parameters({"browser","appUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String saucelabUrl) {
		driver = getBrowserDriver(browserName,saucelabUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.enterToUsernameTextbox(username);
		loginPage.enterToPasswordTextbox(password);
		productPage = loginPage.clickToLoginButton();
		

	}

	@Test
	public void Sort_01_Name() {
		// Ascending
		productPage.selectItemInProductSortDropdown("Name (A to Z)");
		productPage.sleepInSecond(3);
		//Assert.assertTrue(productPage.isProductNameByAscending());
		Assert.assertTrue(productPage.isProductNameSortByAscendingLambda());
		
		
		// Descending
		productPage.selectItemInProductSortDropdown("Name (Z to A)");
		productPage.sleepInSecond(3);
	//	Assert.assertTrue(productPage.isProductNameByDescending());
		Assert.assertTrue(productPage.isProductNameSortByDescendingLambda());
	
	}
	

	
	public void Sort_02_Price() {
	productPage.selectItemInProductSortDropdown("Price (low to high)");
	//productPage.sleepInSecond(3);
	Assert.assertTrue(productPage.isProductPriceSortByAscending());
	
		
	productPage.selectItemInProductSortDropdown("Price (high to low)");
	//productPage.sleepInSecond(3);
	Assert.assertTrue(productPage.isProductPriceSortByDescending());
	
		
	}
	
	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		//closeBrowserAndDriver();
	}






}
