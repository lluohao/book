package com.code.net;

import java.io.File;
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
import com.code.util.PathUtil;

public class XuanShuNetSearch implements NetSearch {

	@Override
	public List<Book> search(String key) {
		Browser browser = new Browser();
		List<Book> books = null;
		try {
			InputStream is = browser
					.browerURL("http://s.xuanshu.com/cse/search?q="
							+ URLEncoder.encode(key, "gb2312")
							+ "&s=411345338971652212&nsid=0");
			String data = StreamUtils.readString(is, "utf-8");
			Pattern pattern = Pattern
					.compile("<a.+class=\"result-game-item-pic-link\".+>");
			Matcher matcher = pattern.matcher(data);
			books = new ArrayList<Book>();
			while (matcher.find()) {
				Book book = parseNetBook(matcher.group());
				if (book != null) {
					books.add(book);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return books;
	}

	private Book parseNetBook(String group) {
		Browser browser = new Browser();
		int id = 0;
		NetBook book = null;
		try {
			Pattern pattern = Pattern.compile("http.+index.html");
			Matcher macher = pattern.matcher(group);
			if (macher.find()) {
				InputStream is = browser.browerURL(macher.group());
				String data = StreamUtils.readString(is, "gb2312");
				pattern = Pattern.compile("[\\d]+");
				macher = pattern.matcher(macher.group());
				if (macher.find()) {
					id = Integer.parseInt(macher.group().trim());
				}
				pattern = Pattern.compile("<dl>.+</dl>");
				macher = pattern.matcher(data);
				if (macher.find()) {
					book = new NetBook();
					String msg = macher.group();
					if (msg.startsWith("<dl>作&nbsp;&nbsp;&nbsp;&nbsp;者："))
						book.setAuthor(msg.split("作&nbsp;&nbsp;&nbsp;&nbsp;者：")[1]
								.split("</dl>")[0]);
				}
				pattern = Pattern.compile("<h1>.+</h1>");
				macher = pattern.matcher(data);
				if (macher.find()) {
					book.setName(macher.group().replaceAll("<h1>", "")
							.replaceAll("</h1>", ""));
				}
				pattern = Pattern.compile("<a href=\"[\\d]+.html\">.+</a>");
				macher = pattern.matcher(data);
				List<String> urls = new ArrayList<String>();
				while (macher.find()) {
					Pattern pattern1 = Pattern.compile("[\\d]+.html");
					Matcher macher1 = pattern1.matcher(macher.group());
					if (macher1.find()) {
						urls.add("http://www.xuanshu.com/book/" + id + "/"
								+ macher1.group());
					}
				}
				book.setUrls(urls);
				book.setNetId(id);

			}
		} catch (Exception e) {
		}

		return book;
	}

	public boolean downLoad(Book book, OutputStream os) throws IOException {
		NetBook netBook = (NetBook) book;
		Browser browser = new Browser();
		File file  = new File(PathUtil.toAbsPath("root/"+netBook.getName()));
		if (!file.exists()) {
			file.mkdirs();
		}
		for (int i = 0; i < netBook.getUrls().size(); i++) {
			InputStream is = browser.browerURL(netBook.getUrls().get(i));
			String data = StreamUtils.readString(is, "gb2312");
			Pattern pattern = Pattern.compile(".+<br />.+");
			Matcher matcher = pattern.matcher(data);
			String msg = "";
			if (matcher.find()) {
				msg = matcher.group();
				msg = msg.replaceAll("<br />", "\n");
				msg = msg.replaceAll("&nbsp;", "");
			}
			byte[] buff=msg.getBytes();
			os.write(buff);
			os.flush();
		}
		InputStream imgIs = browser
				.browerURL("http://www.xuanshu.com/tupian/0/"
						+ netBook.getNetId() + "/" + netBook.getNetId()
						+ "s.jpg");
		File imgFile = new File(file.getAbsolutePath() + "/index.jpg");
		if(!imgFile.exists()){
			imgFile.createNewFile();
		}
		StreamUtils.copy(imgIs, new FileOutputStream(imgFile));
		book.setImg(PathUtil.toRePath(imgFile.getAbsolutePath()));
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public class NetBook extends com.code.net.NetBook {
		private List<String> urls;
		private int netId;

		public int getNetId() {
			return netId;
		}

		public void setNetId(int netId) {
			this.netId = netId;
		}

		public List<String> getUrls() {
			return urls;
		}

		public void setUrls(List<String> urls) {
			this.urls = urls;
		}
	}

	public static void main(String[] args) {
		XuanShuNetSearch xsns = new XuanShuNetSearch();
		try {
			System.out.println(xsns.search("妖月").get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
