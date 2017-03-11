package com.code.parse;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author LostHistory
 */
public class StreamUtils {
	/**
	 * 
	 * @param isr
	 * @param len
	 * @throws IOException
	 */
	public static String readLine(InputStreamReader isr, int len)
			throws IOException {
		if (isr == null) {
			throw new NullPointerException();
		}
		StringBuilder builder = new StringBuilder();
		int c = isr.read();
		if (c == -1) {
			return null;
		}
		int now = 0;
		while (true) {
			now += c > 255 ? 2 : 1;
			if (now >= len) {
				builder.append((char) c);
				break;
			}
			if (c == '\n' || c == -1) {
				break;
			}
			builder.append((char) c);
			c = isr.read();
		}
		return builder.toString();
	}

	public static void copy(InputStream is, OutputStream os) throws IOException {
		if (is == null || os == null) {
			return;
		}
		byte[] buff = new byte[2048];
		int len = 0;
		while ((len=is.read(buff))>0) {
			os.write(buff, 0, len);
		}
		os.flush();
	}
}
