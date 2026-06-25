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
import jp.co.sss.lms.pages.password.ChangePasswordPage;
import jp.co.sss.lms.pages.user.AgreeSecurityPage;

/**
 * 結合テスト ログイン機能②
 * ケース17
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース17 受講生 初回ログイン 正常系")
public class Case17 {

	private static LoginPage loginPage;

	private static AgreeSecurityPage agreeSecurityPage;

	private static ChangePasswordPage changePasswordPage;

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
		loginPage = new LoginPage(webDriver);
		agreeSecurityPage = new AgreeSecurityPage(webDriver);
		changePasswordPage = new ChangePasswordPage(webDriver);
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
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() {
		// TODO ここに追加
		//		同意しますをクリック
		agreeSecurityPage.agree();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 変更パスワードを入力し「変更」ボタン押下")
	void test04() {

		//		入力値を入力
		changePasswordPage.inputPassword("StudentAA02", "StudentBB02", "StudentBB02");

		//		「変更」ボタン、確認モーダルの変更ボタン押下
		changePasswordPage.clickSubmitButton();

		//		確認モーダルの変更ボタンを押下
		visibilityTimeout(By.id("upd-btn"), 10);
		webDriver.findElement(By.id("upd-btn")).click();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

}
