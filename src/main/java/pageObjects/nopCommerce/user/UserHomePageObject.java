package pageObjects.nopCommerce.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.HomePageUI;

import org.openqa.selenium.WebDriver;

public class UserHomePageObject extends BasePage {
    // Biến global;
    private WebDriver driver;

    //constructor
    public  UserHomePageObject(WebDriver driver) {
      //  Biến local
        this.driver = driver;
    }
    
    @Step("Navigate to Register page")
    public UserRegisterPageObject openRegisterPage() {
        waitForElementClickable(driver,HomePageUI.REGISTER_LINK);
        clickToElement(driver,HomePageUI.REGISTER_LINK);
        //2
    // return new RegisterPageObject(driver);
        
        //3
        return PageGeneratorManager.getUserRegisterPage(driver);

    }
    
    @Step("Navigate to Login page")
    public UserLoginPageObject openLoginPage() {
        waitForElementClickable(driver,HomePageUI.LOGIN_LINK);
        clickToElement(driver,HomePageUI.LOGIN_LINK);
  //2    return  new LoginPageObject(driver);
        return PageGeneratorManager.getUserLoginPage(driver);
       
    }

    @Step("Verify 'My Account' link to displayed")
    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver,HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver,HomePageUI.MY_ACCOUNT_LINK);
    }
    
    @Step("Navigate to 'My Account' page ")
	public UserCustomerInforPageObject openMyAccountPage() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);		
		 clickToElement(driver,HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
		 
	}
}
