package com.code.net;

/**
 * 九九小说网
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.code.entity.Book;
import com.code.net.util.Browser;
import com.code.net.util.StreamUtils;

public class TXT99NetSearch implements NetSearch {

	/**
	 * http://zhannei.baidu.com/cse/search?q=%E6%96%97%E7%A0%B4&click=1&s=
	 * 10722981113312165527&nsid=
	 */
	@Override
	public List<Book> search(String key) {
		Browser browser = new Browser();
		List<Book> books = new ArrayList<Book>();
		try {
			Document doc = Jsoup
					.connect("http://zhannei.baidu.com/cse/search?q="
							+ URLEncoder.encode(key, "gb2312")
							+ "&click=1&s=10722981113312165527&nsid=").get();
			Elements eles = doc.select(".result-game-item");
			for (Element e : eles) {
				NetBook book = new NetBook();
				Elements ts = e.select(".result-game-item-title-link");
				if (ts.size() > 0) {
					Element line = ts.get(0);
					book.setName(line.text());
					book.setUrl(line.attr("href"));
				}
				// result-game-item-desc
				ts = e.select(".result-game-item-desc");
				if (ts.size() > 0) {
					Element line = ts.get(0);
					book.setDiscribe(line.text());
				}
				books.add(book);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return books;
	}

	private Book parseBook(String line) throws IOException {
		NetBook book = new NetBook();
		// 解析名称
		Pattern pattern = Pattern.compile("title=\"[^\"]+");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			String name = matcher.group().replace("title=\"", "");
			book.setName(name);
		}
		pattern = Pattern.compile("\\d{4,}");
		matcher = pattern.matcher(line);
		if (matcher.find()) {
			book.setUrl(matcher.group());
		}
		return book;
	}

	@Override
	public boolean downLoad(Book book, OutputStream os) throws IOException {
		NetBook book1 = (NetBook) book;
		Browser browser = new Browser();
		InputStream is = browser
				.browerURL("http://www.txt99.cc/home/down/txt/id/"
						+ book1.getUrl());
		StreamUtils.copy(is, os);
		return true;
	}

	public static void main(String[] args) {
		TXT99NetSearch search = new TXT99NetSearch();
		List<Book> books = search.search("斗破");
		for (Book book : books) {
			try {
				search.downLoad(book, new FileOutputStream(book.getName()
						+ ".txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
