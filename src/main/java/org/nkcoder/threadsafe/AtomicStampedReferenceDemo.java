package org.nkcoder.threadsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

  public static void main(String[] args) {

    AtomicStampedReference<Integer> balance = new AtomicStampedReference<>(15, 0);

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    int stamp = balance.getStamp();

    // recharge
    for (int i = 0; i < 3; i++) {
      executorService.submit(() -> {
        for (int j = 0; j < 1000; j++) {
          int money = balance.getReference();

          if (money < 20) {
            System.out.println("money < 20");
            if (balance.compareAndSet(money, money + 20, stamp, stamp + 1)) {
              System.out.println("money < 20 and recharge done, money: " + balance.getReference());
            }
          } else {
            System.out.println("money > 20, no need to recharge.");
          }
        }
      });
    }

    executorService.submit(() -> {
      for (int j = 0; j < 100; j++) {
        int stamp2 = balance.getStamp();
        int money = balance.getReference();

        if (money > 10) {
          System.out.println("money > 10");
          if (balance.compareAndSet(money, money - 10, stamp2, stamp2 + 1)) {
            System.out.println("money > 10, expend 10 and money: " + balance.getReference());
          }
        } else {
          System.out.println("money < 10 and cannot expend.");
        }
      }
    });
  }
}
