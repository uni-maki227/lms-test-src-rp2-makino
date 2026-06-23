package jp.co.sss.lms.ct.f04_attendance;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 結合テスト 勤怠管理機能
 * ケース11
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース11 受講生 勤怠直接編集 正常系")
public class Case11 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		goTo("http://localhost:8080/lms");

		assertEquals("ログイン | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		goTo("http://localhost:8080/lms");

		//		ログイン
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StudentBB01");

		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「勤怠」リンクから勤怠管理画面に遷移")
	void test03() {
		// TODO ここに追加
		webDriver.findElement(By.partialLinkText("勤怠")).click();
		webDriver.findElement(By.partialLinkText("勤怠情報を直接編集する")).click();

		List<WebElement> rows = webDriver.findElements(By.cssSelector("tbody tr"));

		//		すべて空欄にする
		for (int i = 0; i < rows.size(); i++) {
			new Select(webDriver.findElement(By.id("startHour" + i))).selectByValue("");
			new Select(webDriver.findElement(By.id("startMinute" + i))).selectByValue("");
			new Select(webDriver.findElement(By.id("endHour" + i))).selectByValue("");
			new Select(webDriver.findElement(By.id("endMinute" + i))).selectByValue("");
		}

		WebElement updateButton = webDriver.findElement(By.cssSelector(".update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();

		//		勤怠管理画面に遷移することを確認
		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「勤怠情報を直接編集する」リンクから勤怠情報直接変更画面に遷移")
	void test04() {
		// TODO ここに追加
		webDriver.findElement(By.partialLinkText("勤怠情報を直接編集する")).click();

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 すべての研修日程の勤怠情報を正しく更新し勤怠管理画面に遷移")
	void test05() {
		// TODO ここに追加

		//		全日程に勤怠情報を入力
		List<WebElement> startHours = webDriver.findElements(By.cssSelector("select[id^='startHour']"));

		for (int i = 0; i < startHours.size(); i++) {

			new Select(webDriver.findElement(By.id("startHour" + i)))
					.selectByValue("9");

			new Select(webDriver.findElement(By.id("startMinute" + i)))
					.selectByValue("0");

			new Select(webDriver.findElement(By.id("endHour" + i)))
					.selectByValue("18");

			new Select(webDriver.findElement(By.id("endMinute" + i)))
					.selectByValue("0");

		}

		WebElement updateButton = webDriver.findElement(By.cssSelector(".update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();

		//		勤怠管理画面に遷移することを確認
		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		//		勤怠情報が表示されているか確認
		List<WebElement> rows = webDriver.findElements(By.cssSelector("tbody tr"));

		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));

			assertFalse(cols.get(2).getText().isEmpty()); // 出勤
			assertFalse(cols.get(3).getText().isEmpty()); // 退勤
		}

		getEvidence(new Object() {
		});
	}

}
