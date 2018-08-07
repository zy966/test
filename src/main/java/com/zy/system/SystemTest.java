package com.zy.system;

public class SystemTest {

	public static void main(String[] args) {
		int core = Runtime.getRuntime().availableProcessors();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		
		System.out.println("core : " + core);
		System.out.println("totalMemory : " + totalMemory/1024/1024 + " MB");
		System.out.println("freeMemory  : " + freeMemory/1024/1024 + " MB");
	}

}
