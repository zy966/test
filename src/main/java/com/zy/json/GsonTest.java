package com.zy.json;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	public static void main(String[] args) {
		Map<String , Object> map = new HashMap<>();
		for(int i=0; i<10; i++) {
			map.put("key" + i, i);
		}
				
		
		String json = new Gson().toJson(map);
		int times = 5000000;
		
		test1(json, times);
		test2(json, times);
	}

	private static void test1(String json, int times) {
		Gson gson = new Gson();
		
		long start = System.currentTimeMillis();
		
		for(int i=0; i<times; i++) 
			gson.fromJson(json, HashMap.class);
		
		System.out.println("class: " + (System.currentTimeMillis() - start));
		
	}
	
	private static void test2(String json, int times) {
		long start = System.currentTimeMillis();
		
		Gson gson = new Gson();
		
		for(int i=0; i<times; i++) 
			gson.fromJson(json, new TypeToken<HashMap<String, Object>>(){}.getType());
		
		System.out.println("classType: " + (System.currentTimeMillis() - start));
		
	}

}
