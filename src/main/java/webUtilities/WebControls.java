package webUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebControls extends BaseTest {
	public static Logger log = LogManager.getLogger(WebControls.class);

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public static boolean safeClickElement(By path, int waitTime) {
		boolean bFlag = false;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			ele.click();
			bFlag = true;
		}
		return bFlag;
	}

	public static boolean safeJavaScriptClick(By path, int waitTime) {
		boolean bFlag = false;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ele);
			bFlag = true;
		}
		return bFlag;
	}

	public static boolean safeEnterValue(By path, String sValue, int waitTime) {
		boolean bFlag = false;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			ele.sendKeys(sValue);
			bFlag = true;
		}
		return bFlag;
	}

	public static boolean safeClearandEnterValue(By path, String sValue, int waitTime) {
		boolean bFlag = false;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			ele.clear();
			ele.sendKeys(sValue);
			bFlag = true;
		}
		return bFlag;
	}

	public static String safeGetAttribute(By path, String sAttributeValue, int waitTime) {
		String sReturnValue = null;
		if (Sync.isElementDisplayed(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			sReturnValue = ele.getAttribute(sAttributeValue);
		}
		return sReturnValue;
	}

	public static void switchToIframe(String frameName, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public static String safeGetText(By path, int waitTime) {
		String sText = null;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			sText = ele.getText().trim();
		}
		return sText;
	}

	public static boolean safeSelectByVisibleText(By path, String sVisibleText, int waitTime) {
		boolean bFlag = false;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			Select select = new Select(ele);
			select.selectByVisibleText(sVisibleText);
			bFlag = true;
		}
		return bFlag;
	}

	public static boolean safeSelectByIndex(By path, int iIndex, int waitTime) {
		boolean bFlag = false;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			Select select = new Select(ele);
			select.selectByIndex(iIndex);
			bFlag = true;
		}
		return bFlag;
	}

	public static boolean safeSelecx(By path, String sValue, int waitTime) {
		boolean bFlag = false;
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			Select select = new Select(ele);
			select.selectByValue(sValue);
			bFlag = true;
		}
		return bFlag;
	}

	public static boolean isElementDisplayed(By path, int waitTime) {
		return Sync.isElementDisplayed(path, waitTime);
	}
	public static List<WebElement> getListofElements(By path, int waitTime) {
		List<WebElement> elementList = null;
		boolean bFlag = Sync.isElementPresent(path, waitTime);
		if (bFlag) {
			elementList = driver.findElements(path);
		}
		return elementList;
	}

	public static void pageScrolldown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public static void pageScrollUntilElementIsVisible(By path, int waitTime) {
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		}
	}

	public static void pageScrollUntilElementIsVisibleActions(By path, int waitTime) {
		if (Sync.isElementDisplayed(path, waitTime) && Sync.isEnabled(path, waitTime)) {
			WebElement ele = driver.findElement(path);
			Actions a = new Actions(driver);
			a.moveToElement(ele);
			a.perform();
		}
	}

	public static boolean mouseHover(WebElement element) {
		boolean status = true;
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public static boolean verifyText(WebElement element, String text) {
		boolean status = true;
		try {
			status = scrollToElement(element);
			String appText = element.getText().trim();
			if (!appText.equals(text))
				status = false;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public static boolean isElementEditable(WebElement element) {
		boolean status;
		try {
			scrollToElement(element);
			status = ((element.getAttribute("disabled") != null) || (element.getAttribute("readonly") != null));
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public static String getCurrentDate() {
		String timeStamp = new SimpleDateFormat("MM.dd.yyyy").format(new Date());

		return timeStamp.replace(".", "/");
	}

	public static String getPreviousDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
		// String timeStamp = new SimpleDateFormat("MM.dd.yyyy");

		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		Date previousDate = calendar.getTime();
		String pastDate = dateFormat.format(previousDate);
		return pastDate.replace(".", "/");
	}

	public static String getFutureDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
		// String timeStamp = new SimpleDateFormat("MM.dd.yyyy");

		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, +2);

		Date previousDate = calendar.getTime();
		String pastDate = dateFormat.format(previousDate);
		return pastDate.replace(".", "/");
	}

	public static boolean scrollToElement(WebElement element) {
		boolean status = true;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(3000);
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public static void sendEnterKey(By path) {
		driver.findElement(path).sendKeys(Keys.ENTER);
	}
	
	public static String switchWindow() {
		Set<String> windows = driver.getWindowHandles();
		String currentWindow = driver.getWindowHandle();
		for(String window: windows) {
			if(window != currentWindow)
				driver.switchTo().window(window);
		}
		return currentWindow;
	}
	
	public static void switchWindow(String windowId) {
		driver.switchTo().window(windowId);
	}
	
	public static void navigateForward() {
		driver.navigate().forward();
	}
	
	public static void navigateBackward() {
		driver.navigate().back();
	}
	
	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}
	
	public static void refreshPage() {
		driver.navigate().refresh();
	}
}
