package org.nkcoder.jdk;

@SuppressWarnings("deprecation")
public class BadSuspendResumeDemo {

  private static Object object = new Object();

  public static void main(String[] args) throws InterruptedException {

    ChangeObjectRunnable r1 = new ChangeObjectRunnable("t1");
    ChangeObjectRunnable r2 = new ChangeObjectRunnable("t2");
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);

    t1.start();
    Thread.sleep(200);
    t2.start();

    t1.resume();
    System.out.println("t1 is resumed.");
    t2.resume();
    System.out.println("t2 is resumed");

    t1.join();
    t2.join();
  }

  public static class ChangeObjectRunnable implements Runnable {

    private String name;

    public ChangeObjectRunnable(String name) {
      this.name = name;
    }

    @Override
    public void run() {
      synchronized (object) {
        System.out.println("thread: " + getName() + " starts.");
        Thread.currentThread().suspend();
        System.out.println("thread: " + getName() + " is done.");
      }
    }

    public String getName() {
      return name;
    }
  }

}
