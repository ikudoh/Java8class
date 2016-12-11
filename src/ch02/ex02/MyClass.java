package ch02.ex02;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

	public static void main(String[] args) {
		List<String> words = new ArrayList<>();
		words.add("strawberry");	// イチゴ
		words.add("mandarin");		// みかん
		words.add("pear");			// 梨
		words.add("pomegranate");	// ザクロ
		words.add("cherry");		// さくらんぼ
		words.add("pineapple");		// パイナップル
		words.add("apple");			// りんご
		words.add("watermelon");	// スイカ
		words.add("persimmon");		// 柿
		words.add("apricot");		// あんず

		words.stream().filter(w -> {
			System.out.println("### filter in : w : " + w);
			return w.length() > 7;
		}).limit(5).forEach(System.out::println);
		// watermelon以降はsysoutされない。
	}

}
