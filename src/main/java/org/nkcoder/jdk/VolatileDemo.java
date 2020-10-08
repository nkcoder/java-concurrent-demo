package org.nkcoder.jdk;

public class VolatileDemo {

  private volatile boolean initDone = false;

  public void initWorkDone() {
    this.initDone = true;
    System.out.println("init done.");
  }

  /**
   * start to work.
   */
  public void startToWork() {
    while (true) {
      if (initDone) {
        System.out.println("init done, start to work");
        break;
      }
    }
  }

  /**
   * entry.
   */
  public static void main(String[] args) throws InterruptedException {
    VolatileDemo volatileDemo = new VolatileDemo();
    Thread t1 = new Thread(() -> {
      volatileDemo.startToWork();
    });

    Thread t2 = new Thread(() -> {
      volatileDemo.initWorkDone();
    });

    t1.start();
    t2.start();
    t1.join();;
    t2.join();
  }
}
