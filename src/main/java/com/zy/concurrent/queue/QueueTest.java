package com.zy.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {

	static int sum = 1000*1000*100;
//	static SynchronousQueue<Long> squeue = new SynchronousQueue<Long>();
//	static ArrayBlockingQueue<Long> squeue = new ArrayBlockingQueue<Long>(100);
	static LinkedBlockingQueue<Long> squeue = new LinkedBlockingQueue<Long>(100);
	
	static CyclicBarrier cb = new CyclicBarrier(2, new A());
	
	public static void main(String[] args) throws Exception {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					cb.await();
				} catch (InterruptedException | BrokenBarrierException e1) {
					e1.printStackTrace();
				}
				long count = 0;
				while(count < sum) {
					try {
						squeue.put(count);
//						System.out.println("write index is " + count);
//						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					count++;
				}
				
				System.out.println("write index is " + (count));
			}
			
		});
		thread.start();
		
		cb.await();
		
		long i = 0;
		long start = System.currentTimeMillis();
		while(true) {
			i = squeue.take();
			if(i >= sum-1) {
				break;
			}
//			System.out.println("read index is " + i);
			i = 0;
//			Thread.sleep(1000);
		}
		
		long end = System.currentTimeMillis();
		i++;
		System.out.println("read  index is " + i);
		System.out.println("spend time  is " + (end - start) + " ms");
		System.out.println("tps         is " + (i*1000)/(end - start) + " t/s");
	}

	static class A implements Runnable {
		@Override
		public void run() {
			System.out.println("start test");
			
		}
	}
}
