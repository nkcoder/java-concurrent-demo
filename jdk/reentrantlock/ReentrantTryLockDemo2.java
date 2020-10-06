
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryLockDemo2 {

  private static ReentrantLock lockOne = new ReentrantLock();
  private static ReentrantLock lockTwo = new ReentrantLock();

  public static void main(String[] args) {
    Thread t1 = new Thread(new TryLockRunnable(0), "t1");
    Thread t2 = new Thread(new TryLockRunnable(1), "t2");

    t1.start();
    t2.start();
  }

  static class TryLockRunnable implements Runnable {

    private int order;

    public TryLockRunnable(int order) {
      this.order = order;
    }

    @Override
    public void run() {
      if (order == 0) {
        while (true) {
          if (lockOne.tryLock()) {
            try {
              try {
                Thread.sleep(50);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              if (lockTwo.tryLock()) {
                System.out.println("My job is done, name: " + Thread.currentThread().getName());
                lockTwo.unlock();
              }
            } finally {
              lockOne.unlock();
            }
          }
        }
      } else {
        while (true) {
          if (lockTwo.tryLock()) {
            try {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              if (lockOne.tryLock()) {
                System.out.println("My job is done, name: " + Thread.currentThread().getName());
                lockOne.unlock();
              }
            } finally {
              lockTwo.unlock();
            }
          }
        }
      }
    }
  }
}
