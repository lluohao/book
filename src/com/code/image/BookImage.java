package com.code.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class BookImage {
	private BufferedImage image = new BufferedImage(160, 213,
			BufferedImage.TYPE_3BYTE_BGR);
	private Book book;

	public BookImage(Book book) {
		this.book = book;
		init();
	}

	public void init() {
		Graphics g = image.getGraphics();
		g.setColor(new Color(0x702E04));
		g.fillRect(0, 0, 160, 213);
		String title = book.getName();
		String author = book.getAuthor();
		Font f = new Font("宋体", Font.BOLD, 15);
		g.setColor(Color.WHITE);
		g.setFont(f);
		boolean titleLenght = false;
		if (title.length() <= 7) {
			int i = title.length();
			int begin = 20 + 20 * (7 - i) / 2;
			g.drawString(title, begin, 60);
		} else {
			g.drawString(title.substring(0, 7), 20, 60);
			int i = (title.length() - 7) <= 7 ? (title.length() - 7) : 7;
			int begin = 20 + 20 * (7 - i) / 2;
			g.drawString(title.substring(7), begin, 80);
			titleLenght = true;
		}
		f = new Font("宋体", Font.PLAIN, 15);
		int height = 100;
		if (titleLenght) {
			height += 20;
		}
		if (author == null || "".equals(author)) {
			author = "佚名";
		}
		if (author.length() <= 7) {
			int i = author.length();
			int begin = 20 + 20 * (7 - i) / 2;
			g.drawString(author, begin, height);
		} else {
			g.drawString(author.substring(0, 7), 20, height);
			int i = (author.length() - 7) <= 7 ? (author.length() - 7) : 7;
			int begin = 20 + 20 * (7 - i) / 2;
			height += 20;
			g.drawString(author.substring(7), begin, height);
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public static void main(String[] args) {
		IBookService service=ServiceFactory.getInstance().newBookService();
		Book book=service.findBookById(226);
		BookImage bookImage = new BookImage(book);
		BufferedImage image = bookImage.getImage();
		try {
			ImageIO.write(image, "jpg", new File("123.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
