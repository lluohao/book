package com.code.net;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.code.entity.Book;

public interface NetSearch {
	List<Book> search(String key);

	boolean downLoad(Book book, OutputStream os) throws IOException;
}
