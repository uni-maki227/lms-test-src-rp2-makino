package jp.co.sss.lms.pages.exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamResultPage {
	private WebDriver driver;

	@FindBy(css = "input[value='戻る']")
	private WebElement backButton;

	public ExamResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private void scrollAndClick(WebElement element) {
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				element);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				element);
	}

	public void clickBackButton() {
		scrollAndClick(backButton);
	}

	public boolean hasScore() {
		return driver.findElement(By.cssSelector("h2 small"))
				.getText()
				.contains("あなたのスコア");
	}
}
