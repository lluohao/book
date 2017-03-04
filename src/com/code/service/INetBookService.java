package com.code.service;

import java.io.IOException;
import java.util.List;

import com.code.entity.Book;

public interface INetBookService {
	List<Book> search(String key);

	Book downloadBook(Book book) throws IOException;
}
