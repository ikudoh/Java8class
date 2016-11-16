package ch01.ex06;

public class UncheckedThread {

	public void startUncheckedThread() {
		new Thread(uncheck(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
			//			return null;	// Callable<Void>メソッドを使った場合に必要。
		})).start();
		// catch(InterruptedException) が必要ありません！
	}

	// Q. RunnableExの代わりにCallable<Void>を使えないのはなぜ？
	// A. Callable<Void>メソッドを使うと、サンプルコードにreturn nullを1行追加しないといけない。
	//	private Runnable uncheck(Callable<Void> runner) {
	//		return () -> {
	//			try {
	//				runner.call();
	//			} catch (Exception ex) {
	//				new InterruptedException(ex.getMessage());
	//			}
	//		};
	//	}

	public static Runnable uncheck(RunnableEx runner) {
		return () -> {
			try {
				runner.run();
			} catch (Exception ex) {
				new InterruptedException(ex.getMessage());
			}
		};
	}

	public static void main(String[] args) {
		new UncheckedThread().startUncheckedThread();
	}

}
