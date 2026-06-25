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
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.pages.course.CoursePage;
import jp.co.sss.lms.pages.login.LoginPage;
import jp.co.sss.lms.pages.report.ReportDetailPage;
import jp.co.sss.lms.pages.report.ReportRegistPage;
import jp.co.sss.lms.pages.section.SectionPage;
import jp.co.sss.lms.pages.user.UserDetail;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {
	private static LoginPage loginPage;
	private static CoursePage coursePage;

	private static SectionPage sectionPage;
	private static ReportRegistPage reportRegistPage;

	private static ReportDetailPage reportDetailPage;

	private static UserDetail userDetail;
	private static final String REPORT_TEXT = "Case08：受講生　レポート修正（週報）正常系";

	private static String reportDate;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		coursePage = new CoursePage(webDriver);
		sectionPage = new SectionPage(webDriver);
		reportRegistPage = new ReportRegistPage(webDriver);
		reportDetailPage = new ReportDetailPage(webDriver);
		userDetail = new UserDetail(webDriver);

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

		//		ログイン
		loginPage.login("StudentAA01", "StudentBB01");

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
		List<WebElement> rows = coursePage.getRows();

		for (WebElement row : rows) {

			// 上から見て「提出済」がある行を探す
			if (row.getText().contains("提出済み")) {

				// 日付を保存
				reportDate = coursePage.getDate(row);

				coursePage.clickDetail(row);

				visibilityTimeout(By.tagName("h2"), 10);

				// セクション詳細画面で週報があるか確認
				if (sectionPage.hasWeeklyReport()) {

					// 日付を保存
					reportDate = sectionPage.getReportDate();
					assertEquals("セクション詳細 | LMS", webDriver.getTitle());

					getEvidence(new Object() {
					});

					break;
				}

				// 週報がなければコース詳細画面に戻る
				webDriver.navigate().back();
				visibilityTimeout(By.tagName("h2"), 10);

				rows = coursePage.getRows();
			}

		}

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		//		「提出済み週報【デモ】を確認する」ボタンを押下する
		sectionPage.clickWeeklyReportButton();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加
		reportRegistPage.inpuyWeekReport(REPORT_TEXT);

		//		「提出する」ボタンを押下する
		reportRegistPage.submit();

		// セクション詳細画面に遷移していることを確認
		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
		sectionPage.openUserMenu();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("ユーザー詳細", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加
		userDetail.openDetail(reportDate);

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

		// レポート内容を確認
		reportDetailPage.assertReportText(REPORT_TEXT);

		getEvidence(new Object() {
		});

	}

}
