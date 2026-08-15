package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String testName) {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = testName + "_" + LocalDateTime.now().toString().replace(":", "-") + ".png";
		String destination = "screenshots/" + fileName;
		try {

			Files.copy(source.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {

			e.printStackTrace();

		}

		return destination;

	}

	public static String captureElementScreenshot(WebElement element, String elementName) {

		File source = element.getScreenshotAs(OutputType.FILE);

		String fileName = elementName + "_" + LocalDateTime.now().toString().replace(":", "-") + ".png";

		String destination = "screenshots/" + fileName;

		try {

			Files.copy(source.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {

			e.printStackTrace();

		}

		return destination;
	}

}
