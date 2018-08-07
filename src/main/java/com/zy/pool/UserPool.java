package com.zy.pool;

import java.util.concurrent.atomic.AtomicInteger;

public class UserPool extends ElementPool {
	
	private static AtomicInteger id;
	private AtomicInteger elementId;
	
	public UserPool() {
		super();
		this.id = new AtomicInteger();
		this.elementId = new AtomicInteger();
	}

	@Override
	protected Element createElement(ElementPool pool) {
		User user = new User(pool);
		user.setElementId(elementId.getAndIncrement());
		return user;
	}

	public static void main(String[] args) {
		test1();
		
//		test2();
	}

	private static void test1() {
		System.out.println("pool test");
		
		UserPool pool = new UserPool();
		AtomicInteger count = new AtomicInteger();
		
		for (int i = 0; i < 10; i++) {
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							User user = (User) pool.acquire();
							int userId = id.getAndIncrement();
							user.setName("z-" + userId);
							user.setGender(userId%2);
							user.setAge(userId);
							user.setId(userId);
//							System.out.println(Thread.currentThread().getName() + ": " + user.toString());

							Thread.sleep(1);

							if(count.incrementAndGet() % 10000 == 0) {
								System.out.println(10000 + " time");
							}
							
							user.release();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} 
					}
				}

			});
			t1.setName("thread-" + i);
			t1.start();
		}
	}

	private static void test2() {
		System.out.println("test");
		int count = 0;
		for(;;) {
			try {
				byte[] bytes = new byte[102400];
				Thread.sleep(1);
				count++;
				if(count % 10000 == 0) {
					System.out.println(10000 + " time");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
