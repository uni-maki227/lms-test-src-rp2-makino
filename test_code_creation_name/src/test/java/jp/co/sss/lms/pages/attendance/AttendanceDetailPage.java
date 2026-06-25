package jp.co.sss.lms.pages.attendance;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AttendanceDetailPage {
	private WebDriver driver;

	@FindBy(css = "input[value*='出勤']")
	private WebElement punchInButton;

	@FindBy(css = "input[value*='退勤']")
	private WebElement punchOutButton;

	@FindBy(partialLinkText = "勤怠情報を直接編集する")
	private WebElement attendanceUpdateLink;

	public AttendanceDetailPage(WebDriver driver) {
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

	public void clickPunchInButton() {
		scrollAndClick(punchInButton);
		webDriver.switchTo().alert().accept();
	}

	public void clickPunchOutButton() {
		scrollAndClick(punchOutButton);
		webDriver.switchTo().alert().accept();
	}

	public void openAttendanceUpdateLink() {
		attendanceUpdateLink.click();
	}
}
