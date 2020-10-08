package org.nkcoder.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HookThreadPoolDemo {

  public static void main(String[] args) {

    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>()) {
          @Override
          protected void beforeExecute(Thread t, Runnable r) {
            System.out.println(t.getName() + " will run task: " + ((Task) r).getName());
            super.beforeExecute(t, r);
          }

          @Override
          protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("task: is done: " + ((Task) r).getName());
            super.afterExecute(r, t);
          }

          @Override
          protected void terminated() {
            System.out.println("thread pool is terminated.");
            super.terminated();
          }
        };

    for (int i = 0; i < 10; i++) {
      threadPoolExecutor.execute(new Task("task-" + i));
    }

    threadPoolExecutor.shutdown();
  }

  private static class Task implements Runnable {
    private final String name;

    private Task(String name) {
      this.name = name;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public String getName() {
      return name;
    }
  }
}
