package jp.co.sss.lms.pages;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportDetailPage {
	private WebDriver webDriver;

	@FindBy(tagName = "h2")
	private WebElement title;

	@FindBy(xpath = "//h3[text()='報告レポート']/following-sibling::table")
	private WebElement reportDetailTable;

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
}
