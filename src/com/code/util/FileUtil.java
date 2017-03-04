package com.code.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {
	public static String randomString(File file) throws IOException {
		StringBuilder builder = new StringBuilder();
		if (file != null
				&& (file.getName().endsWith(".txt") || file.getName().endsWith(
						".TXT"))) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "GB2312"));
			for (int i = 0; i < 50; i++) {
				reader.readLine();
			}
			for (int i = 0; i < 20; i++) {
				builder.append(reader.readLine());
			}
			reader.close();
		} else {
			builder.append("本书暂无简介！");
		}
		String result = builder.toString();
		if (result.length() > 240) {
			result = result.substring(0, 240);
		}
		return result;
	}

	public static void main(String[] args) {
		File file = new File("F:/data/book/dsyq/尘埃眠于光年-夏茗悠.txt");
		System.out.println(file.getAbsolutePath());
	}
}
