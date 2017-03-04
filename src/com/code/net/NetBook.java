package com.code.net;

import java.awt.image.BufferedImage;

import com.code.entity.Book;

public class NetBook extends Book {
	private String url;
	private int netSearchId;
	private BufferedImage image;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getNetSearchId() {
		return netSearchId;
	}

	public void setNetSearchId(int netSearchId) {
		this.netSearchId = netSearchId;
	}

}
