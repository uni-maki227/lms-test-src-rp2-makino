package jp.co.sss.lms.pages.exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamStartPage {

	private WebDriver driver;

	@FindBy(css = "input[value='試験を開始する']")
	private WebElement startButton;

	public ExamStartPage(WebDriver driver) {
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

	public void clickStartButton() {
		scrollAndClick(startButton);
	}

	private WebElement getLastExamResultRow() {
		List<WebElement> rows = driver.findElements(
				By.xpath("//h3[text()='過去の試験結果']/following::table[1]//tr[position()>1]"));

		return rows.get(rows.size() - 1);
	}

	public String getLastExamResultDate() {
		return getLastExamResultRow()
				.findElements(By.tagName("td"))
				.get(3)
				.getText();
	}

	public void scrollToLastExamResult() {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				getLastExamResultRow());
	}
}
