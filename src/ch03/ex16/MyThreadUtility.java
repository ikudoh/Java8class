package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.management.RuntimeErrorException;


public class MyThreadUtility {

//	Q. 3つ目のパラメータ（Throwable）は必要か？
//	A. 必要。secondのエラーを処理するために必要。

	public static <T> void doInOrderAsync(
			Supplier<T> first, BiConsumer<T, Throwable> second, Consumer<Throwable> handler){
		Thread t = new Thread() {
			public void run() {
				T result = null;
				try {
					result = first.get();
				} catch (Throwable t1) {
					try {
					second.accept(result, t1);
					return;
					} catch (Throwable t2) {
						handler.accept(t2);
					}
				}
				try {
					second.accept(result, null);
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}

	public static void main(String[] args) {
		MyThreadUtility.doInOrderAsync(() -> {return true;},
				(t, u) -> {
					System.out.println("正常系（期待値：t = true, u = null）：");
					System.out.println(" t : " + t + ", u :" + u);
				},
				(handler) -> {
					System.out.println(handler);
				});

		MyThreadUtility.doInOrderAsync(() -> {throw new RuntimeException();},
				(t, u) -> {
					System.out.println("異常系（期待値：t = null, u = RuntimeException）：");
					System.out.println(" t : " + t + ", u :" + u);
				},
				(handler) -> {
					System.out.println(handler);
				});

		MyThreadUtility.doInOrderAsync(() -> {return true;},
				(t, u) -> {
					System.out.println("異常系（期待値：t = true, handler = RuntimeException）：");
					System.out.print(" t : " + t);
					throw new RuntimeErrorException(null);
				},
				(handler) -> {
					System.out.println(", handler = " + handler);
				});
	}
}
