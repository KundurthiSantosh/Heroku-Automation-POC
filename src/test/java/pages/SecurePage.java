package pages;

import static webUtilities.Sync.isElementPresent;
import static webUtilities.WebControls.safeClickElement;

import org.openqa.selenium.By;

import webUtilities.BasePage;

public class SecurePage extends BasePage {

	By secureAreaHeading = By.xpath("//h2[contains(text(),'Secure Area')]");
	By logout = By.xpath("//i[contains(text(),'Logout')]");

	public boolean isSecureAreaHeadingAvailable() {
		return isElementPresent(secureAreaHeading, minWait);
	}

	public boolean logout() {
		return safeClickElement(logout, minWait);
	}
}
