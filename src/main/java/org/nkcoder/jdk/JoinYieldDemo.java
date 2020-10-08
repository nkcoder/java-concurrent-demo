package org.nkcoder.jdk;

public class JoinYieldDemo {
  private volatile static int total = 0;

  public static void main(String[] args) throws InterruptedException {

    Thread t1 = new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          total += i;
        }
      }
    };

    t1.start();
    t1.join();

    System.out.println("total: " + total);
  }
}
