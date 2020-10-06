package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryLockDemo implements Runnable {
  private static ReentrantLock lock = new ReentrantLock();

  @Override
  public void run() {
    try {
      if (lock.tryLock(3, TimeUnit.SECONDS)) {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getId() + ": my job done.");
      } else {
        System.out.println(Thread.currentThread().getId() + ": get lock failed.");
      }
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    } finally {
      if (lock.isHeldByCurrentThread()) {
        lock.unlock();
      }
    }
  }

  /**
   * main.
   * 
   * @param args args
   */
  public static void main(String[] args) {
    ReentrantTryLockDemo lockDemo = new ReentrantTryLockDemo();
    Thread t1 = new Thread(lockDemo);
    Thread t2 = new Thread(lockDemo);
    t1.start();
    t2.start();
  }

}
