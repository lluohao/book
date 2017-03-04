package com.code.read;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private List<String> lines;

	public Page() {
		lines = new ArrayList<String>();
	}

	public void put(String str) {
		lines.add(str);
	}

	public String get(int index) {
		return lines.get(index);
	}

	public int getMaxLineIndex() {
		return lines.size();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (String string : lines) {
			builder.append(string + "\n");
		}
		return builder.toString();
	}
}
