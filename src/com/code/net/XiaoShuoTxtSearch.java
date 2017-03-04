package com.code.net;

import java.io.FileOutputStream;
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
import com.code.util.PathUtil;

public class XiaoShuoTxtSearch implements NetSearch {

	@Override
	public List<Book> search(String key) {
		List<Book> books = new ArrayList<Book>();
		Document doc;
		try {
			doc = Jsoup.connect(
					"http://s.xiaoshuotxt.net/cse/search?s=2878701773780702087&entry=1&x=0&y=0&q="
							+ URLEncoder.encode(key, "GB2312")).get();
			Elements divs = doc.select(".result-item");
			for (Element element : divs) {
				NetBook book = new NetBook();
				Elements ts = element.select(".result-game-item-title-link");
				if (ts.size() > 0) {
					Element line = ts.get(0);
					book.setName(line.text());
					book.setUrl(line.attr("href"));
				}
				// result-game-item-desc
				ts = element.select(".result-game-item-desc");
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

	@Override
	public boolean downLoad(Book book, OutputStream os) throws IOException {
		NetBook book1 = (NetBook) book;
		Document doc = Jsoup.connect(book1.getUrl()).get();
		Elements es = doc.select(".butt a[title]");
		/**
		 * 下载文件
		 */
		if (es.size() > 0) {
			String path = es.get(0).attr("href");
			Document doc1 = Jsoup.connect(path).get();
			Elements eles = doc1.select("a[rel]");
			if (eles.size() > 0) {
				String dpath = eles.get(0).attr("href");
				InputStream is = new Browser().browerURL(dpath);
				StreamUtils.copy(is, os);
				return true;
			}
		}
		/**
		 * 下载图片
		 */
		es = doc.select("img.img1");
		if (es.size() > 0) {
			String imgPath = es.get(0).attr("src");
			InputStream is = new Browser().browerURL(imgPath);
			String outImg = null;
			if (book.getImg() != null) {
				outImg = PathUtil.toAbsPath(book.getImg());
			} else {
				outImg = PathUtil.toAbsPath("root/image/" + book.getName() + ""
						+ (int) (Math.random() * 100000) + ".jpg");
				book.setImg(PathUtil.toRePath(book.getImg()));
			}
			StreamUtils.copy(is, new FileOutputStream(outImg));
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		XiaoShuoTxtSearch search = new XiaoShuoTxtSearch();
		List<Book> books = search.search("斗");
		for (Book book2 : books) {
			System.out.println(book2.getName());
			FileOutputStream fos = new FileOutputStream(book2.getName()
					+ ".txt");
			search.downLoad(book2, fos);
			fos.flush();
			fos.close();
		}
	}

}
