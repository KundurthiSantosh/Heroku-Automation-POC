package webUtilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sync extends BaseTest {

	public static boolean isElementDisplayed(By path, int waitTime) {
		nullifyImplicitWait();
		boolean bFlag = false;

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(path));

		bFlag = ele.isDisplayed();

		return bFlag;
	}

	public static boolean isElementPresent(By path, int waitTime) {
		nullifyImplicitWait();
		boolean bFlag = false;

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(path));

		int iSize = driver.findElements(path).size();
		if (iSize > 0)
			bFlag = true;
		else
			bFlag = false;

		return bFlag;
	}

	public static boolean isEnabled(By path, int waitTime) {
		boolean bElementDisplayFlag = isElementDisplayed(path, waitTime);
		boolean bEnableFlag = false;
		WebElement ele = null;
		if (bElementDisplayFlag) {
			ele = driver.findElement(path);
			bEnableFlag = ele.isDisplayed();
		}
		return bEnableFlag;
	}

	public static WebElement getWebElement(By path, int waitTime) {
		WebElement ele = null;
		if (isElementPresent(path, waitTime)) {
			ele = driver.findElement(path);
		}
		return ele;
	}

	private static void nullifyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

}
