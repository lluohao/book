package com.code.parse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author �޺�
 *
 **/
public class DownLoader {
	public boolean download(String url, File file) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		int code = conn.getResponseCode();
		if (code == 200) {
			InputStream is = conn.getInputStream();
			OutputStream os = new FileOutputStream(file);
			StreamUtils.copy(is, os);
			is.close();
			os.flush();
			os.close();
			return true;
		}
		return false;
	}
}
