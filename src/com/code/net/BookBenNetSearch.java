package com.code.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.code.entity.Book;
import com.code.net.util.Browser;
import com.code.net.util.StreamUtils;
import com.code.util.TypeUtil;

/**
 * 书本网：http://m.bookben.com
 * @author losthistory
 *
 */
public class BookBenNetSearch implements NetSearch{

	@Override
	public List<Book> search(String key) {
		List<Book> books = new ArrayList<Book>();
		Browser browser = new Browser();
		try {
			InputStream is = browser.browerURL("http://m.bookben.com/e/sch/index.php?keyboard="+URLEncoder.encode(key,"gb2312")+"&field=2");
			String data = StreamUtils.readString(is,"gb2312");
			Pattern pattern = Pattern.compile("<li class=\"li_bg\">.+</li>");
			Matcher matcher = pattern.matcher(data);
			while(matcher.find()){
				Book book = parseNetBook(matcher.group(), key);
				books.add(book);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return books;
	}

	private Book parseNetBook(String line,String key){
		/**
		 * 预处理
		 */
		NetBook netBook = new NetBook();
		line = line.replace("<font color=\"red\">"+key+"</font>", key);
		/**
		 * 解析书名
		 */
		Pattern p = Pattern.compile("<p>[^(<p)]+</p>");
		Matcher matcher = p.matcher(line);
		if(matcher.find()){
			netBook.setName(matcher.group().replace("<p>", "").replace("</p>", ""));
		}
		/**
		 * 解析地址
		 */
		p = Pattern.compile("/txt/.*shtml");
		matcher = p.matcher(line);
		if(matcher.find()){
			netBook.setUrl(matcher.group());
		}
		/**
		 * 解析描述
		 */
		p = Pattern.compile("<p class=\"intro\">[^(<p)]*</p>");
		matcher = p.matcher(line);
		if(matcher.find()){
			netBook.setDiscribe(matcher.group().replace("<p class=\"intro\">", "").replace("</p>", ""));
		}
		
		return netBook;
	}
	
	@Override
	public boolean downLoad(Book book, OutputStream os) throws IOException {
		NetBook sbBook = (NetBook) book;
		Browser browser = new Browser();
		InputStream  is = browser.browerURL("http://m.bookben.com/"+sbBook.getUrl());
		String str = StreamUtils.readString(is, "gb2312");
		/**
		 * 解析分类
		 */
		Pattern p2 = Pattern.compile("<p class=\"className\">[^(<p)]*</p>");
		Matcher matcher2 = p2.matcher(str);
		if(matcher2.find()){
			String typeStr = matcher2.group().replace("<p class=\"intro\">", "").replace("</p>", "");
			sbBook.setType(TypeUtil.machType((typeStr)));
		}
		/**
		 * 解析地址
		 */
		Pattern p = Pattern.compile("/e/download[^'(</a>)]*");
		Matcher matcher = p.matcher(str);
		if(matcher.find()){//如果是第一种情况
			is = browser.browerURL("http://m.bookben.com/"+matcher.group());
			str = StreamUtils.readString(is, "gb2312");
			Pattern p1 = Pattern.compile("http://txt\\.bookben\\.com/c_down.*txt");
			//System.out.println(str);
			Matcher matcher1 = p1.matcher(str);
			if(matcher1.find()){
				is = browser.browerURL(matcher1.group());
				StreamUtils.copy(is, os);
				return true;
			}
			return false;
		}else{//第二种情况
			//href="http://download.qidian.com/pda/1209977.txt"
			p = Pattern.compile("d_2 = '\\d*';");
			matcher = p.matcher(str);
			if(matcher.find()){
				String id = matcher.group();
				id = id.replace("d_2 = '", "").replace("';", "");
				InputStream isdata = browser.browerURL("http://download.qidian.com/pda/"+id+".txt");
				StreamUtils.copy(isdata, os);
			}
		}
		return false;
	}
	public static void main(String[] args) {
		BookBenNetSearch bbns = new BookBenNetSearch();
		List<Book> books = bbns.search("斗破");
		for (Book book : books) {
			System.out.println(book);
			//bbns.downLoad(book, new FileOutputStream(book.getName()+".txt"));
		}
	}
	

}
