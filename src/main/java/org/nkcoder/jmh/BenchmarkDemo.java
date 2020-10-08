package org.nkcoder.jmh;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkDemo {

  @State(Scope.Thread)
  public static class MyState {
    private HashMap<String, String> hashMap = new HashMap<>(10000);
    private ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(10000);
    private Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>(10000));

    @Setup
    public void setup() {
      for (int i = 0; i < 10000; i++) {
        hashMap.put(i + "", i + "");
        concurrentHashMap.put(i + "", i + "");
        synchronizedMap.put(i + "", i + "");
      }
    }

    public HashMap<String, String> getHashMap() {
      return hashMap;
    }

    public ConcurrentHashMap<String, String> getConcurrentHashMap() {
      return concurrentHashMap;
    }

    public Map<String, String> getSynchronizedMap() {
      return synchronizedMap;
    }
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void hashMapGet(MyState myState) {
    myState.getHashMap().get("10");
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void hashMapSize(MyState myState) {
    myState.getHashMap().get("10");
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void concurrentHashMapGet(MyState myState) {
    myState.getConcurrentHashMap().size();
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void concurrentHashMapSize(MyState myState) {
    myState.getConcurrentHashMap().size();
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void synchronizedMapGet(MyState myState) {
    myState.getSynchronizedMap().get("10");
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void synchronizedMapSize(MyState myState) {
    myState.getSynchronizedMap().size();
  }

  public static void main(String[] args) throws RunnerException {
    Options options =
        new OptionsBuilder().include(BenchmarkDemo.class.getSimpleName()).forks(2).build();

    new Runner(options).run();
  }
}
