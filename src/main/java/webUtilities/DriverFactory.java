package webUtilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import commonUtilities.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	ConfigFileReader configFileReader = new ConfigFileReader();
	private static final Supplier<WebDriver> chromeSupplier = () -> {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	};
	private static final Supplier<WebDriver> firefoxSupplier = () -> {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	};

	private static final Map<String, Supplier<WebDriver>> MAP = new HashMap<>();

	static {
		MAP.put("chrome", chromeSupplier);
		MAP.put("firefox", firefoxSupplier);
	}

	public static WebDriver getDriver(String browser) {
		return MAP.get(browser).get();
	}
}
