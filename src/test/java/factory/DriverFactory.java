package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.ConfigReader;

public class DriverFactory {

	private static WebDriver driver;
	private static final Logger logger = LogManager.getLogger(DriverFactory.class);
	public static WebDriver initDriver() {
		
		logger.info("Launching Browser");

		String browser = ConfigReader.getProperty("browser");

		switch (browser.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver();
			logger.info("Chrome Browser Launched Successfully");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			logger.info("Firefox Browser Launched Successfully");
			break;

		case "edge":
			driver = new EdgeDriver();
			logger.info("Edge Browser Launched Successfully");
			break;

		default:
			logger.error("Unsupported Browser : " + browser);
			throw new RuntimeException("Browser not supported : " + browser);

		}
	
		driver.manage().window().maximize();
		logger.info("Browser Maximized");

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("implicitWait"))));

		driver.get(ConfigReader.getProperty("url"));

		return driver;

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {

		if (driver != null) {
			driver.quit();
			driver = null;
		}

	}

}
