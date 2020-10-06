package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {

  private static final ReentrantLock LOCK = new ReentrantLock();
  private static int i = 0;

  @Override
  public void run() {
    for (int j = 0; j < 10000000; j++) {
      LOCK.lock();
      LOCK.lock();
      try {
        i++;
      } finally {
        LOCK.unlock();
        LOCK.unlock();
      }
    }
    System.out.println("i: " + i);
  }

  /**
   * main method.
   * 
   * @param args args
   * @throws InterruptedException exception
   */
  public static void main(String[] args) throws InterruptedException {
    ReentrantLockDemo lockDemo = new ReentrantLockDemo();
    Thread t1 = new Thread(lockDemo);
    Thread t2 = new Thread(lockDemo);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }

}
