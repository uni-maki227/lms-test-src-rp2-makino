package jp.co.sss.lms.ct.f05_exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import jp.co.sss.lms.pages.course.CoursePage;
import jp.co.sss.lms.pages.exam.ExamAnswerCheckPage;
import jp.co.sss.lms.pages.exam.ExamQuestionPage;
import jp.co.sss.lms.pages.exam.ExamResultPage;
import jp.co.sss.lms.pages.exam.ExamStartPage;
import jp.co.sss.lms.pages.login.LoginPage;
import jp.co.sss.lms.pages.section.SectionPage;

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

	private static LoginPage loginPage;

	private static CoursePage coursePage;

	private static SectionPage sectionPage;

	private static ExamStartPage examStartPage;

	private static ExamQuestionPage examQuestionPage;

	private static ExamAnswerCheckPage examAnswerCheckPage;

	private static ExamResultPage examResultPage;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		coursePage = new CoursePage(webDriver);
		sectionPage = new SectionPage(webDriver);
		examStartPage = new ExamStartPage(webDriver);
		examQuestionPage = new ExamQuestionPage(webDriver);
		examAnswerCheckPage = new ExamAnswerCheckPage(webDriver);
		examResultPage = new ExamResultPage(webDriver);
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
	@DisplayName("テスト03 「試験有」の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		// 一覧の行を取得
		coursePage.clickFirstUnsubmittedDetail("試験有");

		// セクション詳細画面に遷移していることを確認
		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「本日の試験」エリアの「詳細」ボタンを押下し試験開始画面に遷移")
	void test04() {
		// TODO ここに追加

		sectionPage.clickDetailButton();

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
	void test05() throws InterruptedException {
		// TODO ここに追加

		examStartPage.clickStartButton();

		Thread.sleep(1500);

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("ITリテラシー① | LMS", webDriver.getTitle());

		//		回答選択肢があるか確認
		assertFalse(examQuestionPage.hasAnswerChoices());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 未回答の状態で「確認画面へ進む」ボタンを押下し試験回答確認画面に遷移")
	void test06() {
		// TODO ここに追加

		examQuestionPage.clickNextButton();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("ITリテラシー① | LMS", webDriver.getTitle());

		//		回答数が表示されてるか確認
		assertTrue(examAnswerCheckPage.hasAnswerCount());
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

		examAnswerCheckPage.clickSendButton();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("ITリテラシー① | LMS", webDriver.getTitle());

		//		あなたのスコアが表示されてるか確認
		assertTrue(examResultPage.hasScore());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 「戻る」ボタンを押下し試験開始画面に遷移後当該試験の結果が反映される")
	void test08() throws ParseException {
		// TODO ここに追加

		examResultPage.clickBackButton();

		//最終のテスト実施日時と該当テストの実施日時が一致するか確認
		String resultDate = examStartPage.getLastExamResultDate();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");

		Date result = sdf.parse(resultDate);

		assertEquals(
				new SimpleDateFormat("yyyyMMddHHmm").format(date),
				new SimpleDateFormat("yyyyMMddHHmm").format(result));

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("試験【ITリテラシー①】 | LMS", webDriver.getTitle());

		examStartPage.scrollToLastExamResult();

		getEvidence(new Object() {
		});
	}

}
