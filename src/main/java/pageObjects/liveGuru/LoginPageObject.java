package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class LoginPageObject extends BasePage {
	  private WebDriver driver;

	    //constructor
	    public  LoginPageObject(WebDriver driver) {
	      //  Biến local
	        this.driver = driver;
	    }
	    
}
