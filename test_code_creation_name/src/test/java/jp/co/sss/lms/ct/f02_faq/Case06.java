package jp.co.sss.lms.ct.f02_faq;

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
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
		goTo("http://localhost:8080/lms");

		//		ログイン
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("StudentBB01");

		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		visibilityTimeout(By.tagName("h2"), 10);

		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		webDriver.findElement(By.className("dropdown-toggle")).click();

		webDriver.findElement(By.partialLinkText("ヘルプ")).click();

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
		webDriver.findElement(By.partialLinkText("よくある質問")).click();

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

		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// TODO ここに追加

		//		【研修関係】をクリック
		webDriver.findElement(By.partialLinkText("【研修関係】")).click();

		//検索結果の確認
		List<WebElement> questions = webDriver.findElements(By.className("mb10"));

		String resultText = "";
		for (WebElement question : questions) {
			resultText += question.getText();
		}

		assertTrue(resultText.contains("キャンセル料・途中退校について"));
		assertTrue(resultText.contains("研修の申し込みはどのようにすれば良いですか？"));

		//		検索結果が見える位置までスクロール
		((JavascriptExecutor) webDriver)
				.executeScript("arguments[0].scrollIntoView();", questions.get(0));

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// TODO ここに追加

		//		質問をクリック
		WebElement question = webDriver.findElement(By.className("mb10"));
		question.click();

		//		表示されてるかの確認
		assertTrue(webDriver.findElement(By.className("fs18")).isDisplayed());

		//		検索結果が見える位置までスクロール
		((JavascriptExecutor) webDriver)
				.executeScript("arguments[0].scrollIntoView();", question);

		getEvidence(new Object() {
		});
	}

}
