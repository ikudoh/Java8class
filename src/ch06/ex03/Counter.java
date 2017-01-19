package ch06.ex03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Counter {
	public final int THREAD_NUM = 1000;
	public final int COUNTER_NUM = 100000;

	public void printCountTimeLongAdder() {
		LongAdder adder = new LongAdder();
		CountDownLatch latch = new CountDownLatch(THREAD_NUM);
		ExecutorService es = Executors.newCachedThreadPool();
		long startTime = System.nanoTime();
		for(int i = 0; i < THREAD_NUM; i++) {
			es.submit(() -> {	// runnable
				for(int j = 0; j < COUNTER_NUM; j++) {
					adder.increment();
				}
				latch.countDown();
			});
		}
		es.shutdown();
		try {

			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- LongAdder 1,000スレッド、100,000回１づつ増加---");
		System.out.println("経過時間 : " + (System.nanoTime() - startTime)/1E9 + "秒");
	}

	public void printCountTimeAtomicLong() {
		AtomicLong along = new AtomicLong();
		CountDownLatch latch = new CountDownLatch(THREAD_NUM);
		ExecutorService es = Executors.newCachedThreadPool();
		long startTime = System.nanoTime();
		for(int i = 0; i < THREAD_NUM; i++) {
			es.submit(()-> {
				for(int j = 0; j < COUNTER_NUM; j++) {
					along.incrementAndGet();
				}
				latch.countDown();
			});
		}
		es.shutdown();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- AtomicLong 1,000スレッド、100,000回１づつ増加---");
		System.out.println("経過時間 : " + (System.nanoTime() - startTime)/1E9 + "秒");
	}

	public static void main(String[] args) {
		Counter c = new Counter();
		c.printCountTimeAtomicLong();
		c.printCountTimeLongAdder();
	}
}
