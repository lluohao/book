package com.code.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.code.dao.IBookDAO;
import com.code.dao.impl.BookDAOImpl;
import com.code.entity.Book;

public class BookDAOTest {
	@Test
	public void testMaxId() throws Exception {
		IBookDAO dao = new BookDAOImpl();
		System.out.println(dao.maxBookId());
		System.out.println(dao.minBookId());
	}
	@Test
	public void testInt() throws Exception {
		int[] ids = new int[]{1,2,3,4,5};
		System.out.println(Arrays.toString(ids));
	}
	
	@Test
	public void testRandomBooks() throws Exception {
		IBookDAO dao = new BookDAOImpl();
		int[] ids = new int[]{1,2,3,4,5};
		List<Book> books = dao.findBooksByIds(ids);
		System.out.println(books.size());
		for (Book book : books) {
			System.out.println(book.getName());
		}
	}
	
	@Test
	public void searchByPrice() throws Exception {
		IBookDAO dao = new BookDAOImpl();
		List<Book> books = dao.searchByPrice(1, 100, 200, 36, 8, -1);
		System.out.println(books.size());
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	@Test
	public void hotBooks() throws Exception {
		IBookDAO dao = new BookDAOImpl();
		List<Book> books = dao.hotBooks(100, 1,1);
		System.out.println(books.size());
		for (Book book : books) {
			System.out.println(book);
		}
	}
	@Test
	public void newBooks() throws Exception {
		IBookDAO dao = new BookDAOImpl();
		List<Book> books = dao.newBooks(100, 1,-1);
		System.out.println(books.size());
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	@Test
	public void findBook() throws Exception {
		IBookDAO dao = new BookDAOImpl();
//		System.out.println(dao.findBookById(1));
		System.out.println(dao.findBooksByKey("何以笙箫默-顾漫").getAuthor());
	}
	
	@Test
	public void count() throws Exception {
		IBookDAO dao = new BookDAOImpl();
		System.out.println(dao.count());
		System.out.println(dao.countType(1));
	}
	
	@Test
	public void countPrice() throws Exception {
		IBookDAO dao = new BookDAOImpl();
		System.out.println(dao.count());
		System.out.println(dao.countType(1,0,100));
	}
	@Test
	public void searchBooks() throws Exception{
		IBookDAO dao=new BookDAOImpl();
		System.out.println(dao.searchBookCounts("叶"));
	}
	@Test
	public void testUpdate(){
		IBookDAO dao=new BookDAOImpl();
		Book book = new Book();
		book.setName("一生，医生何求");
		book.setId(209);
		book.setContent("root\\book\\dsyq\\一生，医生何求-锦竹.txt");
		book.setDiscount(10);
		System.out.println(dao.updateBook(book));
	}

	
}
