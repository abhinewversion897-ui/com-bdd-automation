package utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandles {

	private WebDriver driver;

	public WindowHandles(WebDriver driver) {
		this.driver = driver;
	}

	// Get all window handles
	public Set<String> getAllWindowHandles() {
		return driver.getWindowHandles();
	}

	// Get current window handle
	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	// Switch to a window using window title
	public void switchToWindowByTitle(String expectedTitle) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(driver -> {
			for (String handle : driver.getWindowHandles()) {

				driver.switchTo().window(handle);

				if (driver.getTitle().equals(expectedTitle)) {
					return true;
				}
			}

			return false;
		});
	}

	// Switch to a window using URL
	public void switchToWindowByUrl(String expectedUrl) {

		for (String handle : driver.getWindowHandles()) {

			driver.switchTo().window(handle);

			if (driver.getCurrentUrl().contains(expectedUrl)) {
				return;
			}
		}

		throw new RuntimeException("Window with URL '" + expectedUrl + "' was not found.");
	}

	// Switch to child window
	public void switchToChildWindow(String parentWindow) {

		for (String handle : driver.getWindowHandles()) {

			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				return;
			}
		}

		throw new RuntimeException("Child window was not found.");
	}

	// Switch back to parent window
	public void switchToParentWindow(String parentWindow) {
		driver.switchTo().window(parentWindow);
	}

	// Close current window and switch to parent
	public void closeCurrentWindowAndSwitchToParent(String parentWindow) {

		driver.close();

		driver.switchTo().window(parentWindow);
	}

	// Close all child windows and return to parent
	public void closeAllChildWindows(String parentWindow) {

		Set<String> allWindows = driver.getWindowHandles();

		for (String handle : allWindows) {

			if (!handle.equals(parentWindow)) {

				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(parentWindow);
	}

	// Switch to window by index
	public void switchToWindowByIndex(int index) {

		Set<String> handles = driver.getWindowHandles();

		if (index < 0 || index >= handles.size()) {
			throw new IllegalArgumentException("Invalid window index: " + index);
		}

		int counter = 0;

		for (String handle : handles) {

			if (counter == index) {
				driver.switchTo().window(handle);
				return;
			}

			counter++;
		}
	}
}