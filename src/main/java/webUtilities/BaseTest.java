package webUtilities;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest extends BasePage {

	public static WebDriver driver = null;

	public WebDriver getDriver() {

		String hub = System.getProperty("HUB_HOST");
		String browser = System.getProperty("BROWSER");
		DesiredCapabilities dc;
		if (hub == null) {
			if (browser == null || browser.equalsIgnoreCase("chrome"))
				driver = DriverFactory.getDriver("chrome");
			else if (browser.equalsIgnoreCase("firefox"))
				driver = DriverFactory.getDriver(browser);
			else
				return null;
		} else {
			if (browser.equalsIgnoreCase("firefox"))
				dc = DesiredCapabilities.firefox();
			else
				dc = DesiredCapabilities.chrome();
			String completeUrl = "http://" + hub + ":4444/wd/hub";
			try {
				driver = new RemoteWebDriver(new URL(completeUrl), dc);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return driver;
	}

	@BeforeSuite
	public void initializeTests() {
		reporter.intializeReports();
	}
	
	@AfterSuite
	public void flushReports() {
		reporter.flushReport();
	}

}