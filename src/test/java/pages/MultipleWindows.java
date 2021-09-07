package pages;

import org.openqa.selenium.By;

import webUtilities.BaseTest;

import static webUtilities.WebControls.safeClickElement;
import static webUtilities.WebControls.isElementDisplayed;

public class MultipleWindows extends BaseTest{

	By multipleWindowsLink = By.xpath("//a[text()='Click Here']");
	
	public boolean isMultipleWindowsLinkAvailable() {
		return isElementDisplayed(multipleWindowsLink, minWait);
	}
	
	public boolean clickMultipleWindows() {
		return safeClickElement(multipleWindowsLink, minWait);
	}
}
