package jp.co.sss.lms.pages.report;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jp.co.sss.lms.ct.util.WebDriverUtils;

public class ReportDetailPage {
	private WebDriver webDriver;

	@FindBy(tagName = "h2")
	private WebElement title;

	@FindBy(xpath = "//h3[text()='報告レポート']/following-sibling::table")
	private WebElement reportDetailTable;

	@FindBy(xpath = "//button[text()='フィードバックコメントを登録する']")
	private WebElement feedbackRegisterButton;

	@FindBy(id = "content_create")
	private WebElement feedbackContent;

	@FindBy(className = "popover-title")
	private WebElement feedbackUser;

	@FindBy(css = ".popover-content p")
	private WebElement registeredFeedback;

	public ReportDetailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public void assertPage() {

		new WebDriverWait(webDriver, Duration.ofSeconds(10))
				.until(ExpectedConditions.titleContains("レポート詳細"));

		assertEquals("レポート詳細 | LMS", webDriver.getTitle());
	}

	public void assertReportText(String expectedText) {
		assertTrue(reportDetailTable.getText().contains(expectedText));
	}

	private void scrollAndClick(WebElement element) {
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				element);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				element);
	}

	public boolean isFeedbackModalDisplayed() {
		return feedbackContent.isDisplayed();
	}

	public void clickFeedbackRegisterButton() {
		scrollAndClick(feedbackRegisterButton);
	}

	public void inputFeedback(String text) {
		feedbackContent.clear();
		feedbackContent.sendKeys(text);
		WebDriverUtils.visibilityTimeout(By.id("content_create"), 10);
		webDriver.findElement(By.cssSelector("#div-modal-create input[value='提出する']")).click();
		webDriver.switchTo().alert().accept();
	}

	public String getFeedbackUser() {
		return feedbackUser.getText();
	}

	public String getFeedbackContent() {
		return registeredFeedback.getText();
	}
}
