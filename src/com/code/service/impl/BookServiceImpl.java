package com.code.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.sf.json.JSONObject;

import com.code.dao.IBookDAO;
import com.code.dao.ICartDAO;
import com.code.dao.ICollectionDAO;
import com.code.dao.IShelfDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Book;
import com.code.entity.BookItem;
import com.code.entity.Cart;
import com.code.entity.Collection;
import com.code.entity.Shelf;
import com.code.service.IBookService;
import com.code.util.BookUtil;
import com.code.util.PathUtil;
import com.code.util.StringUtil;

public class BookServiceImpl implements IBookService {
	private IBookDAO bookDao = DaoFactory.newInstance().newBookDAO();
	private ICartDAO cartDao = DaoFactory.newInstance().newCartDAO();
	private IShelfDAO shelfDao = DaoFactory.newInstance().newShelfDAO();
	private ICollectionDAO cltionDao = DaoFactory.newInstance().newCollectionDAO();

	@Override
	public List<Book> randomBooks(int num) {
		Random random = new Random();
		int minId = bookDao.minBookId();
		int maxId = bookDao.maxBookId();
		int length = maxId - minId + 1;
		int id;
		int[] ids;
		if (num > length) {
			// 没有那么多书
			ids = new int[length];
			for (int i = 0; i < length; i++) {
				ids[i] = minId + i;
			}
		} else {
			// 产生num个随机数
			Set<Integer> randoms = new HashSet<Integer>();
			while (true) {
				id = random.nextInt(maxId) + 1;
				if (id >= minId && bookDao.findBookById(id) != null) {
					randoms.add(id);
				}
				if (randoms.size() == num) {
					break;
				}
			}
			// 将Set转成int数组
			ids = new int[num];
			Iterator<Integer> iterator = randoms.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				ids[i] = iterator.next();
				i++;
			}
		}
		List<Book> books = new ArrayList<Book>();
		for (int i : ids) {
			books.add(findBookById(i));
		}
		return books;
	}

	@Override
	public List<Book> hotBooks(int num, int from) {
		return bookDao.hotBooks(num, from);
	}

	@Override
	public List<Book> newBooks(int num, int from) {
		return bookDao.newBooks(num, from);
	}

	@Override
	public List<Book> newBooks(int num, int from, int typeId) {
		return bookDao.newBooks(num, from, typeId);
	}

	@Override
	public List<Book> hotBooks(int num, int from, int typeId) {
		return bookDao.hotBooks(num, from, typeId);
	}

	@Override
	public List<Book> searchByPrice(int typeId, int pricea, int priceb, int pageNo, int pageCount, int orderType) {
		int minPrice;
		int maxPrice;
		if (pricea >= 0 && priceb >= 0) {
			if (pricea <= priceb) {
				minPrice = pricea;
				maxPrice = priceb;
			} else {
				minPrice = priceb;
				maxPrice = pricea;
			}
		} else {
			throw new RuntimeException("你特么sb吧,价格输个负值");
		}
		return bookDao.searchByPrice(typeId, minPrice, maxPrice, pageNo, pageCount, orderType);
	}

	@Override
	public Book findBookById(int id) {
		Book book = bookDao.findBookById(id);
		return book;

	}

	@Override
	public List<Book> userBooks(int userId, int page) {
		// 找到用户书架
		Shelf shelf = shelfDao.findShelfById(userId);
		// 拿出书架里的数
		List<BookItem> bis = null;
		List<Book> books = new ArrayList<Book>();
		if (shelf != null) {
			bis = shelf.getBooks();

			int len = bis.size();
			if (len >= page * 14) {
				len = page * 14;
			}
			for (int i = (page - 1) * 14; i < len; i++) {
				books.add(bis.get(i).getBook());
			}
		}
		return books;
	}

	@Override
	public List<Book> userBooks(int userId) {
		// 找到用户书架
		Shelf shelf = shelfDao.findShelfById(userId);
		// 拿出书架里的数
		List<BookItem> bis = null;
		List<Book> books = new ArrayList<Book>();
		if (shelf != null) {
			bis = shelf.getBooks();
			for (int i = 0; i < bis.size(); i++) {
				books.add(bis.get(i).getBook());
			}

		}
		return books;
	}

	@Override
	public List<Book> userCollectionBooks(int userId) {
		// 找到用户收藏夹
		Collection cltion = cltionDao.findCollectionByUserId(userId);
		List<Book> books = null;
		if (cltion != null) {
			List<BookItem> bis = cltion.getBooks();
			books = new ArrayList<Book>();
			for (int i = 0; i < bis.size(); i++) {
				books.add(bis.get(i).getBook());
			}

		}
		return books;
	}

	@Override
	public List<Book> userCartBooks(int userId) {
		// 找到用户购物车
		Cart cart = cartDao.findCartByUserId(userId);
		List<BookItem> bis = cart.getBooks();
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < bis.size(); i++) {
			books.add(bis.get(i).getBook());
		}
		return books;
	}

	@Override
	public int countsByTypeId(int typeId) {
		return bookDao.countType(typeId);
	}

	@Override
	public int addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public int countsByTypeId(int typeId, int pricea, int priceb) {
		int maxPrice = Math.max(pricea, priceb);
		int minPrice = Math.min(pricea, priceb);
		return bookDao.countType(typeId, minPrice, maxPrice);
	}

	@Override
	public List<Book> userCollectionBooks(int userId, int page) {
		// 找到用户收藏夹
		Collection cltion = cltionDao.findCollectionByUserId(userId);
		List<Book> books = null;
		if (cltion != null) {
			List<BookItem> bis = cltion.getBooks();
			int len = bis.size();
			if (len >= page * 12) {
				len = page * 12;
			}
			books = new ArrayList<Book>();
			for (int i = (page - 1) * 12; i < len; i++) {
				books.add(bis.get(i).getBook());
			}

		}
		return books;
	}

	@Override
	public List<Book> searchBooks(String keyCode, int num, int from) {
		return bookDao.searchBooks(keyCode, num, from);
	}

	@Override
	public int searchBookCounts(String keyCode) {
		return bookDao.searchBookCounts(keyCode);
	}

	public boolean updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public Book findBookByName(String name) {
		if (name != null && !"".equals(name)) {
			return bookDao.findBooksByKey(name);
		}
		return null;
	}

	@Override
	public String simpleText(int id, String charset) {
		int countLine = 200;
		Book book = this.findBookById(id);
		if (book == null || book.getContent() == null || book.getContent().isEmpty()) {
			return "本书暂不支持试读！";
		}
		File file = new File(PathUtil.toAbsPath(book.getContent()));
		if ((!file.exists()) || (!file.getName().endsWith("txt"))) {
			return "本书暂不支持试读！";
		}
		StringBuilder builder = null;
		try {
			builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			String line = "";
			int count = 0;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
				count++;
				if (count >= countLine) {
					break;
				}
			}
			reader.close();
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "文件系统出现异常";
		}
	}

}
