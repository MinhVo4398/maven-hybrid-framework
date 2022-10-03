package pageObjects.techpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveTech.user.UserHomePageUI;
import pageUIs.liveTech.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public	UserRegisterPageObject (WebDriver driver) {
		this.driver =driver;
		
	}

	

	public void inputToFirstNameTextBox(String firstName) {
		waitForAllElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXT_BOX, firstName);
		
	}

	

	public void inputToLastNameTextBox(String lastName) {
		waitForAllElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXT_BOX, lastName);
		
	}

	public void inputToEmailTextBox(String emailAddress) {
		waitForAllElementVisible(driver, UserRegisterPageUI.EMAIL_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXT_BOX, emailAddress);
		
	}

	public void inputToPasswordTextBox(String password) {
		waitForAllElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXT_BOX, password);
		
	}

	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		waitForAllElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXT_BOX, confirmPassword);
		
	}
	public UserAccountDashboardPageObject clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return PageGenerator.getAccountDashBoardPage(driver);
	}



}
