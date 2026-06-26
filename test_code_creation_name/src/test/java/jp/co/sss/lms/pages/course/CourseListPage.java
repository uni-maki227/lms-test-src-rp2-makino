package jp.co.sss.lms.pages.course;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CourseListPage {
	private WebDriver driver;

	@FindBy(partialLinkText = "研修管理")
	private WebElement TrainingManagementLink;

	@FindBy(partialLinkText = "受講生")
	private WebElement userLink;

	public CourseListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getRows() {
		return driver.findElements(By.tagName("tr"));
	}

	public void openUserLink() {
		TrainingManagementLink.click();
		userLink.click();
	}

	public void clickDetail(WebElement row) {
		WebElement detailButton = row.findElement(By.cssSelector(".btn.btn-default"));

		// ボタンが画面中央に来るようにスクロール
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				detailButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				detailButton);
	}
}
