package ch02.ex12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

		AtomicInteger[] shortWords = new AtomicInteger[12];
		Arrays.setAll(shortWords, n -> new AtomicInteger());
		words.stream().parallel().forEach(s -> {
			if(s.length() < 12) {
				shortWords[s.length()].getAndIncrement();
			}
		});
		System.out.println(Arrays.toString(shortWords));
	}
}
