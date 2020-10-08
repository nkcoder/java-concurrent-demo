package org.nkcoder.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixThreadPoolDemo {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    for (int i = 0; i < 20; i++) {
      executorService.submit(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("I'm done.");
      });
    }

    executorService.shutdown();
  }
}
