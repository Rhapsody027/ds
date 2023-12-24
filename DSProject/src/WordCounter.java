import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
	private String content;

	public WordCounter(String urlStr) {
		this.urlStr = urlStr;
	}

	// 回傳網頁的內容
	private String fetchContent() {
		try {
			URL url = new URL(this.urlStr);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36");
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			StringBuilder retVal = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				retVal.append(line).append("\n");
			}

			return retVal.toString();

		} catch (IOException e) {
			// Handle IOException, e.g., print an error message
			System.err.println("Error fetching content: " + e.getMessage());
			// Return a default or empty content
			return "";
		}
	}

	// 回傳單一keyword在網頁內出現的次數
	public int countKeyword(String keyword) throws IOException {
		if (content == null) {
			content = fetchContent();
		}

		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int retVal = 0;
		int fromIdx = 0;
		int found = -1;

		while ((found = content.indexOf(keyword, fromIdx)) != -1) {
			retVal++;
			fromIdx = found + keyword.length();
		}

		return retVal;
	}
}
