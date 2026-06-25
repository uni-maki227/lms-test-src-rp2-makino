package jp.co.sss.lms.ct.f02_faq;

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

import jp.co.sss.lms.pages.course.CoursePage;
import jp.co.sss.lms.pages.help.HelpPage;
import jp.co.sss.lms.pages.login.LoginPage;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {
	private static LoginPage loginPage;
	private static CoursePage coursePage;
	private static HelpPage helpPage;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		coursePage = new CoursePage(webDriver);
		helpPage = new HelpPage(webDriver);

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

		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		coursePage.openUserMenu();
		coursePage.openHelp();

		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加

		//		現在のタブIDを保存
		String windowHandle = webDriver.getWindowHandle();

		//「よくある質問」をクリック
		helpPage.clickFaq();

		//タブ数が「2」であるか確認
		int page = webDriver.getWindowHandles().size();
		assertEquals(2, page);

		//新しいタブへ切り替え
		for (String newPage : webDriver.getWindowHandles()) {
			if (!windowHandle.contentEquals(newPage)) {
				webDriver.switchTo().window(newPage);
				break;
			}
		}

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

}
