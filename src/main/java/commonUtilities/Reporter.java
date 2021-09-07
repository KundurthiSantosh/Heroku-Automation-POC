package commonUtilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporter {

	ConfigFileReader configFileReader = new ConfigFileReader();
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	public static String resultFile;

	public void intializeReports() {
		resultFile = createResultFolder() + "/" + configFileReader.getReportsFile();
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(resultFile);
		extent.attachReporter(spark);

		String sProjectName = configFileReader.getProjectName();
		String sEnvironment = configFileReader.getEnvironment();
		String sReleaseName = configFileReader.getReleaseName();

		extent.setSystemInfo("Project Name", sProjectName);
		extent.setSystemInfo("Environment", sEnvironment);
		extent.setSystemInfo("Release Name", sReleaseName);
	}

	public void startTest(String scenarioName) {
		test = extent.createTest(scenarioName);
	}

	public void flushReport() {
		extent.flush();
	}

	public void logStep(StepStatus stepStatus, String stepDescription) {
		switch (stepStatus) {
		case PASS:
			test.createNode(stepDescription).pass("pass");
			break;

		case PASS_WITH_SCREENSHOT:
			test.createNode(stepDescription).pass("pass", MediaEntityBuilder
					.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotBase64()).build());
			break;

		case FAIL:
			test.createNode(stepDescription).fail("fail");
			break;

		case FAIL_WITH_SCREENSHOT:
			test.createNode(stepDescription).fail("fail", MediaEntityBuilder
					.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotBase64()).build());
			break;

		case WARNING:
			test.createNode(stepDescription).warning("warning");
			break;

		case WARNING_WITH_SCREENSHOT:
			test.createNode(stepDescription).warning("warning", MediaEntityBuilder
					.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotBase64()).build());
			break;

		case INFO:
			test.createNode(stepDescription).info("info");
			break;

		case INFO_WITH_SCREENSHOT:
			test.createNode(stepDescription).info("info", MediaEntityBuilder
					.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotBase64()).build());
			break;

		default:
			System.out.println("Invalid reporting status");
			break;
		}
	}

	public String createResultFolder() {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String folder = System.getProperty("user.dir") + configFileReader.getReportsPath() + "/"
				+ timeStamp.replace(".", "-");
		File file = new File(folder);

		if (!file.exists())
			file.mkdirs();

		return folder;
	}

}
