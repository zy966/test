package com.zy.collection;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArryAndLinkedPerformanceTest {

	public static void main(String[] args) {

		int times = 100000000;
		int length = 1024;
		byte[] bs = new byte[length];
		for(int i=0; i<length; i++) {
			bs[i] = 's';
		}
		String elem = bs.toString();
		
		arrayListTest(3, times, elem);

		linkedListTest(3, times, elem);
	}

	private static void arrayListTest(int length, int times, String elem) {
		ArrayList<String> list = new ArrayList<>();

		long start1 = System.currentTimeMillis();

		for (int i = 0; i < length; i++) {
			list.add(elem);
		}

		System.out.println("arrayListTest add: " + (System.currentTimeMillis() - start1));

		long start2 = System.currentTimeMillis();

		for (int i = 0; i < times; i++) {
			list.toArray();
		}

		System.out.println("arrayListTest toArray: " + (System.currentTimeMillis() - start2));
	}
	
	private static void linkedListTest(int length, int times, String elem) {
		LinkedList<String> list = new LinkedList<>();

		long start1 = System.currentTimeMillis();

		for (int i = 0; i < length; i++) {
			list.add(elem);
		}

		System.out.println("linkedListTest add: " + (System.currentTimeMillis() - start1));

		long start2 = System.currentTimeMillis();

		for (int i = 0; i < times; i++) {
			list.toArray();
		}

		System.out.println("linkedListTest toArray: " + (System.currentTimeMillis() - start2));
	}

}
