package com.code.net;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import com.code.net.util.Browser;
import com.code.net.util.StreamUtils;

public class Test {
	public static void main(String[] args) throws IOException {
		Browser browser = new Browser();
		System.out.println(StreamUtils.readString(browser.browerURL("http://m.bookben.com/e/sch/index.php?keyboard="+URLEncoder.encode("斗破","gb2312")+"&field=2"),"GB2312"));
	}
}
