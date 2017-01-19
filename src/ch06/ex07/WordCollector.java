package ch06.ex07;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCollector {
	public List<String> words;

	public WordCollector(String path) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + path)),
					StandardCharsets.UTF_8);
			words = Arrays.asList(contents.split("[\\P{L}]+"));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void printFrequentWords() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map = (ConcurrentHashMap<String, Long>)words.stream()
				.map(String::toLowerCase)
				.collect(
						Collectors.toConcurrentMap(
								Function.identity(),
								wordCount -> 1L,
								Long::sum
								)
						);
		Map.Entry<String, Long> maxCountNum = map.reduceEntries(
			    1,
			    (wordCount1, wordCount2) -> wordCount1.getValue() > wordCount2.getValue() ? wordCount1: wordCount2
			);
		System.out.println("一番多い単語 : " + maxCountNum.getKey());
	}

	public static void main(String[] args) {
		WordCollector c = new WordCollector("./src/ch06/ex04/Alice.txt");
		c.printFrequentWords();
	}
}
