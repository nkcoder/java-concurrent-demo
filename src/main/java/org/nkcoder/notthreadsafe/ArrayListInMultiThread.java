package org.nkcoder.notthreadsafe;

import java.util.ArrayList;

public class ArrayListInMultiThread {

  private static ArrayList<Integer> container = new ArrayList<>();

  public static class InsertThread implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 1000000; i++) {
        container.add(i);
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread insertOne = new Thread(new InsertThread());
    Thread insertTwo = new Thread(new InsertThread());

    insertOne.start();
    insertTwo.start();

    insertOne.join();
    insertTwo.join();

    System.out.println("contain size: " + container.size());

  }

}
