package pageObects.techpanda.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveTech.admin.AdminCustomnerPageUI;
import pageUIs.liveTech.admin.AdminLoginPageUI;

public class AdminCustomerPageObject extends BasePage {
	WebDriver driver;
	
	public AdminCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void closePopUp() {
		clickToElement(driver, AdminCustomnerPageUI.ClOSE_POP_UP);
		
		
		
	}

	public void enterToHeaderTextBoxByLabel(String headerTextBox, String value) {
		waitForElementVisible(driver, AdminCustomnerPageUI.TEXTBOX_BY_HEADER, headerTextBox);
		sendkeyToElement(driver, AdminCustomnerPageUI.TEXTBOX_BY_HEADER, value, headerTextBox);
		
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminCustomnerPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminCustomnerPageUI.SEARCH_BUTTON);
		
	}
	
	public boolean checkNameAndEmailDisplay(String name ,String email ) {
		return isElementDisplayed(driver, AdminCustomnerPageUI.NAME_AND_EMAIL, name,email );
		
	}

	public void tickToCheckBox(String firstName) {
		
		waitForElementClickable(driver, AdminCustomnerPageUI.CHECKBOX_BY_NAME, firstName);
		clickToElement(driver, AdminCustomnerPageUI.CHECKBOX_BY_NAME, firstName);
		
	}

	public void actionByName(String actionName) {
		waitForElementVisible(driver, AdminCustomnerPageUI.ACTION_BY_NAME);
		selectItemInDefaultDropdown(driver,  AdminCustomnerPageUI.ACTION_BY_NAME,actionName );
		
	}
	
	public void clickToSubmitButton() {
		waitForElementVisible(driver, AdminCustomnerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, AdminCustomnerPageUI.SUBMIT_BUTTON);
	}

	public void clickToAcceptAlert() {
		
		waitForAlertPresent(driver);
		accepptAlert(driver);
	}

	public boolean checkTotalRecordsMessageDisplay() {
		return isElementDisplayed(driver, AdminCustomnerPageUI.TOTAL_RECORDS_MESSAGE );
	}
	
	
}
