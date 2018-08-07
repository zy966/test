package com.zy.concurrent;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

	static Exchanger<String> ec = new Exchanger<String>();
	
	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String a = "A";
				String b = null;
				try {
					b = ec.exchange(a);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("1. a : " + a + ", b = " + b);
			}
			
		}).start();;
		
		String a = null;
		String b = "B";
		a = ec.exchange(b);
		System.out.println("2. a : " + a + ", b = " + b);
	}

}
