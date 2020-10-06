public class PriorityDemo {

  public static void main(String[] args) throws InterruptedException {

    HighPriorityThread high = new HighPriorityThread();
    high.setPriority(Thread.MAX_PRIORITY);
    LowPriorityThread low = new LowPriorityThread();
    low.setPriority(Thread.MIN_PRIORITY);

    high.start();
    low.start();

    // high.join();
    // low.join();

    System.out.println("main exit.");

  }

  public static class HighPriorityThread extends Thread {

    private int count = 0;

    @Override
    public void run() {
      synchronized (PriorityDemo.class) {
        for (int i = 0; i < 10000000; i++) {
          count++;
        }
        System.out.println("high is done, count: " + count);
      }
    }
  }

  public static class LowPriorityThread extends Thread {

    private int count = 0;

    @Override
    public void run() {
      synchronized (PriorityDemo.class) {
        for (int i = 0; i < 10000000; i++) {
          count++;
        }
        System.out.println("low is done, count: " + count);
      }
    }
  }
}
