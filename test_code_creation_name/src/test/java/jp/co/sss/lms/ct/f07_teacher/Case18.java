package jp.co.sss.lms.ct.f07_teacher;

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

import jp.co.sss.lms.pages.course.CourseListPage;
import jp.co.sss.lms.pages.login.LoginPage;
import jp.co.sss.lms.pages.report.ReportDetailPage;
import jp.co.sss.lms.pages.user.UserDetail;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース18 講師 日報フィードバック 正常系")
class Case18 {

	private static LoginPage loginPage;
	private static CourseListPage courseListPage;

	private static UserDetail userDetail;

	private static ReportDetailPage reportDetailPage;

	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		courseListPage = new CourseListPage(webDriver);
		userDetail = new UserDetail(webDriver);
		reportDetailPage = new ReportDetailPage(webDriver);
	}

	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		goTo("http://localhost:8080/lms");

		assertEquals("ログイン | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの講師ユーザーでログイン")
	void test02() {
		//		ログイン
		loginPage.login("Teacher01", "TeacherAA01");

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("コース一覧 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「受講生」リンクからユーザー一覧画面に遷移")
	void test03() {
		courseListPage.openUserLink();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("ユーザー一覧 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 受講生AA1の「詳細」ボタンを押下")
	void test04() {

		List<WebElement> rows = courseListPage.getRows();

		for (WebElement row : rows) {

			// 上から見て「受講生ＡＡ１」がある行を探す
			if (row.getText().contains("受講生ＡＡ１")) {

				courseListPage.clickDetail(row);

				visibilityTimeout(By.tagName("h2"), 10);

				assertEquals("ユーザー詳細", webDriver.getTitle());

				getEvidence(new Object() {
				});

				break;
			}
		}

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 2022年10月2日(日) 週報【デモ】の「詳細」ボタンを押下")
	void test05() {
		userDetail.openDetail("2022年10月2日(日)");

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「フィードバックコメントを登録」ボタンを押下")
	void test06() {
		//		「フィードバックコメントを登録」ボタンを押下
		reportDetailPage.clickFeedbackRegisterButton();

		//		ダイアログが表示されてるか確認
		visibilityTimeout(By.id("content_create"), 10);
		assertTrue(reportDetailPage.isFeedbackModalDisplayed());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 フィードバック内容を入力し「提出する」ボタン、確認ダイアログの「OK」を押下")
	void test07() {
		//		内容を入力し「提出する」ボタン、確認ダイアログの「OK」を押下
		reportDetailPage.inputFeedback("Case18　講師＿受講生日報フィードバック作成＿正常系");

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

		assertEquals("講師１", reportDetailPage.getFeedbackUser());
		assertEquals("Case18　講師＿受講生日報フィードバック作成＿正常系", reportDetailPage.getFeedbackContent());

		getEvidence(new Object() {
		});

	}
}
