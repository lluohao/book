package com.code.net.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author LostHistory
 */
public class StreamUtils {
	/**
	 * 从一个输入流中读取数据并作为字符串返回
	 * 
	 * @param isr
	 * @param len
	 * @throws IOException
	 */
	public static String readString(InputStream is) throws IOException {
		StringBuilder builder = new StringBuilder();
		byte[] buff = new byte[1024 * 4];
		int len = 0;
		while ((len = is.read(buff)) > 0) {
			builder.append(new String(buff, 0, len));
		}
		return builder.toString();
	}

	public static String readString(InputStream is, String charset)
			throws IOException {
		StringBuilder builder = new StringBuilder();
		byte[] buff = new byte[1024 * 4];
		int len = 0;
		while ((len = is.read(buff)) > 0) {
			builder.append(new String(buff, 0, len, charset));
		}
		return builder.toString();
	}

	public static long copy(InputStream is, OutputStream os) throws IOException {
		if (is == null || os == null) {
			return 0;
		}
		byte[] buff = new byte[2048];
		int len = 0;
		int all = 0;
		while ((len = is.read(buff)) > 0) {
			os.write(buff, 0, len);
			all += len;
		}
		os.flush();
		return all;
	}
}
