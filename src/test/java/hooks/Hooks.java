package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;

public class Hooks {

	private static ExtentReports extent;
	private static ExtentTest test;

	@Before
	public void setUp(Scenario scenario) {

		// Create Extent Report only once
		if (extent == null) {

			String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

			sparkReporter.config().setReportName("BDD Automation Test Report");

			sparkReporter.config().setDocumentTitle("Test Execution Report");

			extent = new ExtentReports();

			extent.attachReporter(sparkReporter);

			extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));

			extent.setSystemInfo("OS", System.getProperty("os.name"));

			extent.setSystemInfo("Java Version", System.getProperty("java.version"));

			extent.setSystemInfo("User", System.getProperty("user.name"));
		}

		// Create test
		test = extent.createTest(scenario.getName());

		test.info("Scenario Started");
		test.info("Scenario: " + scenario.getName());

		// Launch browser
		DriverFactory.initDriver();

		test.info("Browser launched successfully");
	}

	// Screenshot after every Given / When / Then step
	@AfterStep
	public void afterStep(Scenario scenario) {

		WebDriver driver = DriverFactory.getDriver();

		if (driver != null) {

			try {

				// Capture screenshot
				String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

				// Attach screenshot to Extent Report
				test.info("Step Screenshot",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				// Attach screenshot to Cucumber report
				scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
						"Step Screenshot");

			} catch (Exception e) {

				test.warning("Unable to capture step screenshot: " + e.getMessage());
			}
		}
	}

	@After
	public void tearDown(Scenario scenario) {

		WebDriver driver = DriverFactory.getDriver();

		try {

			if (driver != null) {

				// Take final screenshot
				String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

				if (scenario.isFailed()) {

					test.fail(" Test Case Failed",
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

					scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
							"Failure Screenshot");

				} else {

					test.pass("Test Case Passed",
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

					scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
							"Final Screenshot");
				}
			}

		} catch (Exception e) {

			test.warning("Unable to capture final screenshot: " + e.getMessage());
		}

		// Close browser
		DriverFactory.quitDriver();

		// Generate Extent Report
		extent.flush();
	}
}