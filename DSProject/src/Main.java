import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		/*-------------------取得使用者輸入--------------------*/
		Scanner sc = new Scanner(System.in);
		String inputText = sc.nextLine();
		/*------------------------------------------------*/

		
		
		/*---------讀取input.txt來設定keywords---------------*/
		File file = new File("input.txt");
		KeywordList keywordList = new KeywordList();
		try (Scanner fileSc = new Scanner(file)) {
			while (fileSc.hasNext()) {
				String name = fileSc.next();
				float weight = fileSc.nextFloat();
				Keyword k = new Keyword(name, weight);
				keywordList.add(k);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*------------------------------------------------*/

		
		
		
		/*---------取得標題和URL，計算所有分數，將所有WebPage加入List中--------------------*/
		HashMap<String, String> titleAndURL = new GoogleQuery(inputText).query();
		ArrayList<WebPage> webPages = new ArrayList<>();

		for (Map.Entry<String, String> entry : titleAndURL.entrySet()) {
			String title = entry.getKey();
			String url = entry.getValue();

			WebPage webPage = new WebPage(url, title);

			// 更新當前網頁的關鍵字計數
			webPage.updateKeywordCounts(keywordList);

			// 計算當前網頁的分數
			webPage.setScore(keywordList.getList());
			webPages.add(webPage);
		}
		/*----------------------------------------------------------------*/

		
		
		
		
		/*---------對List依照score降冪排列，並輸出結果--------------------*/
		Collections.sort(webPages);

		for (WebPage webPage : webPages) {
			System.out.println("標題：" + webPage.name);
			System.out.println("URL：" + webPage.url);
			System.out.println("分數：" + webPage.score);

			for (Keyword keyword : keywordList.getList()) {
				System.out.println(keyword.name + " 出現次數：" + keyword.count);
			}

			System.out.println("---------------");
		}
		sc.close();
	}

}