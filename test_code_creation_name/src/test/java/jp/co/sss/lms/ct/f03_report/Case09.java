package jp.co.sss.lms.ct.f03_report;

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
 * 結合テスト レポート機能
 * ケース09
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース09 受講生 レポート登録 入力チェック")
public class Case09 {

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
	@DisplayName("テスト03 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		webDriver.findElement(By.partialLinkText("ようこそ")).click();

		assertEquals("ユーザー詳細", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		WebElement reportTable = webDriver.findElement(By.xpath("//h3[text()='レポート']/following-sibling::table"));

		List<WebElement> rows = reportTable.findElements(By.tagName("tr"));

		for (WebElement row : rows) {
			if (row.getText().contains("週報")) {

				WebElement detailButton = row.findElement(By.cssSelector("input[value='修正する']"));

				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].scrollIntoView({block:'center'});",
						detailButton);

				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].click();",
						detailButton);

				break;
			}

		}
		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	//入力値テンプレート
	private void inputValidReport() {
		//		学習項目
		webDriver.findElement(By.id("intFieldName_0")).clear();
		webDriver.findElement(By.id("intFieldName_0")).sendKeys("ITリテラシー①");

		//		理解度
		new Select(webDriver.findElement(By.id("intFieldValue_0"))).selectByValue("2");

		//		目標の達成度
		webDriver.findElement(By.id("content_0")).clear();
		webDriver.findElement(By.id("content_0")).sendKeys("5");

		//		所感
		webDriver.findElement(By.id("content_1")).clear();
		webDriver.findElement(By.id("content_1")).sendKeys("週報のサンプルです。");

		//		一週間の振り返り
		webDriver.findElement(By.id("content_2")).clear();
		webDriver.findElement(By.id("content_2")).sendKeys("Case09：受講生　レポート登録（週報）入力チェック");

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		// TODO ここに追加
		inputValidReport();

		// 学習項目だけ未入力にする
		webDriver.findElement(By.id("intFieldName_0")).clear();

		//		「提出する」ボタンを押下する

		WebElement submitButton = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submitButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submitButton);

		visibilityTimeout(By.tagName("h2"), 10);

		// エラーのためレポート登録画面のままであること
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		// 学習項目欄がエラー表示になっていること
		assertTrue(webDriver.findElement(By.id("intFieldName_0"))
				.getAttribute("class")
				.contains("errorInput"));

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：理解度が未入力")
	void test06() {
		// TODO ここに追加
		inputValidReport();

		//		理解度が未入力
		new Select(webDriver.findElement(By.id("intFieldValue_0"))).selectByValue("");

		//		「提出する」ボタンを押下する

		WebElement submitButton = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submitButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submitButton);

		visibilityTimeout(By.tagName("h2"), 10);

		// エラーのためレポート登録画面のままであること
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		// 理解度がエラー表示になっていること
		assertTrue(webDriver.findElement(By.id("intFieldValue_0"))
				.getAttribute("class")
				.contains("errorInput"));

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が数値以外")
	void test07() {
		// TODO ここに追加
		inputValidReport();

		//		目標の達成度が数値以外
		webDriver.findElement(By.id("content_0")).clear();
		webDriver.findElement(By.id("content_0")).sendKeys("できる");

		//		「提出する」ボタンを押下する

		WebElement submitButton = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submitButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submitButton);

		visibilityTimeout(By.tagName("h2"), 10);

		// エラーのためレポート登録画面のままであること
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		// 目標の達成度がエラー表示になっていること
		assertTrue(webDriver.findElement(By.id("content_0"))
				.getAttribute("class")
				.contains("errorInput"));

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が範囲外")
	void test08() {
		// TODO ここに追加
		inputValidReport();

		//		目標の達成度が範囲外
		webDriver.findElement(By.id("content_0")).clear();
		webDriver.findElement(By.id("content_0")).sendKeys("20");

		//		「提出する」ボタンを押下する

		WebElement submitButton = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submitButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submitButton);

		visibilityTimeout(By.tagName("h2"), 10);

		// エラーのためレポート登録画面のままであること
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		// 目標の達成度がエラー表示になっていること
		assertTrue(webDriver.findElement(By.id("content_0"))
				.getAttribute("class")
				.contains("errorInput"));

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度・所感が未入力")
	void test09() {
		// TODO ここに追加
		inputValidReport();

		//		目標の達成度・所感が未入力
		webDriver.findElement(By.id("content_0")).clear();
		webDriver.findElement(By.id("content_1")).clear();

		//		「提出する」ボタンを押下する

		WebElement submitButton = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submitButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submitButton);

		visibilityTimeout(By.tagName("h2"), 10);

		// エラーのためレポート登録画面のままであること
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		// 目標の達成度がエラー表示になっていること
		assertTrue(webDriver.findElement(By.id("content_0"))
				.getAttribute("class")
				.contains("errorInput"));

		// 所感がエラー表示になっていること
		assertTrue(webDriver.findElement(By.id("content_1"))
				.getAttribute("class")
				.contains("errorInput"));

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(10)
	@DisplayName("テスト10 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：所感・一週間の振り返りが2000文字超")
	void test10() {
		// TODO ここに追加
		inputValidReport();

		String overText = "あ".repeat(2001);

		webDriver.findElement(By.id("content_1")).clear();
		webDriver.findElement(By.id("content_1")).sendKeys(overText);

		webDriver.findElement(By.id("content_2")).clear();
		webDriver.findElement(By.id("content_2")).sendKeys(overText);

		WebElement submitButton = webDriver.findElement(By.cssSelector(".btn.btn-primary"));

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				submitButton);

		((JavascriptExecutor) webDriver).executeScript(
				"arguments[0].click();",
				submitButton);

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		assertTrue(webDriver.findElement(By.id("content_1"))
				.getAttribute("class")
				.contains("errorInput"));

		assertTrue(webDriver.findElement(By.id("content_2"))
				.getAttribute("class")
				.contains("errorInput"));

		getEvidence(new Object() {
		});
	}

}
