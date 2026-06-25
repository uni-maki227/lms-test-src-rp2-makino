package jp.co.sss.lms.pages;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePage {
	private WebDriver driver;

	@FindBy(className = "dropdown-toggle")
	private WebElement userMenu;

	@FindBy(partialLinkText = "ヘルプ")
	private WebElement helpLink;

	public CoursePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openUserMenu() {
		userMenu.click();
	}

	public void clickHelp() {
		helpLink.click();
	}

	public void clickFirstUnsubmittedDetail() {

		List<WebElement> rows = driver.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			if (row.getText().contains("未提出")) {

				WebElement detailButton = row.findElement(By.cssSelector(".btn.btn-default"));

				// ボタンが画面中央に来るようにスクロール
				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].scrollIntoView({block:'center'});",
						detailButton);

				detailButton.click();
				break;
			}
		}
	}

	public List<WebElement> getRows() {
		return driver.findElements(By.tagName("tr"));
	}

	public String getDate(WebElement row) {
		return row.findElement(By.tagName("td")).getText();
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
