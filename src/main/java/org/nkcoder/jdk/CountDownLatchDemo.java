package org.nkcoder.jdk;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

  private void doSomeTask(CountDownLatch countDownLatch) {
    try {
      System.out.println("I'm trying to do some task: " + Thread.currentThread().getName());
      Thread.sleep(new Random().nextInt(1000));
      countDownLatch.countDown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("task done by thread: " + Thread.currentThread().getName());
  }

  public static void main(String[] args) {
    CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
    CountDownLatch countDownLatch = new CountDownLatch(10);

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 10; i++) {
      executorService.submit(() -> countDownLatchDemo.doSomeTask(countDownLatch));
    }

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("main task continue.");
    executorService.shutdown();
  }
}
