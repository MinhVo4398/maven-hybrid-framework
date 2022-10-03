package pageObjects.techpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveTech.user.UserAccountDashBoardPageUI;
import pageUIs.liveTech.user.UserHomePageUI;

public class UserAccountDashboardPageObject extends BasePage {
	private WebDriver driver;
	
	public	UserAccountDashboardPageObject (WebDriver driver) {
		this.driver =driver;
		
	}
	
	public String getSuccessMessageText() {
		waitForAllElementVisible(driver, UserAccountDashBoardPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserAccountDashBoardPageUI.SUCCESS_MESSAGE);
	}

	
	

}
