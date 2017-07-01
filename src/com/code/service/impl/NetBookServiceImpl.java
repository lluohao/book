package com.code.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.code.entity.Book;
import com.code.net.ABaDaNetSearch;
import com.code.net.BookBenNetSearch;
import com.code.net.NetBook;
import com.code.net.NetSearch;
import com.code.net.QingKanNetSearch;
import com.code.net.TXT99NetSearch;
import com.code.net.XSNetSearch;
import com.code.net.XiaoShuoTxtSearch;
import com.code.service.INetBookService;
import com.code.util.PathUtil;

public class NetBookServiceImpl implements INetBookService {

	private List<NetSearch> nss = new ArrayList<>();

	public NetBookServiceImpl() {
		nss.add(new ABaDaNetSearch());
		nss.add(new QingKanNetSearch());
		nss.add(new XiaoShuoTxtSearch());
		nss.add(new XSNetSearch());
		//nss.add(new XuanShuNetSearch());
		nss.add(new TXT99NetSearch());
		nss.add(new BookBenNetSearch());
	}

	@Override
	public List<Book> search(String key) {
		if (key == null || key.isEmpty()) {
			return new ArrayList<Book>();
		}
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < nss.size(); i++) {
			search0(nss.get(i), books, key, i);
		}
		return books;
	}

	private void search0(final NetSearch ns, final List<Book> bs,
			final String key, final int id) {
		long s = System.nanoTime();
		List<Book> temps = ns.search(key);
		if (temps != null && temps.size() > 0) {
			for (Book book : temps) {
				NetBook book1 = (NetBook) book;
				book1.setNetSearchId(id);
			}
			bs.addAll(temps);
		}
		System.out.println(id + ":" + (System.nanoTime() - s));
	}

	@Override
	public Book downloadBook(Book book) throws IOException {
		NetBook nb = (NetBook) book;
		NetSearch ns = nss.get(nb.getNetSearchId());
		String path = PathUtil.toAbsPath("root/book/net/" + nb.getName()
				+ ".txt");
		File file = new File(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		ns.downLoad(nb, fos);
		nb.setContent(PathUtil.toRePath(path));
		fos.flush();
		fos.close();
		return nb;
	}

	public static void main(String[] args) throws IOException {
		NetBookServiceImpl impl = new NetBookServiceImpl();
		List<Book> books = impl.search("萌萌");
		for (Book book : books) {
			System.err.println(book);

		}
	}
}
