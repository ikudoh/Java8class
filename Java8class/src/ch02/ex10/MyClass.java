package ch02.ex10;

import java.util.stream.Stream;

public class MyClass {

	private static int count = 0;

	public static void main(String [] args) {

		// Q. 単純に合計を計算してcountで割れない理由は？
		// A. countも合計計算も終端操作だから。

		Stream<Double> sd = Stream.of(1.2, 3.4, 5.6, 7.8);
		Double sum = sd.reduce(0.0, (x, y) -> {
			x = x + y;
			count++;
			return x;
		});
		System.out.println("合計 : " + sum);
		System.out.println("平均 : " + (sum / count));

	}
}
