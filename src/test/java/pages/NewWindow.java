package pages;

import org.openqa.selenium.By;

import webUtilities.BaseTest;

import static webUtilities.WebControls.getPageTitle;
import static webUtilities.WebControls.safeGetText;

public class NewWindow extends BaseTest{

	String newWindowTitle = "New Window";
	By newWindowText = By.tagName("h3");
	
	public boolean isTitleMatching() {
		return getPageTitle().equalsIgnoreCase(newWindowTitle);
	}
	
	public boolean isNewWindowTextMatching() {
		return safeGetText(newWindowText, minWait).equalsIgnoreCase(newWindowTitle);
	}
}
