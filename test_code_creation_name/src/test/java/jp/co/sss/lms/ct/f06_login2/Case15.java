package jp.co.sss.lms.ct.f06_login2;

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

import jp.co.sss.lms.pages.login.LoginPage;
import jp.co.sss.lms.pages.user.AgreeSecurityPage;

/**
 * 結合テスト ログイン機能②
 * ケース15
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース15 受講生 初回ログイン 利用規約に不同意")
public class Case15 {

	private static LoginPage loginPage;

	private static AgreeSecurityPage agreeSecurityPage;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		agreeSecurityPage = new AgreeSecurityPage(webDriver);
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
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() {
		//		ログイン
		loginPage.login("StudentAA02", "StudentAA02");

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("セキュリティ規約 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックをせず「次へ」ボタンを押下")
	void test03() {
		// TODO ここに追加

		agreeSecurityPage.disagree();

		//		エラーメッセージが表示されてるか確認
		assertTrue(webDriver.findElement(By.className("error")).getText().contains("セキュリティ規約への同意は必須です。"));

		//		画面遷移してないか確認
		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("セキュリティ規約 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});

	}

}
