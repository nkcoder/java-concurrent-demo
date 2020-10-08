package org.nkcoder.jdk;

public class SynchronizedDemo {
  public static void main(String[] args) throws InterruptedException {

    CalculateRunnable calculateThread = new CalculateRunnable();

    // they should reference the same runnable
    Thread t1 = new Thread(calculateThread);
    Thread t2 = new Thread(calculateThread);

    t1.start();
    t2.start();
    t1.join();
    t2.join();

  }

  public static class CalculateRunnable implements Runnable {
    private int base = 0;

    public synchronized void increase() {
      base++;
    }

    @Override
    public void run() {

      for (int i = 0; i < 100000; i++) {
        increase();
      }

      System.out.println("base: " + base + ", thread: " + Thread.currentThread().getName());
    }
  }
}
