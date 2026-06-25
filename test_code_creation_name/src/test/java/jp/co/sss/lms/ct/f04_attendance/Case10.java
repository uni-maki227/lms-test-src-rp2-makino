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
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.pages.attendance.AttendanceDetailPage;
import jp.co.sss.lms.pages.course.CoursePage;
import jp.co.sss.lms.pages.login.LoginPage;

/**
 * 結合テスト 勤怠管理機能
 * ケース10
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース10 受講生 勤怠登録 正常系")
public class Case10 {

	private static LoginPage loginPage;

	private static CoursePage coursePage;

	private static AttendanceDetailPage attendanceDetail;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		coursePage = new CoursePage(webDriver);
		attendanceDetail = new AttendanceDetailPage(webDriver);
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
	@DisplayName("テスト03 上部メニューの「勤怠」リンクから勤怠管理画面に遷移")
	void test03() {
		// TODO ここに追加
		coursePage.openAttendance();

		visibilityTimeout(By.tagName("h2"), 10);
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
		attendanceDetail.clickPunchInButton();

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
		attendanceDetail.clickPunchOutButton();

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
