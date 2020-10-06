import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicFieldUpdaterDemo {

  public static class Student {
    int id;
    volatile int score;
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();

    AtomicInteger count = new AtomicInteger(0);
    AtomicIntegerFieldUpdater<Student> updaterCount =
        AtomicIntegerFieldUpdater.newUpdater(Student.class, "score");

    Random random = new Random();

    Student student = new Student();

    for (int i = 0; i < 1000; i++) {
      executorService.submit(() -> {
        if (random.nextDouble() > 0.5) {
          count.incrementAndGet();
          updaterCount.incrementAndGet(student);
        }
      });
    }

    executorService.awaitTermination(2, TimeUnit.SECONDS);

    // they should hold the same value
    System.out.println("count: " + count.get() + ", updateCount: " + updaterCount.get(student));

    executorService.shutdown();
  }
}
