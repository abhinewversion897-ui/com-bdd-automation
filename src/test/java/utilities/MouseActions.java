package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseActions {

	private WebDriver driver;
	private Actions actions;

	public MouseActions(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
	}

	// Mouse Hover
	public void mouseHover(WebElement element) {
		actions.moveToElement(element).perform();
	}

	// Double Click
	public void doubleClick(WebElement element) {
		actions.doubleClick(element).perform();
	}

	// Right Click
	public void rightClick(WebElement element) {
		actions.contextClick(element).perform();
	}

	// Drag and Drop
	public void dragAndDrop(WebElement source, WebElement target) {
		actions.dragAndDrop(source, target).perform();
	}

	// Click and Hold
	public void clickAndHold(WebElement element) {
		actions.clickAndHold(element).perform();
	}

	// Release
	public void release(WebElement element) {
		actions.release(element).perform();
	}
}