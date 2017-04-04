package com.code.test;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.code.dao.ICartDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;
import com.code.service.impl.BookServiceImpl;
import com.code.util.FileUtil;
import com.code.util.PathUtil;

public class BookServiceTest {
	@Test
	public static void main(String[] args) throws Exception {
		BookServiceImpl impl = new BookServiceImpl();
		for(int i = 0;i<8700;i++){
			Book  book = impl.findBookById(i);
			if(book!=null){
				int price = (int) (Math.random()*100+100);
				book.setPrice(price);
				book.setDiscount(price);
				impl.updateBook(book);
				System.out.println(book.toString());
			}
		}
	}

	public void testAdd(File file, int typeId) throws Exception {
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				testAdd(file2, typeId);
			}
			String auth = "皓叶";
			String name = file2.getName().replace(".txt", "")
					.replace(".TXT", "").replace("《", "").replace("》", "");
			if (name.contains("-")) {
				auth = name.split("-")[1];
				name = name.split("-")[0];
			}
			IBookService service = ServiceFactory.getInstance()
					.newBookService();
			Book book = new Book();
			book.setName(name);
			book.setContent(PathUtil.toRePath(file2.getAbsolutePath()));
			book.setAuthor(auth);
			book.setPrice(random(10, 30));
			book.setDiscount(randomDiscount(2, book.getPrice()));
			book.setDiscribe(FileUtil.randomString(file2));
			book.setImg(null);
			book.setSales(random(10, 1000));
			book.setTime(randomTimeStamp());
			book.setStock(random(100, 10000));
			book.setType(typeId);
			int id = service.addBook(book);
			System.out.println(id);
			Thread.sleep(200);
			
		}

	}
	
	private int randomDiscount(int max,int min){
		double ran = Math.random();
		if(ran>0.88){
			return random(min, max);
		}
		return 0;
	}

	private Timestamp randomTimeStamp() {
		Calendar ca = Calendar.getInstance();
		ca.set(random(2010, 2016), random(0, 11), random(0, 29), random(0, 60),
				random(0, 60), random(0, 60));
		return new Timestamp(ca.getTimeInMillis());
	}

	private int random(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	@Test
	public void testRandomBook() throws Exception {
		IBookService service = ServiceFactory.getInstance().newBookService();
		List<Book> books = service.randomBooks(12);
		for (Book book : books) {
			System.out.println(book);
		}
	}
	@Test
	public void testBookService(){
//		ICartService service = ServiceFactory.getInstance().newCartService();
		ICartDAO dao = DaoFactory.newInstance().newCartDAO();
		System.out.println(dao.addBook(531,100000));
	}
}
