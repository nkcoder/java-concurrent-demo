package org.nkcoder.guava;

import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MoreExecutorsDemo {

  public static void main(String[] args) {
    Executor executor = MoreExecutors.newDirectExecutorService();
    executor.execute(MoreExecutorsDemo::log);

    Executor executor2 = MoreExecutors.directExecutor();
    executor2.execute(MoreExecutorsDemo::log);

    Executor executor3 = MoreExecutors
        .getExitingExecutorService((ThreadPoolExecutor) Executors.newFixedThreadPool(2));
    executor3.execute(MoreExecutorsDemo::log);
  }

  private static void log() {
    System.out.println("I'm running in thread: " + Thread.currentThread().getName());
  }
}
