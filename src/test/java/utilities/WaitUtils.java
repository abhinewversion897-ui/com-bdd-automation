package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	private WebDriver driver;

	private WebDriverWait wait;
	private ConfigReader config;

	public WaitUtils(WebDriver driver) {
		this.driver = driver;

		config = new ConfigReader();

		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicitWait"))));

	}

	public void waitForVisibility(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitForPresence(By locator) {

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public boolean waitForInvisibility(WebElement element) {

		return wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public boolean waitForTextToBePresent(WebElement element, String text) {

		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));

	}

	public boolean waitForTitleContains(String title) {

		return wait.until(ExpectedConditions.titleContains(title));

	}

	public boolean waitForUrlContains(String url) {

		return wait.until(ExpectedConditions.urlContains(url));

	}

	public Alert waitForAlert() {

		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public void waitForFrame(By locator) {

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

	public boolean waitForNumberOfWindows(int number) {

		return wait.until(ExpectedConditions.numberOfWindowsToBe(number));

	}

	public void waitForPageLoad() {

		wait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

	}

	public WebElement fluentWait(By locator) {

		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

		return fluentWait.until(driver -> driver.findElement(locator));

	}

	public List<WebElement> waitForAllElements(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// Custom Timeout
	public WebElement waitForVisibility(WebElement element, int seconds) {

		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

		return customWait.until(ExpectedConditions.visibilityOf(element));

	}

	// Sleep
	public void sleep(int seconds) {

		try {

			Thread.sleep(seconds * 1000);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}
	}

}
