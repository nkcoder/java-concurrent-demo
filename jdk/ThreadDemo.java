public class ThreadDemo {
  public static void main(String[] args) {
    Thread t1 = new Thread() {
      @Override
      public void run() {
        System.out.print("in a new thread.");
      }
    };

    t1.start();

  }
}
