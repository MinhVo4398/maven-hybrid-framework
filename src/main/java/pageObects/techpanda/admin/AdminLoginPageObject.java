package pageObects.techpanda.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.techpanda.user.PageGenerator;
import pageUIs.liveTech.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToUserNameTextBox(String userName) {
		
		waitForElementVisible(driver, AdminLoginPageUI.USER_NAME_TEXT_BOX);
		sendkeyToElement(driver, AdminLoginPageUI.USER_NAME_TEXT_BOX, userName);
		
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXT_BOX, password);
		
	}

	public AdminCustomerPageObject openCustomerPage() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		// open ra trang Admin Customer Page
		return PageGenerator.getAdminCustomerPage(driver);
		
	}
	
	
}
