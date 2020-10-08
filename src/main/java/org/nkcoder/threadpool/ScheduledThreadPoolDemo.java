package org.nkcoder.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {

  public static void main(String[] args) throws InterruptedException {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

    executorService.scheduleAtFixedRate(() -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("I'm running at fixed rate.");
    }, 0, 3, TimeUnit.SECONDS);

    executorService.scheduleWithFixedDelay(() -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("I'm running with fixed delay");
    }, 0, 3, TimeUnit.SECONDS);

    TimeUnit.MINUTES.sleep(2);
    executorService.shutdown();
  }
}
