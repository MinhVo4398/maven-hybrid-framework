package com.facebook.register;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGenerator;

public class Level_13_Element_Undisplayed extends BaseTest {
	private WebDriver driver;

	private LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		// Nếu 1 cái hàm chỉ mong đợi verify element displayed thôi- kết hợp cả wait +
		// isDisplay
		// waitForElementVisible
		// isElementDisplayed

		// Verify True - mong đợi Confirm Email displayed (true)
		loginPage.enterToEmailAddressTextbox("automationfc@gmail.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplay());

	}

	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		// Nếu như mình mong đợi 1 hàm vừa verify displayed / undisplayed thì k dc kết
		// hợp wait
		// Vì waitForElementVisible ko đúng trong trường hợp element undisplayed

		// Verify False - mong đợi Confirm Email Undisplayed (false)
		loginPage.enterToEmailAddressTextbox(""); // xoá cái emailtextbox thì element vẫn còn trong DOM
		loginPage.sleepInSecond(3);
		// verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplay());
		
		//Case 2 : Element có trong DOM nhưng ko visible /displayed
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());

	}

	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickToCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);

		// Khi close cái form Register đi thì Confirm Email ko còn trong DOM nữa
		// Nên hàm findElement sẽ bị fail vì ko tìm thấy element
		// 1- Nó sẽ chờ hết timeout của implicit: 30s
		// 2- Nó sẽ đánh fail testcase tại đúng step này luôn
		// 3 - Ko có chạy các step còn lại nữa

		// Verify False - mong đợi Confirm Email Undisplayed (False)
		// isDisplayed : bản chất ko kiểm tra 1 element ko có trong DOM dc

		// verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplay());
		
		//Case 3 - Element không có trong DOM
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();

	}

}
