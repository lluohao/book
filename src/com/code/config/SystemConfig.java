package com.code.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.code.app.factory.JedisFactory;
import com.code.dao.IBookDAO;
import com.code.dao.impl.BookDAOImpl;
import com.code.entity.Book;
import com.code.util.StringUtil;

import redis.clients.jedis.Jedis;

/**
 * @author 罗浩
 *
 **/
public class SystemConfig {
	private static SystemConfig config = new SystemConfig();

	private Map<String, String> data = new HashMap<>();
	private Jedis jedis = JedisFactory.getJedis();

	private SystemConfig() {
		fresh();
		new Thread() {
			public void run() {
				while (true) {
					long millis = getInt("sc_freshtime");
					try {
						Thread.sleep(millis);
						fresh();
						System.out.println("fresh::::");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	private void fresh() {
		Map<String, String> maps = new HashMap<>();
		Set<String> keys = jedis.keys("sc_*");

		for (String key : keys) {
			maps.put(key, jedis.get(key));
		}
		data = maps;

	}

	public static SystemConfig getConfig() {
		return config;
	}

	public String get(String key) {
		return data.get(key);
	}

	public int getInt(String key) {
		return StringUtil.safeToInteger(get(key), 0);
	}

	public String get(String key, String def) {
		String result = data.get(key);
		return result == null ? def : result;
	}

	public int getInt(String key, int def) {
		return StringUtil.safeToInteger(get(key), def);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("1.txt"));
		List<String> names = new ArrayList<>();
		String line = null;
		while((line=reader.readLine())!=null){
			names.add(line);
		}
		System.out.println(names);
		IBookDAO dao = new BookDAOImpl();
		for (int i = 1; i < 10000; i++) {
			Book book = dao.findBookById(i);
			if(book==null){
				continue;
			}
			book.setAuthor(names.get((int) (Math.random()*names.size())));
			dao.updateBook(book);
			System.out.println(book.getAuthor());
		}
	}
}
