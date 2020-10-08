package org.nkcoder.jdk;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierDemo {

  void doTask(CyclicBarrier cyclicBarrier) {

    try {
      cyclicBarrier.await();
      doTaskOne();
      cyclicBarrier.await();
      doTaskTwo();
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }

  private void doTaskOne() {
    try {
      Thread.sleep(new Random().nextInt(500));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("task one, name: " + Thread.currentThread().getName());
  }

  private void doTaskTwo() {
    try {
      Thread.sleep(new Random().nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("task two, name: " + Thread.currentThread().getName());
  }

  public static void main(String[] args) {
    CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
    final AtomicInteger phase = new AtomicInteger(0);

    final int THREAD_NUMBER = 10;

    Runnable action = () -> {
      if (phase.get() == 0) {
        System.out.println("all threads are ready to do the task.");
        phase.addAndGet(1);
      } else if (phase.get() == 1) {
        System.out.println("all threads have finished task: " + phase.get());
        phase.addAndGet(1);
      } else {
        System.out.println("all threads have finished task: " + phase.get());
      }
    };

    CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUMBER, action);

    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);

    for (int i = 0; i < THREAD_NUMBER; i++) {
      executorService.submit(() -> cyclicBarrierDemo.doTask(cyclicBarrier));
    }

    executorService.shutdown();
  }
}
