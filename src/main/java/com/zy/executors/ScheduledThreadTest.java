package com.zy.executors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadTest {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        Map<Integer, ScheduledFuture<?>> futures = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            ScheduledFuture<?> sf = executor.scheduleAtFixedRate(new Runnable() {
                int number = index;

                @Override
                public void run() {
                    System.out.println(number);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }, 1, 1, TimeUnit.SECONDS);

            futures.put(i, sf);
        }

        Thread.sleep(5000);

        for (int i = 0; i < 5; i++) {
            ScheduledFuture<?> sf = futures.get(i);
            if (sf.isCancelled())
                System.out.println("thread " + i + " is cancelled");
            else {
                if (sf.cancel(true))
                    System.out.println("thread " + i + " is cancelled right now");
                else
                    System.out.println("thread " + i + " is fail to cancel");
            }

            Thread.sleep(2000);
        }

        executor.shutdown();
    }

}
