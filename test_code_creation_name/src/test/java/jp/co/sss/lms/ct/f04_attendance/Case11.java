package jp.co.sss.lms.ct.f04_attendance;

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

import jp.co.sss.lms.pages.AttendanceDetailPage;
import jp.co.sss.lms.pages.AttendanceUpdatePage;
import jp.co.sss.lms.pages.CoursePage;
import jp.co.sss.lms.pages.LoginPage;

/**
 * 結合テスト 勤怠管理機能
 * ケース11
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース11 受講生 勤怠直接編集 正常系")
public class Case11 {

	private static LoginPage loginPage;

	private static CoursePage coursePage;

	private static AttendanceDetailPage attendanceDetailPage;

	private static AttendanceUpdatePage attendanceUpdatePage;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		coursePage = new CoursePage(webDriver);
		attendanceDetailPage = new AttendanceDetailPage(webDriver);
		attendanceUpdatePage = new AttendanceUpdatePage(webDriver);
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
	@DisplayName("テスト03 上部メニューの「勤怠」リンクから勤怠管理画面に遷移")
	void test03() {
		// TODO ここに追加
		coursePage.openAttendance();
		attendanceDetailPage.openAttendanceUpdateLink();

		attendanceUpdatePage.setAllWorkinghours("", "", "", "");

		attendanceUpdatePage.clickUpdateButton();
		//		勤怠管理画面に遷移することを確認
		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「勤怠情報を直接編集する」リンクから勤怠情報直接変更画面に遷移")
	void test04() {
		// TODO ここに追加
		attendanceDetailPage.openAttendanceUpdateLink();

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 すべての研修日程の勤怠情報を正しく更新し勤怠管理画面に遷移")
	void test05() {
		// TODO ここに追加

		//		全日程に勤怠情報を入力
		attendanceUpdatePage.setAllWorkinghours("9", "0", "18", "0");

		attendanceUpdatePage.clickUpdateButton();

		//		勤怠管理画面に遷移することを確認
		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());

		//		勤怠情報が表示されているか確認
		List<WebElement> rows = webDriver.findElements(By.cssSelector("tbody tr"));

		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));

			assertFalse(cols.get(2).getText().isEmpty()); // 出勤
			assertFalse(cols.get(3).getText().isEmpty()); // 退勤
		}

		getEvidence(new Object() {
		});
	}

}
