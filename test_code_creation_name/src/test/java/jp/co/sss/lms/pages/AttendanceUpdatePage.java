package jp.co.sss.lms.pages;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AttendanceUpdatePage {
	private WebDriver driver;

	@FindBy(css = ".update-button")
	private WebElement updateButton;

	public AttendanceUpdatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setAllWorkinghours(
			String startHour,
			String startMinute,
			String endHour,
			String endMinute) {

		List<WebElement> startHours = webDriver.findElements(By.cssSelector("select[id^='startHour']"));

		for (int i = 0; i < startHours.size(); i++) {

			new Select(webDriver.findElement(By.id("startHour" + i)))
					.selectByValue(startHour);

			new Select(webDriver.findElement(By.id("startMinute" + i)))
					.selectByValue(startMinute);

			new Select(webDriver.findElement(By.id("endHour" + i)))
					.selectByValue(endHour);

			new Select(webDriver.findElement(By.id("endMinute" + i)))
					.selectByValue(endMinute);

		}
	}

	public void setWorkinghours0(
			String startHour,
			String startMinute,
			String endHour,
			String endMinute,
			String blankTime,
			String note) {

		new Select(webDriver.findElement(By.id("startHour0")))
				.selectByValue(startHour);

		new Select(webDriver.findElement(By.id("startMinute0")))
				.selectByValue(startMinute);

		new Select(webDriver.findElement(By.id("endHour0")))
				.selectByValue(endHour);

		new Select(webDriver.findElement(By.id("endMinute0")))
				.selectByValue(endMinute);

		new Select(webDriver.findElement(By.name("attendanceList[0].blankTime")))
				.selectByValue(blankTime);

		webDriver.findElement(By.name("attendanceList[0].note")).sendKeys(note);
	}

	public void clickUpdateButton() {
		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();
	}

}
