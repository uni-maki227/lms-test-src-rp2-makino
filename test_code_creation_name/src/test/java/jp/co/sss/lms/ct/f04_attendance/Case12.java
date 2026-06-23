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
import org.openqa.selenium.support.ui.Select;

/**
 * 結合テスト 勤怠管理機能
 * ケース12
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース12 受講生 勤怠直接編集 入力チェック")
public class Case12 {

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
	@DisplayName("テスト05 不適切な内容で修正してエラー表示：出退勤の（時）と（分）のいずれかが空白")
	void test05() {
		// TODO ここに追加

		//		出勤、退勤を入力
		new Select(webDriver.findElement(By.id("startHour0")))
				.selectByValue("9");

		new Select(webDriver.findElement(By.id("startMinute0")))
				.selectByValue("");

		new Select(webDriver.findElement(By.id("endHour0")))
				.selectByValue("");

		new Select(webDriver.findElement(By.id("endMinute0")))
				.selectByValue("0");

		//		更新」ボタン、確認ダイアログの「OK」を押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();

		//		エラー表示確認
		assertTrue(webDriver.findElement(By.id("startMinute0"))
				.getAttribute("class")
				.contains("errorInput"));

		assertTrue(webDriver.findElement(By.id("endHour0"))
				.getAttribute("class")
				.contains("errorInput"));

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正してエラー表示：出勤が空白で退勤に入力あり")
	void test06() {
		// TODO ここに追加

		//		出勤、退勤を入力
		new Select(webDriver.findElement(By.id("startHour0")))
				.selectByValue("9");

		new Select(webDriver.findElement(By.id("startMinute0")))
				.selectByValue("0");

		new Select(webDriver.findElement(By.id("endHour0")))
				.selectByValue("");

		new Select(webDriver.findElement(By.id("endMinute0")))
				.selectByValue("");

		//		更新」ボタン、確認ダイアログの「OK」を押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();

		//		エラー表示確認
		assertTrue(webDriver.findElement(By.id("endHour0"))
				.getAttribute("class")
				.contains("errorInput"));

		assertTrue(webDriver.findElement(By.id("endMinute0"))
				.getAttribute("class")
				.contains("errorInput"));

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正してエラー表示：出勤が退勤よりも遅い時間")
	void test07() {
		// TODO ここに追加

		//		出勤、退勤を入力
		new Select(webDriver.findElement(By.id("startHour0")))
				.selectByValue("19");

		new Select(webDriver.findElement(By.id("startMinute0")))
				.selectByValue("0");

		new Select(webDriver.findElement(By.id("endHour0")))
				.selectByValue("18");

		new Select(webDriver.findElement(By.id("endMinute0")))
				.selectByValue("0");

		//		更新」ボタン、確認ダイアログの「OK」を押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();

		//		エラー表示確認
		assertTrue(webDriver.findElement(By.id("endHour0"))
				.getAttribute("class")
				.contains("errorInput"));

		assertTrue(webDriver.findElement(By.id("endMinute0"))
				.getAttribute("class")
				.contains("errorInput"));

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正してエラー表示：出退勤時間を超える中抜け時間")
	void test08() {
		// TODO ここに追加

		//		出勤、退勤を入力
		new Select(webDriver.findElement(By.id("startHour0")))
				.selectByValue("9");

		new Select(webDriver.findElement(By.id("startMinute0")))
				.selectByValue("0");

		new Select(webDriver.findElement(By.id("endHour0")))
				.selectByValue("12");

		new Select(webDriver.findElement(By.id("endMinute0")))
				.selectByValue("0");

		//		中抜け時間を入力
		new Select(webDriver.findElement(By.name("attendanceList[0].blankTime")))
				.selectByValue("240");

		//		更新」ボタン、確認ダイアログの「OK」を押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();

		//		エラー表示確認
		assertTrue(webDriver.findElement(By.name("attendanceList[0].blankTime"))
				.getAttribute("class")
				.contains("errorInput"));

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正してエラー表示：備考が100文字超")
	void test09() {
		// TODO ここに追加

		//		出勤、退勤を入力
		new Select(webDriver.findElement(By.id("startHour0")))
				.selectByValue("9");

		new Select(webDriver.findElement(By.id("startMinute0")))
				.selectByValue("0");

		new Select(webDriver.findElement(By.id("endHour0")))
				.selectByValue("18");

		new Select(webDriver.findElement(By.id("endMinute0")))
				.selectByValue("0");

		//		備考欄に101文字入力
		webDriver.findElement(By.name("attendanceList[0].note")).sendKeys("あ".repeat(101));

		//		更新」ボタン、確認ダイアログの「OK」を押下する
		WebElement updateButton = webDriver.findElement(By.cssSelector(".update-button"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				updateButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				updateButton);

		webDriver.switchTo().alert().accept();

		//		エラー表示確認
		assertTrue(webDriver.findElement(By.name("attendanceList[0].note"))
				.getAttribute("class")
				.contains("errorInput"));

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

}
