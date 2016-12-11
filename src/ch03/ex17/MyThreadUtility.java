package ch03.ex17;

import java.util.function.Consumer;


public class MyThreadUtility {


	public static <T> void doInParallelAsync(
			Runnable first, Runnable second, Consumer<Throwable> handler){

		Thread firstTh = new Thread(
				() -> {
					try {
						first.run();
					} catch(Throwable t1) {
						handler.accept(t1);
					}
				});

		Thread secondTh = new Thread(
				() -> {
					try {
						second.run();
					} catch(Throwable t2) {
						handler.accept(t2);
					}
				});

		firstTh.start();
		secondTh.start();
	}

	public static void main(String[] args) {

		System.out.println("正常系（エラーなし）");
		MyThreadUtility.doInParallelAsync(
				() -> {
					System.out.println("first");
				},
				() -> {
					System.out.println("second");
				},
				(handler) -> {
					System.out.println(", handler = " + handler);
				}
				);

//		System.out.println("異常系（firstでエラー）");
//		MyThreadUtility.doInParallelAsync(
//				() -> {
//					throw new RuntimeException("throw first");
//				},
//				() -> {
//					System.out.println("second");
//				},
//				(handler) -> {
//					System.out.println("handler = " + handler);
//				}
//				);

//		System.out.println("異常系（secondでエラー）");
//		MyThreadUtility.doInParallelAsync(
//				() -> {
//					System.out.println("first");
//				},
//				() -> {
//					throw new RuntimeException("throw second");
//				},
//				(handler) -> {
//					System.out.println("handler = " + handler);
//				}
//				);
	}
}
