package jp.co.sss.lms.ct.f03_report;

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

import jp.co.sss.lms.pages.LoginPage;
import jp.co.sss.lms.pages.ReportRegistPage;
import jp.co.sss.lms.pages.SectionPage;
import jp.co.sss.lms.pages.UserDetail;

/**
 * 結合テスト レポート機能
 * ケース09
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース09 受講生 レポート登録 入力チェック")
public class Case09 {

	private static LoginPage loginPage;

	private static SectionPage sectionPage;

	private static UserDetail userDetail;

	private static ReportRegistPage reportRegistPage;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		sectionPage = new SectionPage(webDriver);
		userDetail = new UserDetail(webDriver);
		reportRegistPage = new ReportRegistPage(webDriver);
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
		//		ログイン
		loginPage.login("StudentAA01", "StudentBB01");

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
		sectionPage.clickSubmitUserLink();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("ユーザー詳細", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		userDetail.openReportRegit();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		// TODO ここに追加
		reportRegistPage.clearStudyContent();

		// エラーのためレポート登録画面のままであること
		visibilityTimeout(By.tagName("h2"), 10);
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
		reportRegistPage.clearUnderstandingLevel();

		// エラーのためレポート登録画面のままであること
		visibilityTimeout(By.tagName("h2"), 10);
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
		reportRegistPage.inputAchievementLevel("できる");

		// エラーのためレポート登録画面のままであること
		visibilityTimeout(By.tagName("h2"), 10);
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
		reportRegistPage.inputAchievementLevel("20");

		// エラーのためレポート登録画面のままであること
		visibilityTimeout(By.tagName("h2"), 10);
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
		reportRegistPage.clearAchievementLevelAndComments();

		// エラーのためレポート登録画面のままであること
		visibilityTimeout(By.tagName("h2"), 10);
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
		reportRegistPage.inputCommentsAndReportTextArea("あ".repeat(2001));

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
