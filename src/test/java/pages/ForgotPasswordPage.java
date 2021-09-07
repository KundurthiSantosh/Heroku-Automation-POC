package pages;

import org.openqa.selenium.By;

import webUtilities.BasePage;
import static webUtilities.WebControls.safeEnterValue;
import static webUtilities.WebControls.safeClickElement;
import static webUtilities.WebControls.isElementDisplayed;

public class ForgotPasswordPage extends BasePage{

	By emailTextBox = By.id("email");
	By retrievePassword = By.id("form_submit");
	
	public boolean isEmailTextBoxAvaiable() {
		return isElementDisplayed(emailTextBox, minWait);
	}
	
	public boolean enterEmail(String email) {
		return safeEnterValue(emailTextBox, email, minWait);
	}
	
	public boolean clickRetrievePassword() {
		return safeClickElement(retrievePassword, minWait);
	}
}
