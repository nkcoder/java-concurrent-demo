package org.nkcoder.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

  public static void main(String[] args) throws InterruptedException {
    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), (r) -> {
          Thread thread = new Thread(r);
          System.out.println("created thread: " + thread);
          return thread;
        }, ((r, executor) -> System.out.println("task is discarded: " + r.toString())));

    for (int i = 0; i < 30; i++) {
      threadPoolExecutor.submit(() -> {
        System.out.println("I'm running.");
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    TimeUnit.SECONDS.sleep(20);
    threadPoolExecutor.shutdown();
  }
}
