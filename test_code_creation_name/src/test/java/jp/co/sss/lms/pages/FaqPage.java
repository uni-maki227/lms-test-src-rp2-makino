package jp.co.sss.lms.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FaqPage {

	private WebDriver driver;

	@FindBy(id = "form")
	private WebElement keywordForm;

	@FindBy(css = "input[value='検索']")
	private WebElement searchButton;

	@FindBy(css = "input[value='クリア']")
	private WebElement clearButton;

	@FindBy(partialLinkText = "【研修関係】")
	private WebElement trainingCategory;

	@FindBy(className = "fs18")
	private WebElement answer;

	@FindBy(className = "mb10")
	private List<WebElement> questions;

	public FaqPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void search(String keyword) {
		keywordForm.clear();
		keywordForm.sendKeys(keyword);
		searchButton.click();
	}

	public void clear() {
		clearButton.click();
	}

	public String getSearchResultText() {
		StringBuilder sb = new StringBuilder();

		for (WebElement question : questions) {
			sb.append(question.getText());
		}

		return sb.toString();
	}

	public boolean isKeywordEmpty() {
		return keywordForm.getAttribute("value").isEmpty();
	}

	public void scrollToFirstQuestion(WebDriver driver) {
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].scrollIntoView();",
						questions.get(0));
	}

	public void clickTrainingCategory() {
		trainingCategory.click();
	}

	public String getQuestionText() {

		StringBuilder sb = new StringBuilder();

		for (WebElement question : questions) {
			sb.append(question.getText());
		}

		return sb.toString();
	}

	public void clickFirstQuestion() {
		questions.get(0).click();
	}

	public boolean isAnswerDisplayed() {
		return answer.isDisplayed();
	}
}
