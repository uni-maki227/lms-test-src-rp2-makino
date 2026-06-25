package jp.co.sss.lms.pages.user;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetail {
	private WebDriver driver;

	@FindBy(xpath = "//h3[text()='レポート']/following-sibling::table")
	private WebElement reportTable;

	public UserDetail(WebDriver driver) {
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

	public void openDetail(String reportDate) {

		List<WebElement> rows = reportTable.findElements(By.tagName("tr"));

		String targetDate = reportDate.replaceAll("\\(.+\\)", "");

		for (WebElement row : rows) {

			if (row.getText().contains(targetDate)) {

				WebElement detailButton = row.findElement(By.cssSelector("input[value='詳細']"));

				scrollAndClick(detailButton);
				break;
			}
		}
	}

	public void openReportRegit() {
		List<WebElement> rows = reportTable.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			if (row.getText().contains("週報")) {

				WebElement correctionButton = row.findElement(By.cssSelector("input[value='修正する']"));

				scrollAndClick(correctionButton);

				break;
			}

		}
	}
}
