package ch02.ex03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MyClass {

// 実行結果
//	--- Java 8 doc (index S) ---
//	stream         : 48814012ナノ秒
//	parallelStream : 7538238ナノ秒
//	--- War and Peace ---
//	stream         : 3608107ナノ秒
//	parallelStream : 2990149ナノ秒

	public static void main(String[] args) throws URISyntaxException, IOException {
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + "\\test\\ch02\\ex03\\java8docS.txt")),
				StandardCharsets.UTF_8);
		List <String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		System.out.println("--- Java 8 doc (index S) ---");
		printProcessingTime(words);

		contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + "\\test\\ch02\\ex03\\warAndPeace.txt")),
				StandardCharsets.UTF_8);
		words = Arrays.asList(contents.split("[\\P{L}]+"));
		System.out.println("--- War and Peace ---");
		printProcessingTime(words);
	}

	static void printProcessingTime(List <String> words) {
		long start = System.nanoTime();
		words.stream().filter(w -> w.length() > 12).count();
		long end = System.nanoTime();
		System.out.println("stream         : " + (end - start) + "ナノ秒");

		start = System.nanoTime();
		words.parallelStream().filter(w -> w.length() > 12).count();
		end = System.nanoTime();
		System.out.println("parallelStream : " + (end - start) + "ナノ秒");
	}

}
