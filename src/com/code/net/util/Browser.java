package com.code.net.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 浏览器对象
 */
public class Browser {
	/**
	 * 访问一个网址，并读取所有源码
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public InputStream browerURL(URL url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		conn.setConnectTimeout(5000);
		conn.connect();
		int code = conn.getResponseCode();
		if (code == 200) {
			return conn.getInputStream();
		}
		return null;
	}

	public InputStream browerURL(String string) throws IOException {
		return browerURL(new URL(string));
	}

}
