package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionDemo implements Runnable {
  private static ReentrantLock lock = new ReentrantLock();
  private static Condition condition = lock.newCondition();

  @Override
  public void run() {
    try {
      lock.lock();
      condition.await();
      System.out.println(Thread.currentThread().getId() + ": I'm done.");
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  /**
   * main.
   * 
   * @param args args
   * @throws InterruptedException exception
   */
  public static void main(String[] args) throws InterruptedException {
    ReentrantLockConditionDemo conditionDemo = new ReentrantLockConditionDemo();
    Thread t1 = new Thread(conditionDemo);

    t1.start();

    TimeUnit.SECONDS.sleep(3);

    try {
      lock.lock();
      condition.signalAll();
    } finally {
      lock.unlock();
    }
  }
}
