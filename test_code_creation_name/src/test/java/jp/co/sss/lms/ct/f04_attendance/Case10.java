package jp.co.sss.lms.ct.f04_attendance;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

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

/**
 * 結合テスト 勤怠管理機能
 * ケース10
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース10 受講生 勤怠登録 正常系")
public class Case10 {

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

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「出勤」ボタンを押下し出勤時間を登録")
	void test04() {
		// TODO ここに追加

		//	出勤ボタンを押す
		WebElement punchInButton = webDriver.findElement(By.cssSelector("input[value*='出勤']"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				punchInButton);

		punchInButton.click();

		// 確認ダイアログでOK
		webDriver.switchTo().alert().accept();

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		//	出勤時間が登録されているか確認
		WebElement todayRow = webDriver.findElement(By.cssSelector("tr.info"));

		String punchInTime = todayRow.findElements(By.tagName("td")).get(2).getText();

		assertFalse(punchInTime.isEmpty());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 「退勤」ボタンを押下し退勤時間を登録")
	void test05() {
		// TODO ここに追加
		WebElement punchInButton = webDriver.findElement(By.cssSelector("input[value*='退勤']"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				punchInButton);

		punchInButton.click();

		// 確認ダイアログでOK
		webDriver.switchTo().alert().accept();

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		//		退勤時間が登録されているかの確認
		WebElement todayRow = webDriver.findElement(By.cssSelector("tr.info"));

		String punchInTime = todayRow.findElements(By.tagName("td")).get(2).getText();

		assertFalse(punchInTime.isEmpty());

		getEvidence(new Object() {
		});
	}

}
