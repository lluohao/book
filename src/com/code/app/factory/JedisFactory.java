package com.code.app.factory;

import java.io.IOException;
import java.util.Properties;

import redis.clients.jedis.Jedis;

public class JedisFactory {
	private static Jedis jedis;

	static {
		Properties prop = new Properties();
		try {
			prop.load(JedisFactory.class
					.getResourceAsStream("/conf/redis.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String portStr = prop.getProperty("port");
		int port = 6379;
		if (portStr != null && !portStr.isEmpty()) {
			port = Integer.parseInt(portStr);
		}
		jedis = new Jedis(prop.getProperty("host"), port);
		String password = prop.getProperty("password");
		if (password != null) {
			jedis.auth(password);
		}
		System.out.println(jedis.get("name"));
	}

	public static Jedis getJedis() {
		return jedis;
	}

	public static void main(String[] args) {

	}
}
