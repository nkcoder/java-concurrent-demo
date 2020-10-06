public class InterruptDemo {
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new MyRunnable());
    t1.start();
    Thread.sleep(3000);
    t1.interrupt();
  }

  public static class MyRunnable implements Runnable {

    @Override
    public void run() {
      while (true) {
        // when current thread is interrupted, stop
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("I'm interrupted, exit.");
          break;
        }

        try {
          Thread.sleep(2000);
        } catch (InterruptedException ex) {
          // when interrupt in sleep()|join()|wait(), the interrupt status is cleared
          System.out.println("I'm interrupted when sleeping.");
          // interrupt again to set interrupt status
          Thread.currentThread().interrupt();;
        }
      }
    }
  }
}
