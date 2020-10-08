package org.nkcoder.jdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

  private static Object object = new Object();

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      System.out.println("before job done: " + Thread.currentThread().getName());
      synchronized (object) {
        LockSupport.park();
      }
      System.out.println("job done: " + Thread.currentThread().getName());
    }, "t1");

    Thread t2 = new Thread(() -> {
      System.out.println("before job done: " + Thread.currentThread().getName());
      synchronized (object) {
        LockSupport.park();
      }
      System.out.println("job done: " + Thread.currentThread().getName());
    }, "t2");

    t1.start();
    TimeUnit.SECONDS.sleep(3);

    t2.start();

    LockSupport.unpark(t1);
    LockSupport.unpark(t2);

    t1.join();
    t2.join();
  }
}
