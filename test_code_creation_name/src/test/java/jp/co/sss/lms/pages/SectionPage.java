package jp.co.sss.lms.pages;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SectionPage {
	private WebDriver driver;

	@FindBy(css = "input[value*='を提出する']")
	private WebElement submitReportButton;

	@FindBy(css = "input[value*='提出済み日報']")
	private WebElement submittedDateButton;

	@FindBy(css = "input[value*='提出済み週報']")
	private WebElement weeklyReportButton;

	@FindBy(partialLinkText = "ようこそ")
	private WebElement userMenu;

	public SectionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSubmitReport() {
		submitReportButton.click();
	}

	public void clickweeklyReportButton() {
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				weeklyReportButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				weeklyReportButton);
	}

	public boolean isSubmitted() {
		return submittedDateButton
				.getAttribute("value")
				.contains("提出済み");
	}

	public boolean hasWeeklyReport() {

		List<WebElement> weeklyReports = driver.findElements(
				By.cssSelector("input[value*='提出済み週報']"));

		return !weeklyReports.isEmpty();
	}

	public String getReportDate() {

		return driver.findElement(By.cssSelector("#sectionDetail h2 small"))
				.getText()
				.trim();
	}

	public void openWeeklyReport() {
		weeklyReportButton.click();
	}

	public void clickSubmitUserLink() {
		userMenu.click();
	}
}
