package pages;

import org.openqa.selenium.By;

import webUtilities.BasePage;

import static webUtilities.WebControls.safeGetText;

public class BasicAuthPage extends BasePage{

	By successMessage = By.tagName("p");
	
	public boolean isAuthSuccess() {
		return safeGetText(successMessage, minWait).contains("Congratulations");
	}
}
