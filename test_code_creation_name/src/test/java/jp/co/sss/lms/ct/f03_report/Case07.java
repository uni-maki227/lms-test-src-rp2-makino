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

import jp.co.sss.lms.pages.CoursePage;
import jp.co.sss.lms.pages.LoginPage;
import jp.co.sss.lms.pages.ReportRegistPage;
import jp.co.sss.lms.pages.SectionPage;

/**
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {
	private static LoginPage loginPage;
	private static CoursePage coursePage;

	private static SectionPage sectionPage;
	private static ReportRegistPage reportRegistPage;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		coursePage = new CoursePage(webDriver);
		sectionPage = new SectionPage(webDriver);
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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加

		// 一覧の行を取得
		coursePage.clickFirstUnsubmittedDetail();

		// セクション詳細画面が表示されるまで待機
		visibilityTimeout(By.tagName("h2"), 10);

		// セクション詳細画面に遷移していることを確認
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加

		//		「日報【デモ】を提出する」ボタンを押下する
		sectionPage.clickSubmitReport();

		visibilityTimeout(By.tagName("h2"), 10);

		// レポート登録画面に遷移していることを確認
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// TODO ここに追加

		reportRegistPage.inputDateReport("Case07：受講生 レポート新規登録（日報）正常系");

		//		「提出する」ボタンを押下する
		reportRegistPage.submit();

		// セクション詳細画面が表示されるまで待機
		visibilityTimeout(By.tagName("h2"), 10);

		// セクション詳細画面に遷移していることを確認
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		assertTrue(sectionPage.isSubmitted());

		getEvidence(new Object() {
		});

	}

}
