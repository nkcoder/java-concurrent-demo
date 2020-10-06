import java.util.concurrent.TimeUnit;

public class WaitNotifyDemo {
  public static void main(String[] args) {
    final Object object = new Object();

    Thread t1 = new Thread("t1") {
      @Override
      public void run() {
        synchronized (object) {
          System.out.println(
              Thread.currentThread().getName() + ", start at " + System.currentTimeMillis());
          try {
            object.wait();
          } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
          }
          System.out
              .println(Thread.currentThread().getName() + ", end at " + System.currentTimeMillis());
        }
      }
    };

    Thread t2 = new Thread("t2") {
      @Override
      public void run() {
        synchronized (object) {
          System.out.println(
              Thread.currentThread().getName() + ", start at " + System.currentTimeMillis());
          object.notify();
          try {
            TimeUnit.SECONDS.sleep(2);
          } catch (InterruptedException ex) {
            System.out.println(ex.getStackTrace());
          }
          System.out
              .println(Thread.currentThread().getName() + ", end " + System.currentTimeMillis());
        }
      }
    };

    t1.start();
    t2.start();
  }

}

