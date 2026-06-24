package jp.co.sss.lms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;

	@FindBy(id = "loginId")
	private WebElement loginId;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(css = "input[type='submit']")
	private WebElement loginButton;

	@FindBy(className = "error")
	private WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputLoginId(String id) {
		loginId.clear();
		loginId.sendKeys(id);
	}

	public void inputPassword(String pass) {
		password.clear();
		password.sendKeys(pass);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void login(String id, String pass) {
		inputLoginId(id);
		inputPassword(pass);
		clickLogin();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public boolean isErrorDisplayed() {
		return errorMessage.isDisplayed();
	}
}