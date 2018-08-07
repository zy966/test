package com.zy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	public static Jedis create(String host, int port) {
		Jedis ret = new Jedis(host, port);
		return ret;
	}
	
	public static JedisPool createPool(String host, int port) {
		JedisPool ret = new JedisPool(host, port);
		return ret;
	}
	
	public static void main(String[] args) {
		JedisPool pool = createPool("", 6379);
		Jedis jedis = pool.getResource();
		jedis.set("", "");
	}

}
