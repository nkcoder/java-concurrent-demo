package org.nkcoder.threadsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {

  public static void main(String[] args) throws InterruptedException {
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 10; i++) {
      executorService.submit(() -> {
        for (int j = 0; j < 100; j++) {
          atomicIntegerArray.incrementAndGet(j % 10);
        }
      });
    }

    Thread.sleep(100);

    System.out.println("array: " + atomicIntegerArray);

    executorService.shutdown();
  }
}
