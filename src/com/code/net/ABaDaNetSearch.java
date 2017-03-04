package com.code.net;

import java.io.File;
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

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.code.entity.Book;
import com.code.net.util.Browser;
import com.code.net.util.StreamUtils;
import com.code.util.PathUtil;

public class ABaDaNetSearch implements NetSearch {

	@Override
	public List<Book> search(String key) {
		List<Book> netBooks = null;
		try {
			Document doc = Jsoup.connect(
					"http://www.abada.com/Book/Search.aspx?SearchClass=1&SearchKey="
							+ URLEncoder.encode(key, "gb2312")
							+ "&SeaButton=搜索").get();
			Elements books = doc.select(".sh_ul");
			netBooks = new ArrayList<Book>();
			for (Element element : books) {
				Element eName = element.select(".uli311").first();
				String name = eName.text();
				String urlPage = eName.attr("href");
				Pattern pattern = Pattern.compile("[\\d]+");
				Matcher macher = pattern.matcher(urlPage);
				String url = "";
				if (macher.find()) {
					String netId = macher.group();
					url = "http://ga2rkp.txbiao.com/txt/"
							+ netId.substring(0, 1) + "/" + netId + "/" + netId
							+ ".rar";
				}
				String author = element.select(".uli31").first().select("a")
						.get(1).text();
				String discribe = element.select(".uli32").first().text();

				String imgUrl = "http://www.abada.com"
						+ element.select(".s_lili").first().select("img")
								.first().attr("src");
				NetBook book = new NetBook();
				book.setName(name);
				book.setAuthor(author);
				book.setDiscribe(discribe);
				book.setUrl(url);
				book.setImgUrl(imgUrl);
				netBooks.add(book);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return netBooks;
	}
	
	@Override
	public boolean downLoad(Book book, OutputStream os) throws IOException {
		Browser browser = new Browser();
		NetBook netBook = (NetBook) book;
		InputStream imgIs = browser.browerURL(netBook.getImgUrl());
		if (book.getImg() == null || !"".equals(book.getImg())) {
			File file = new File(PathUtil.toAbsPath("root/"+book.getName()));
			System.out.println(book.getName());
			if (!file.exists()) {
				file.mkdirs();
			}
			File imgFile = new File(file.getAbsoluteFile() + "/index.jpg");
			if(!imgFile.exists()){
				imgFile.createNewFile();
			}
			StreamUtils.copy(imgIs, new FileOutputStream(imgFile));
			book.setImg(PathUtil.toRePath(imgFile.getAbsolutePath()));
		} else {
			File imgFile = new File(netBook.getImg());
			if(!imgFile.exists()){
				imgFile.mkdir();
			}
			StreamUtils.copy(imgIs, new FileOutputStream(imgFile));
		}
		InputStream is = browser.browerURL(netBook.getUrl());
		return StreamUtils.copy(is, os) > 0;
	}
	public class NetBook extends com.code.net.NetBook{
		private String imgUrl;

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
	}
	public static void main(String[] args) throws UnsupportedEncodingException {

		NetBook book = (NetBook) new ABaDaNetSearch().search("斗").get(0);
		// System.out.println(book.getImage());
		// System.out.println(URLEncoder.encode("斗", "gb2312"));
	}
}
