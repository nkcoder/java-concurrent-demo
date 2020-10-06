package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantFairLockDemo implements Runnable {
  private static ReentrantLock fairLock = new ReentrantLock(true);

  @Override
  public void run() {
    while (true) {
      try {
        fairLock.lock();
        System.out.println(Thread.currentThread().getName() + ": get lock");
      } finally {
        if (fairLock.isHeldByCurrentThread()) {
          fairLock.unlock();
        }
      }
    }
  }

  /**
   * main.
   * 
   * @param args args
   */
  public static void main(String[] args) {
    ReentrantFairLockDemo fairLockDemo = new ReentrantFairLockDemo();
    Thread t1 = new Thread(fairLockDemo, "t1");
    Thread t2 = new Thread(fairLockDemo, "t2");
    t1.start();
    t2.start();
  }

}
