package jp.co.sss.lms.pages.user;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AgreeSecurityPage {

	private WebDriver driver;

	public AgreeSecurityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void disagree() {
		//		「次へ」ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
	}

	public void agree() {
		//		同意しますをクリック
		webDriver.findElement(By.name("securityFlg")).click();

		//		「次へ」ボタンを押下
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
	}

	public String getErrorMessage() {
		return driver.findElement(By.className("error"))
				.getText();
	}
}
