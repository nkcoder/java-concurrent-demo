import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {

  public static void main(String[] args) {
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    doWorkAndDiscardIfOverflow(rateLimiter);
    doWorkAndWaitIfOverflow(rateLimiter);
  }

  private static void doWorkAndDiscardIfOverflow(RateLimiter rateLimiter) {
    for (int i = 0; i < 50; i++) {
      if (!rateLimiter.tryAcquire()) {
        continue;
      }
      new Thread(() -> System.out.println("I'm running"), "d-" + i).start();
    }
  }

  private static void doWorkAndWaitIfOverflow(RateLimiter rateLimiter) {
    for (int i = 0; i < 50; i++) {
      rateLimiter.acquire();
      new Thread(() -> System.out.println("I'm running"), "w-" + i).start();
    }
  }
}
