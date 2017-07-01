package com.code.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.code.entity.Book;
import com.code.net.util.Browser;
import com.code.net.util.StreamUtils;

public class QingKanNetSearch implements NetSearch {

	@Override
	public List<Book> search(String key) {
		List<Book> books = new ArrayList<Book>();
		Document doc;
		try {
			doc = Jsoup.connect(
					"http://zhannei.baidu.com/cse/search?s=9429875553405098652&entry=1&ie=gbk&q="
							+ URLEncoder.encode(key, "GB2312")).get();
			Elements divs = doc.select("a[cpos]");
			for (Element element : divs) {
				NetBook book = new NetBook();
				String text = element.text();
				if (text.contains(",")) {
					book.setName(text.split(",")[0]);
				} else {
					book.setName(text.split(" ")[0]);
				}
				book.setUrl(element.attr("href"));
				parseMessage(book);
				books.add(book);
			}
		} catch (IOException e) {
		}
		return books;
	}

	@Override
	public boolean downLoad(Book book, OutputStream os) throws IOException {
		NetBook book1 = (NetBook) book;
		String url = book1.getUrl().endsWith("txt.html") ? book1.getUrl()
				: book1.getUrl() + "/txt.html";
		Document doc = Jsoup.connect(url).get();
		Elements eles = doc.select("#TxtdownTop a");
		String path = "";
		for (Element element : eles) {
			if (element.attr("href").endsWith(".txt")) {
				path = element.attr("href");
			}
		}
		
		InputStream is = new Browser().browerURL(path);
		int len = (int) StreamUtils.copy(is, os);
		return len > 1000;
	}

	private void parseMessage(Book book) throws IOException{
		/**
		 * 解析简介和作者
		 */
		NetBook book1 = (NetBook) book;
		String url = book1.getUrl().endsWith("txt.html") ? book1.getUrl()
				: book1.getUrl() + "/txt.html";
		Document doc = Jsoup.connect(url).get();
		Elements eles = doc.select("#TxtdownTop a");
		eles = doc.select("#BookMl");
		for (Element element : eles) {
			String text = element.text();
			if (element.text().contains("|")) {
				book.setAuthor(text.split("\\|")[0].replace("作者：", "").replace(book.getName(), "").trim());
				book.setDiscribe(text.split("\\|")[1]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		QingKanNetSearch search = new QingKanNetSearch();
		List<Book> books = search.search("完美");
		for (Book book2 : books) {
			System.out.println(book2);
		}
	}
}
