package com.code.net;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSOUPTest {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://zhannei.baidu.com/cse/search?s=9429875553405098652&entry=1&ie=gbk&q=%B6%B7").get();
		Elements divs = doc.select("a[cpos]");
		for (Element element : divs) {
			System.out.println(element.attr("href"));
		}
	}
}
