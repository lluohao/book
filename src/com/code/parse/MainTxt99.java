package com.code.parse;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.code.entity.Book;
import com.code.mail.MailSender;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class MainTxt99 {
	public static void parse(int start, int end, int count) {
		IBookService service = ServiceFactory.getInstance().newBookService();
		DownLoader loader = new DownLoader();
		int sum = 0;
		int p = 1;
		for (int i = start; i < end; i++) {
			try {
				URL url = new URL("http://www.txt99.cc/txt/"+i+".html");
				Document doc = Jsoup.parse(url, 5000);
				Element ele = doc.select("#downInfoArea h1").first();
				String title = ele.text();
				title = StringUtils.replace(title.toUpperCase(), "TXT下载", "");
				System.out.println(title);
				File folder = new File("c://data/" + title);
				folder.mkdirs();

				ele = doc.select("#mainSoftIntro p").last();
				String info = ele.text();

				ele = doc.select(".downInfoRowL .img img").first();
				String imgSrc = ele.attr("src");
				if (!imgSrc.contains("notimg")) {
					loader.download(imgSrc, new File("c://data/" + title + "/1.jpg"));
				}

				boolean save = loader.download("http://www.txt99.cc/home/down/txt/id/"+i, new File("c://data/" + title + "/1.txt"));

				if (!save) {
					continue;
				}
				Book book = new Book();
				book.setName(title);
				book.setContent("root/data/" + title + "/1.txt");
				book.setImg("root/data/" + title + "/1.jpg");
				int type = (int) (Math.random() * (24 - 9) + 9);
				if (type == 5) {
					type = 20;
				}
				book.setType(type);
				book.setAuthor("皓叶");
				book.setDiscribe(info);
				book.setTime(new Timestamp((long) (System.currentTimeMillis() - Math.random() * 10000000)));
				book.setSales((int) (Math.random() * 500 + 20));

				book.setPrice((int) (Math.random() * 100 + 100));
				int id = service.addBook(book);
				sum++;
				if (sum >= count) {
					MailSender sender = new MailSender();
					//sender.sendMail("皓叶电子书爬虫", "已经读取了" + p * count + "本书，其中最后ID：" + id, "840230057@qq.com");
					p++;
					sum=0;
				}
				System.out.println(i + "====" + id);
			} catch (Exception e) {
				System.out.println(i + "::" + e.getMessage());
			}
		}
	}
	public static void main(String[] args) {
		parse(35765, 36759, 100);
	}
}
