package pageObjects.techpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveTech.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public	UserHomePageObject (WebDriver driver) {
		this.driver =driver;
		
	}

	public UserLoginPageObject openMyAccountLink() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGenerator.getLoginPage(driver);
	}
	

}
