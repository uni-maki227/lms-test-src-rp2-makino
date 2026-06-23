package jp.co.sss.lms.ct.f05_exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 * 結合テスト 試験実施機能
 * ケース13
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース13 受講生 試験の実施 結果0点")
public class Case13 {

	/** テスト07およびテスト08 試験実施日時 */
	static Date date;

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
	@DisplayName("テスト03 「試験有」の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		// 一覧の行を取得
		List<WebElement> rows = webDriver.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			// 上から見て最初に「試験有」がある行を探す
			if (row.getText().contains("試験有")) {

				WebElement detailButton = row.findElement(By.cssSelector(".btn.btn-default"));

				// ボタンが画面中央に来るようにスクロール
				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].scrollIntoView({block:'center'});",
						detailButton);

				detailButton.click();
				break;
			}
		}
		// セクション詳細画面が表示されるまで待機
		visibilityTimeout(By.tagName("h2"), 10);

		// セクション詳細画面に遷移していることを確認
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「本日の試験」エリアの「詳細」ボタンを押下し試験開始画面に遷移")
	void test04() {
		// TODO ここに追加

		webDriver.findElement(By.cssSelector("input[value='詳細']")).click();

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("試験【ITリテラシー①】 | LMS", webDriver.getTitle());

		//		最終の試験回数を表示
		((JavascriptExecutor) webDriver)
				.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 「試験を開始する」ボタンを押下し試験問題画面に遷移")
	void test05() {
		// TODO ここに追加

		WebElement startButton = webDriver.findElement(By.cssSelector("input[value='試験を開始する']"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				startButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				startButton);

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("ITリテラシー① | LMS", webDriver.getTitle());

		//		試験残り時間が表示されてるか確認
		assertTrue(webDriver.findElement(By.id("remainTime")).isDisplayed());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 未回答の状態で「確認画面へ進む」ボタンを押下し試験回答確認画面に遷移")
	void test06() {
		// TODO ここに追加

		WebElement Button = webDriver.findElement(By.cssSelector("input[value='確認画面へ進む']"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				Button);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				Button);

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("ITリテラシー① | LMS", webDriver.getTitle());

		//		回答数が表示されてるか確認
		assertTrue(webDriver.findElement(By.cssSelector("h2 small")).getText().contains("回答数"));

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 「回答を送信する」ボタンを押下し試験結果画面に遷移")
	void test07() throws InterruptedException {
		// TODO ここに追加

		//		試験実施日時を保存
		date = new Date();

		Thread.sleep(1500);

		WebElement Button = webDriver.findElement(By.id("sendButton"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				Button);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				Button);

		webDriver.switchTo().alert().accept();

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("ITリテラシー① | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 「戻る」ボタンを押下し試験開始画面に遷移後当該試験の結果が反映される")
	void test08() throws ParseException {
		// TODO ここに追加

		WebElement Button = webDriver.findElement(By.cssSelector("input[value='戻る']"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				Button);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				Button);

		//最終のテスト実施日時と該当テストの実施日時が一致するか確認
		List<WebElement> rows = webDriver.findElements(
				By.xpath("//h3[text()='過去の試験結果']/following::table[1]//tr[position()>1]"));

		WebElement lastRow = rows.get(rows.size() - 1);

		String resultDate = lastRow.findElements(By.tagName("td")).get(3).getText();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");

		Date result = sdf.parse(resultDate);

		assertEquals(
				new SimpleDateFormat("yyyyMMddHHmm").format(date),
				new SimpleDateFormat("yyyyMMddHHmm").format(result));

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("試験【ITリテラシー①】 | LMS", webDriver.getTitle());

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				lastRow);

		getEvidence(new Object() {
		});
	}

}
