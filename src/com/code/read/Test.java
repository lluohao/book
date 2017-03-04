package com.code.read;

import java.io.FileInputStream;
import java.io.IOException;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;
import com.code.util.PathUtil;

public class Test {
	public static void main(String[] args) throws IOException {
		IBookService service = ServiceFactory.getInstance().newBookService();
		Book book = service.findBookById(233);
		FileInputStream fis = new FileInputStream(PathUtil.toAbsPath(book.getContent()));
		BookDate bookDate = new BookDate(fis);
		BookPagination bp = new BookPagination(bookDate, 32, 20);
		System.out.println(bp.getMaxPage());
		System.out.println(bp.getPage(10));
	}
}
