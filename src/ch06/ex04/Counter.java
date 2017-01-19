package ch06.ex04;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;

public class Counter {
	public int threadNum;
	public final int counterNum = 100;
	public int lastCounterNum;
	public String[] words;

	public Counter(String path) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + path)),
					StandardCharsets.UTF_8);
			words = (String[]) Arrays.asList(contents.split("[\\P{L}]+")).toArray();
			int wordsLength = words.length;
			threadNum = wordsLength / counterNum + 1;
			lastCounterNum = wordsLength % counterNum;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printMaxWordLength() {
		LongAccumulator accumulator = new LongAccumulator(Math::max, 0);
		CountDownLatch latch = new CountDownLatch(threadNum);
		ExecutorService es = Executors.newCachedThreadPool();
		String maxWord = "";
		for(int i = 0; i < threadNum; i++) {
			int s = i * counterNum;
			int e = i != threadNum - 1 ? s + counterNum : s + lastCounterNum;
			es.submit(() -> {	// runnable
				for(int j = s; j < e; j++) {
					LongAccumulator tmp = accumulator;
					accumulator.accumulate(words[j].length());
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
		System.out.println("Alice.txtの一番長い単語の長さ : " + accumulator);
	}

	public static void main(String[] args) {
		Counter c = new Counter("./src/ch06/ex04/Alice.txt");
		c.printMaxWordLength();
	}
}
