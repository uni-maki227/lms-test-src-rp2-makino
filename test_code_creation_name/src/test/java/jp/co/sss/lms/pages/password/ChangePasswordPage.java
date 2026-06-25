package jp.co.sss.lms.pages.password;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {
	private WebDriver driver;

	@FindBy(css = "button[type='submit']")
	private WebElement submitButton;

	public ChangePasswordPage(WebDriver driver) {
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

	public void clickSubmitButton() {
		scrollAndClick(submitButton);

		//		確認モーダルの変更ボタンを押下
		visibilityTimeout(By.id("upd-btn"), 10);
		webDriver.findElement(By.id("upd-btn")).click();
	}

	public void inputPassword(String currentPassword, String Password, String passwordConfirm) {

		webDriver.findElement(By.id("currentPassword")).sendKeys(currentPassword);
		webDriver.findElement(By.id("password")).sendKeys(Password);
		webDriver.findElement(By.id("passwordConfirm")).sendKeys(passwordConfirm);

	}
}
