package jp.co.sss.lms.pages.exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamQuestionPage {
	private WebDriver driver;

	@FindBy(css = "input[value='確認画面へ進む']")
	private WebElement nextButton;

	public ExamQuestionPage(WebDriver driver) {
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

	public void clickNextButton() {
		scrollAndClick(nextButton);
	}

	public void answerQuestions(int[] answers) {

		for (int i = 0; i < answers.length; i++) {

			WebElement radio = driver.findElement(
					By.cssSelector("input[name='answer[" + i + "]'][value='" + answers[i] + "']"));

			scrollAndClick(radio);
		}
	}

	public boolean hasAnswerChoices() {
		return driver.findElements(
				By.cssSelector("input[type='radio']"))
				.isEmpty();
	}
}
