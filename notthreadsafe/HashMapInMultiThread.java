import java.util.HashMap;

public class HashMapInMultiThread {

  private static HashMap<Integer, String> container = new HashMap<>();

  public static class Insert implements Runnable {

    int start = 0;

    public Insert(int start) {
      this.start = start;
    }

    @Override
    public void run() {
      for (int i = start; i < 100000; i += 2) {
        container.put(i, Integer.toString(i));
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread insertOne = new Thread(new Insert(0));
    Thread insertTwo = new Thread(new Insert(1));

    insertOne.start();
    insertTwo.start();

    insertOne.join();
    insertTwo.join();

    System.out.println("map size: " + container.size());

  }

}
