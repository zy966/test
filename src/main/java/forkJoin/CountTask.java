package forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

	private static final int THRESHOLD = 2;
	
	private int start;
	private int end;
	private int level;
	
	public CountTask(int start, int end, int level) {
		this.start = start;
		this.end = end;
		this.level = level;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		boolean canCompute = (end - start) <= THRESHOLD;
		if(canCompute) {
			for(int i = start; i <= end; i++) {
				sum += i;
				try {
					System.out.println(level + "---" + Thread.currentThread().getName());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println(level + "---" + Thread.currentThread().getName());
			int middle = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle, ++level);
			CountTask rightTask = new CountTask(middle + 1, end, ++level);
			
			leftTask.fork();
			rightTask.fork();
			
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();
			
			sum = leftResult + rightResult;
		}
		
		return sum;
	}
	
	
	public static void main(String[] args) throws Exception {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
		CountTask task = new CountTask(1, 8, 1);
		
		long start = System.currentTimeMillis();
		Future<Integer> result = forkJoinPool.submit(task);
		int re = result.get();
		long end = System.currentTimeMillis();
		
		System.out.println(re);
		System.out.println((end - start) + " ms");
	}

}
