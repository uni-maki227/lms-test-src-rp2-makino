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
 * ケース16
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース16 受講生 初回ログイン 変更パスワード未入力")
public class Case16 {

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

		agreeSecurityPage.agree();

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 パスワードを未入力で「変更」ボタン押下")
	void test04() {

		//		「変更」ボタン、確認モーダルの変更ボタン押下
		changePasswordPage.clickSubmitButton();

		//		エラー表示確認
		visibilityTimeout(By.id("currentPassword"), 10);
		assertTrue(webDriver.findElement(By.id("currentPassword")).getAttribute("class").contains("errorInput"));
		assertTrue(webDriver.findElement(By.id("password")).getAttribute("class").contains("errorInput"));
		assertTrue(webDriver.findElement(By.id("passwordConfirm")).getAttribute("class").contains("errorInput"));

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 20文字以上の変更パスワードを入力し「変更」ボタン押下")
	void test05() {

		//		入力値を入力
		changePasswordPage.inputPassword("StudentAA02", "StudentBB022222222222", "StudentBB022222222222");

		//		「変更」ボタン、確認モーダルの変更ボタン押下
		changePasswordPage.clickSubmitButton();

		//		エラー表示確認
		visibilityTimeout(By.id("password"), 10);
		assertTrue(webDriver.findElement(By.id("password")).getAttribute("class").contains("errorInput"));

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 ポリシーに合わない変更パスワードを入力し「変更」ボタン押下")
	void test06() {
		// TODO ここに追加 
		//		入力値を入力
		changePasswordPage.inputPassword("StudentAA02", "studentbb02", "studentbb02");

		//		「変更」ボタン、確認モーダルの変更ボタン押下
		changePasswordPage.clickSubmitButton();

		//		エラー表示確認
		visibilityTimeout(By.id("password"), 10);
		assertTrue(webDriver.findElement(By.id("password")).getAttribute("class").contains("errorInput"));

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 一致しない確認パスワードを入力し「変更」ボタン押下")
	void test07() {
		// TODO ここに追加
		//		入力値を入力
		changePasswordPage.inputPassword("StudentAA02", "StudentBB02", "StudentCC02");

		//		「変更」ボタン、確認モーダルの変更ボタン押下
		changePasswordPage.clickSubmitButton();

		//		エラー表示確認
		visibilityTimeout(By.id("password"), 10);
		assertTrue(webDriver.findElement(By.id("password")).getAttribute("class").contains("errorInput"));

		visibilityTimeout(By.tagName("h2"), 10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

}
