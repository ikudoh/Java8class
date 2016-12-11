package ch01.ex08;

import java.util.ArrayList;
import java.util.List;

public class MyThread {

	public static void main(String[] args) {

		String[] names = {"Peter", "Paul", "Mary"};
		List<Runnable> runners = new ArrayList<>();
		for(String name : names) {
			runners.add(() -> System.out.println(name));
		}

		// Q. これは正当なコードか？各ラムダ式は異なる値をキャプチャするか？
		// A. 正当なコード。実行すると意図通りPeter, Paul, Mary の3種類が表示される。
		System.out.println("--- 拡張for文で実行 ---");
		for(Runnable runner : runners) {
			new Thread(runner).start();
		}

		// Q. 従来のforループを使うとどうなる？
		// A. 拡張for文と同じ
		runners = new ArrayList<>();
		for(int i = 0; i < names.length; i++) {
			String name = names[i];
			runners.add(() -> System.out.println(name));
		}

		System.out.println("--- 従来のfor文で実行 ---");
		for(Runnable runner : runners) {
			new Thread(runner).start();
		}

	}
}
