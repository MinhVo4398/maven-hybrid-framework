package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.HomePageUI;

public class HomePageObject extends BasePage {
	  // Biến global;
    private WebDriver driver;

    //constructor
    public  HomePageObject(WebDriver driver) {
      //  Biến local
        this.driver = driver;
    }

	

	public RegisterPageObject clickToRegisterLink() {
		waitForElementVisible(driver, HomePageUI.ACCOUNT_MENU);
		clickToElement(driver, HomePageUI.ACCOUNT_MENU);
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
    
    
}
