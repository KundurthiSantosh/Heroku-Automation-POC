package tests;

import static webUtilities.WebControls.getCurrentUrl;
import static webUtilities.WebControls.navigateBackward;
import static webUtilities.WebControls.navigateTo;
import static webUtilities.WebControls.switchWindow;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonUtilities.StepStatus;
import pages.BasicAuthPage;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MultipleWindows;
import pages.NewWindow;
import pages.SecurePage;
import webUtilities.BaseTest;

public class HerokuTests extends BaseTest {

	HomePage homePage = null;
	LoginPage loginPage = null;
	SecurePage securePage = null;
	MultipleWindows multipleWindowsPage = null;
	NewWindow newWindowPage = null;
	ForgotPasswordPage forgotPasswordPage = null;
	BasicAuthPage basicAuthPage = null;

	@BeforeMethod
	public void gotoBaseURL(ITestResult result) {
		driver = getDriver();
		driver.manage().window().maximize();
		driver.get(configFileReader.getWebBaseUrl());
		reporter.startTest(result.getMethod().getMethodName());
	}

	@AfterMethod
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void formAuthenticationTest() {

		homePage = new HomePage();
		loginPage = new LoginPage();
		securePage = new SecurePage();

		if (homePage.isFormAuthenticationAvailable())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Form Authentication available on Home page");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Form Authentication is not available on Home page");

		if (homePage.clickFormAuthentication())
			reporter.logStep(StepStatus.PASS, "Clicked on Form Authentication Link");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on Form Authentication Link");

		if (loginPage.enterUserName(loginUserName) && loginPage.enterPassword(loginPassword))
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Entered Username and Password");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to entered Username and Password");

		if (loginPage.clickLogin())
			reporter.logStep(StepStatus.PASS, "Clicked on Login button");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on Login button");

		if (securePage.isSecureAreaHeadingAvailable()) {
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Login Successful");
			if (securePage.logout()) {
				reporter.logStep(StepStatus.PASS, "Clicked on logout");
				if (loginPage.isLogoutSuccessful())
					reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Logout successful");
				else
					reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to logout");
			} else
				reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on logout");
		} else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Login failed");

	}

	@Test
	public void multipleWindowsTest() {
		homePage = new HomePage();
		multipleWindowsPage = new MultipleWindows();
		newWindowPage = new NewWindow();

		if (homePage.isMultipleWindowsAvailable())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Multiple Windows available on Home page");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Multiple Windows is not available on Home page");

		if (homePage.clickMultipleWindows())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Clicked on Multiple Windows Link");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on Multiple Windows Link");

		if (multipleWindowsPage.clickMultipleWindows())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Clicked on Multiple Windows link");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on Multiple Windows link");

		String parentWindow = switchWindow();

		if (newWindowPage.isNewWindowTextMatching())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Successfully switched to new window");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to switch to new window");

		switchWindow(parentWindow);

		if (multipleWindowsPage.isMultipleWindowsLinkAvailable())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Successfully switched to original window");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to switch to original window");

	}

	@Test
	public void forgotPasswordTest() {
		homePage = new HomePage();
		forgotPasswordPage = new ForgotPasswordPage();

		if (homePage.isForgotPasswordAvaiable())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Forgot Password link available on Home page");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Forgot Password is not available on Home page");

		if (homePage.clickForgotPassword())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Clicked on Forgot Password Link");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on Forgot Password Link");

		if (forgotPasswordPage.enterEmail("santosh@test.com"))
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Entered email in Email text box");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to enter Email in Email text box");

		if (forgotPasswordPage.clickRetrievePassword())
			reporter.logStep(StepStatus.PASS, "Clicked on Retireve Password button");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on Retrieve Password Button");

		navigateBackward();

		if (forgotPasswordPage.isEmailTextBoxAvaiable())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Navigated back to Forgot Password page");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to navigated back to Forgot Password page");

		navigateBackward();

		if (homePage.isForgotPasswordAvaiable())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Navigated back to original page");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to navigated back to original page");

	}

	@Test
	public void basicAuthTest() {

		homePage = new HomePage();
		basicAuthPage = new BasicAuthPage();

		String currentUrl = getCurrentUrl();
		String updatedUrl = currentUrl.substring(0, 8) + authUserName + ":" + authPassword + "@" + currentUrl.substring(8);
		navigateTo(updatedUrl);

		if (homePage.clickBasicAuth())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Clicked on Basic Auth link");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Unable to click on Basic Auth link");

		if (basicAuthPage.isAuthSuccess())
			reporter.logStep(StepStatus.PASS_WITH_SCREENSHOT, "Authentication successful");
		else
			reporter.logStep(StepStatus.FAIL_WITH_SCREENSHOT, "Authentication Failure");

	}

}
