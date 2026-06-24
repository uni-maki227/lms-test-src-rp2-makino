package jp.co.sss.lms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HelpPage {

	@FindBy(partialLinkText = "よくある質問")
	private WebElement faqLink;

	public HelpPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickFaq() {
		faqLink.click();
	}
}
