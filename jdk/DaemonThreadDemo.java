public class DaemonThreadDemo extends Thread {

  public static void main(String[] args) {

    Thread daemonThread = new DaemonThreadDemo();
    daemonThread.setDaemon(true);
    daemonThread.start();

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void run() {
    while (true) {
      System.out.println("I'm alive.");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
