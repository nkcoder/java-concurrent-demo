import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

  public static void main(String[] args) {
    AtomicReference<Integer> balance = new AtomicReference<>(15);

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    for (int i = 0; i < 3; i++) {
      executorService.submit(() -> {
        for (int j = 0; j < 1000; j++) {
          int money = balance.get();
          if (money > 20) {
            System.out.println("money > 20, no need to recharge");
          } else {
            balance.compareAndSet(money, money + 20);
            System.out.println("money < 20, recharge done, money: " + balance.get());
          }
        }
      });
    }

    executorService.submit(() -> {
      for (int i = 0; i < 100; i++) {
        int money = balance.get();
        if (money < 10) {
          System.out.println("money < 10, cannot expend.");
        } else {
          balance.compareAndSet(money, money - 10);
          System.out.println("money < 10, expend 10, money: " + balance.get());
        }
      }
    });
  }
}
