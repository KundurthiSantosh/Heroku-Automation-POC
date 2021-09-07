package pages;

import static webUtilities.WebControls.safeClickElement;
import static webUtilities.WebControls.safeEnterValue;
import static webUtilities.WebControls.isElementDisplayed;

import org.openqa.selenium.By;

import webUtilities.BasePage;

public class LoginPage extends BasePage {

	By userName = By.id("username");
	By password = By.id("password");
	By login = By.xpath("//i[contains(text(),'Login')]");
	By logoutMessage = By.xpath("//div[contains(text(),'You logged out of the secure area!')]");

	public boolean enterUserName(String user) {
		return safeEnterValue(userName, user, minWait);
	}

	public boolean enterPassword(String pass) {
		return safeEnterValue(password, pass, minWait);
	}

	public boolean clickLogin() {
		return safeClickElement(login, minWait);
	}

	public boolean isLogoutSuccessful() {
		return isElementDisplayed(logoutMessage, minWait);
	}

}
