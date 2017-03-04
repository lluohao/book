package com.code.net;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.code.entity.Book;
import com.code.net.util.Browser;
import com.code.net.util.StreamUtils;

/**
 * 炫书网
 * @author losthistory
 *
 */
public class XSNetSearch implements NetSearch{

	@Override
	public List<Book> search(String key) {
		Browser browser = new Browser();
		List<Book> books = new ArrayList<Book>();
		try {
			InputStream is = browser.browerURL("http://www.3uww.com/search.html?searchkey="+URLEncoder.encode(key,"UTF-8"));
			String str = StreamUtils.readString(is);
			Pattern pattern = Pattern.compile("\\d{1,2}、\\[<.*");
			Matcher matcher = pattern.matcher(str);
			while(matcher.find()){
				books.add(parseBook(matcher.group()));
			}
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return books;
	}

	private Book parseBook(String group) {
		NetBook book = new NetBook();
		/**
		 * 解析名字
		 */
		Pattern pattern = Pattern.compile("title=\"[^\"]*");
		Matcher matcher = pattern.matcher(group);
		if(matcher.find()){
			book.setName(matcher.group().replace("title=\"", ""));
		}
		/**
		 * 解析作者
		 */
		pattern = Pattern.compile(book.getName()+"/作者：[^\\<]*");
		matcher = pattern.matcher(group);
		if(matcher.find()){
			book.setAuthor(matcher.group().split("/作者：")[1]);
		}
		
		/**
		 * 解析地址
		 */
		pattern = Pattern.compile("\\d{3,}.html");
		matcher = pattern.matcher(group);
		if(matcher.find()){
			book.setUrl(matcher.group().replace(".html", ""));
		}
		return book;
	}

	@Override
	public boolean downLoad(Book book, OutputStream os) throws IOException {
		NetBook book1 = (NetBook) book;
		Browser browser = new Browser();
		InputStream is = browser.browerURL("http://www.3uww.com/home/down/txt/id/"+book1.getUrl());
		StreamUtils.copy(is, os);
		return true;
	}
	
	public static void main(String[] args) {
		XSNetSearch xs = new XSNetSearch();
		List<Book> books = xs.search("总");
		for (Book book : books) {
			System.out.println(book.getName());
			try {
				xs.downLoad(book, new FileOutputStream(book.getName()+".txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
