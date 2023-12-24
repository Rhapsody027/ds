import java.io.IOException;
import java.util.ArrayList;

public class WebPage implements Comparable<WebPage> {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;

	public WebPage(String url, String name) {
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
	}

	public void setScore(ArrayList<Keyword> keywords) throws IOException {
		score = 0;
		for (var keyword : keywords) {
			score += keyword.weight * counter.countKeyword(keyword.name);
		}
	}

	public void updateKeywordCounts(KeywordList keywordList) throws IOException {
		for (Keyword keyword : keywordList.getList()) {
			int count = counter.countKeyword(keyword.name);
			keyword.count = count;
		}
	}

	@Override
	public int compareTo(WebPage other) {
		return Double.compare(other.score, this.score);
	}
}