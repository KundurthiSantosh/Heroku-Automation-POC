package commonUtilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import webUtilities.BaseTest;


public class ScreenshotUtil extends BaseTest{

	public static String getScreenshotBase64() {	
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
	
}
