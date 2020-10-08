package org.nkcoder.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteOrSubmitDemo {

  private static class Divide implements Runnable {

    private final int divider;
    private final int divisor;

    public Divide(int divider, int divisor) {
      this.divider = divider;
      this.divisor = divisor;
    }

    @Override
    public void run() {
      int result = divider / divisor;
      System.out.println("result is: " + result);
    }
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    for (int i = 0; i < 10; i++) {
      executorService.execute(new Divide(100, i));
      // executorService.submit(new Divide(100, i));
    }

    executorService.shutdown();
  }
}
