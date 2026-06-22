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

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

	private static final String REPORT_TEXT = "Case08：受講生　レポート修正（週報）正常系";

	private static String reportDate;

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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		// 一覧の行を取得
		List<WebElement> rows = webDriver.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			// 上から見て最初に「提出済」がある行を探す
			if (row.getText().contains("提出済み")) {

				// 日付を保存
				reportDate = row.findElement(By.tagName("td")).getText();

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
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		//		「提出済み日報【デモ】を確認する」ボタンを押下する
		webDriver.findElement(By.cssSelector("input[value*='提出済み']")).click();

		visibilityTimeout(By.tagName("h2"), 10);

		// レポート登録画面に遷移していることを確認
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加
		webDriver.findElement(By.className("form-control")).sendKeys(REPORT_TEXT);

		//		「提出する」ボタンを押下する
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		// セクション詳細画面が表示されるまで待機
		visibilityTimeout(By.tagName("h2"), 10);

		// セクション詳細画面に遷移していることを確認
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
		webDriver.findElement(By.partialLinkText("ようこそ")).click();

		assertEquals("ユーザー詳細", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加

		WebElement reportTable = webDriver.findElement(By.xpath("//h3[text()='レポート']/following-sibling::table"));

		List<WebElement> rows = reportTable.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			if (row.getText().contains(reportDate)) {

				WebElement detailButton = row.findElement(By.cssSelector("input[value='詳細']"));

				((JavascriptExecutor) webDriver).executeScript(
						"arguments[0].click();",
						detailButton);

				break;
			}
		}

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

		// レポート内容を確認
		WebElement reportDetailTable = webDriver
				.findElement(By.xpath("//h3[text()='報告レポート']/following-sibling::table"));

		String actualText = reportDetailTable.getText();

		assertTrue(actualText.contains(REPORT_TEXT));

		getEvidence(new Object() {
		});
	}

}
