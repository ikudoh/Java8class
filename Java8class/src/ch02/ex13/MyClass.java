package ch02.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyClass {

	public static void main(String [] args) {
		List<String> words = new ArrayList<String>();
		words.add("strawberry");	// イチゴ	10
		words.add("mandarin");		// みかん	8
		words.add("pear");			// 梨		4
		words.add("pomegranate");	// ザクロ	11
		words.add("cherry");		// さくらんぼ	6
		words.add("pineapple");		// パイナップル	9
		words.add("apple");			// りんご	5
		words.add("watermelon");	// スイカ	10
		words.add("persimmon");		// 柿		9
		words.add("apricot");		// あんず	7
		words.add("valencian orange");	//バレンシアオレンジ	16

		Map<Integer, Long> wordsMap = words.stream().parallel()
				.filter(s -> s.length() < 12)
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));

		for(Map.Entry<Integer, Long> e : wordsMap.entrySet()) {
			System.out.println("文字列長：" + e.getKey() + ", 単語数：" + e.getValue());
		}
	}
}
