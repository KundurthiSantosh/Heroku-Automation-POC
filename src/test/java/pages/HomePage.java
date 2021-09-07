package pages;

import static webUtilities.Sync.isElementPresent;
import static webUtilities.WebControls.pageScrollUntilElementIsVisible;
import static webUtilities.WebControls.safeClickElement;

import org.openqa.selenium.By;

import webUtilities.BasePage;

public class HomePage extends BasePage{

    By formAuthenticationLink = By.xpath("//a[text()='Form Authentication']");
    By multipleWindowsLink = By.xpath("//a[text()='Multiple Windows']");
    By forgotPasswordLink = By.xpath("//a[text()='Forgot Password']");
    By basicAuthLink = By.xpath("//a[text()='Basic Auth']");
    
    public boolean isFormAuthenticationAvailable(){
        return isElementPresent(formAuthenticationLink, minWait);
    }
    
    public boolean isMultipleWindowsAvailable(){
        return isElementPresent(formAuthenticationLink, minWait);
    }
    
    public boolean isForgotPasswordAvaiable(){
        return isElementPresent(forgotPasswordLink, minWait);
    }

    public boolean clickFormAuthentication(){
        return safeClickElement(formAuthenticationLink, minWait);
    }
    
    public boolean clickMultipleWindows(){
    	pageScrollUntilElementIsVisible(multipleWindowsLink, minWait);
        return safeClickElement(multipleWindowsLink, minWait);
    }
    
    public boolean clickForgotPassword(){
    	pageScrollUntilElementIsVisible(forgotPasswordLink, minWait);
        return safeClickElement(forgotPasswordLink, minWait);
    }
    
    public boolean clickBasicAuth(){
    	pageScrollUntilElementIsVisible(basicAuthLink, minWait);
        return safeClickElement(basicAuthLink, minWait);
    }

}
