package org.nkcoder.jdk;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyDemo {

  private static ReentrantLock lockOne = new ReentrantLock();
  private static ReentrantLock lockTwo = new ReentrantLock();

  public static void main(String[] args) throws InterruptedException {
    LockThread threadOne = new LockThread(0, "t1");
    LockThread threadTwo = new LockThread(1, "t2");

    threadOne.start();
    threadTwo.start();

    Thread.sleep(500);

    threadTwo.interrupt();
  }

  static class LockThread extends Thread {
    private int order;
    private String name;

    public LockThread(int order, String name) {
      this.order = order;
      this.name = name;
    }

    @Override
    public void run() {
      try {
        if (order == 0) {
          lockOne.lockInterruptibly();
          Thread.sleep(300);
          lockTwo.lockInterruptibly();
        } else {
          lockTwo.lockInterruptibly();
          Thread.sleep(300);
          lockOne.lockInterruptibly();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        if (lockOne.isHeldByCurrentThread()) {
          lockOne.unlock();
        }
        if (lockTwo.isHeldByCurrentThread()) {
          lockTwo.unlock();
        }
      }

      System.out.println("Thread is exited: " + name);
    }
  }
}
