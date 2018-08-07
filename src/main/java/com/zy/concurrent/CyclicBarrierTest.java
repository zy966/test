package com.zy.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	static CyclicBarrier cb = new CyclicBarrier(2, new A());
	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("-1");
				try {
					cb.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("+1");
			}
			
		}).start();;
		System.out.println("-2");
		cb.await();
		System.out.println("+2");

	}

	static class A implements Runnable {
		@Override
		public void run() {
			System.out.println("A");
			
		}
	}

}
