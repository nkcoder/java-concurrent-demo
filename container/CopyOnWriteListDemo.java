import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteListDemo {

  public static void readAndWriteToList() throws InterruptedException {
    CopyOnWriteArrayList<String> arrayList =
        new CopyOnWriteArrayList<>(Arrays.asList("111", "222"));

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    for (int i = 0; i < 50; i++) {
      executorService.submit(() -> System.out.println("second data: " + arrayList.get(1)));
      if (i % 10 == 0) {
        executorService.submit(() -> arrayList.add(1, "333"));
      }
    }

    executorService.submit(() -> arrayList.add(1, "444"));

    executorService.awaitTermination(3, TimeUnit.SECONDS);

    Iterator iterator = arrayList.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }

    executorService.shutdown();
  }

  public static void main(String[] args) throws InterruptedException {
    readAndWriteToList();
  }
}
