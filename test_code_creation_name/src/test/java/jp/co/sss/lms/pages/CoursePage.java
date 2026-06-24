package jp.co.sss.lms.pages;

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
		PageFactory.initElements(driver, this);
	}

	public void openUserMenu() {
		userMenu.click();
	}

	public void clickHelp() {
		helpLink.click();
	}
}
