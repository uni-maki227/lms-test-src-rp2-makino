package jp.co.sss.lms.pages;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportRegistPage {
	private WebDriver driver;

	@FindBy(className = "form-control")
	private WebElement reportContent;

	@FindBy(css = ".btn.btn-primary")
	private WebElement submitButton;

	@FindBy(id = "content_2")
	private WebElement reportTextArea;

	public ReportRegistPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputDateReport(String text) {
		reportContent.clear();
		reportContent.sendKeys(text);
	}

	public void inpuyWeekReport(String text) {
		reportTextArea.clear();
		reportTextArea.sendKeys(text);
	}

	private void scrollAndClick(WebElement element) {
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				element);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				element);
	}

	public void submit() {
		scrollAndClick(submitButton);
	}

}
