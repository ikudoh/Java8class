package ch02.ex04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyClass {

	public static void main(String[] args) {
		int[] values = {1, 4, 9, 16};
		// Q. Stream.of(values)は何になる？
		// A. Stream<int[]>になる。（javaDocに単一要素を含む順次Streamを返します。の記述）
		Stream<int[]> s = Stream.of(values);
		// Q. intのStreamをどうやって取得する？
		// A. IntStreamを使って取得できる ↓
		IntStream is = IntStream.of(values);
		is.forEach(System.out::println);
		// 出力結果
		//		1
		//		4
		//		9
		//		16
	}
}
