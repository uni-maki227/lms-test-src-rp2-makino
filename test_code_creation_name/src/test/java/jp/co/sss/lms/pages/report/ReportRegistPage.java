package jp.co.sss.lms.pages.report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ReportRegistPage {
	private WebDriver driver;

	@FindBy(className = "form-control")
	private WebElement reportContent;

	@FindBy(css = ".btn.btn-primary")
	private WebElement submitButton;

	// 学習項目
	@FindBy(id = "intFieldName_0")
	private WebElement studyContent;

	//	理解度
	@FindBy(id = "intFieldValue_0")
	private WebElement understandingLevel;

	//	目標の達成度
	@FindBy(id = "content_0")
	private WebElement achievementLevel;

	//	所感
	@FindBy(id = "content_1")
	private WebElement comments;

	//	一週間の振り返り
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

	private void temple() {
		studyContent.clear();
		studyContent.sendKeys("ITリテラシー①");

		new Select(understandingLevel).selectByValue("2");

		achievementLevel.clear();
		achievementLevel.sendKeys("5");

		comments.clear();
		comments.sendKeys("週報のサンプルです。");

		reportTextArea.clear();
		reportTextArea.sendKeys("Case09：受講生　レポート登録（週報）入力チェック");

	}

	//	学習項目が未入力
	public void clearStudyContent() {
		temple();
		studyContent.clear();
		submit();
	}

	//	理解度が未入力
	public void clearUnderstandingLevel() {
		temple();
		new Select(understandingLevel).selectByValue("");
		submit();
	}

	//	目標の達成度の入力
	public void inputAchievementLevel(String text) {
		temple();
		achievementLevel.clear();
		achievementLevel.sendKeys(text);
		submit();
	}

	//	目標の達成度・所感が未入力
	public void clearAchievementLevelAndComments() {
		temple();
		achievementLevel.clear();
		comments.clear();
		submit();
	}

	//	所感・一週間の振り返りの入力
	public void inputCommentsAndReportTextArea(String text) {
		temple();
		comments.clear();
		comments.sendKeys(text);
		reportTextArea.clear();
		reportTextArea.sendKeys(text);
		submit();
	}

}
