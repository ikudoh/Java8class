package ch01.ex07;

public class MyThread {


	static Runnable andThen(Runnable first, Runnable second) {
		return new Runnable() {

			@Override
			public void run() {
				first.run();
				second.run();
			}
		};

	}

	public static void main(String[] args) {
		Runnable ret = andThen(
				() -> System.out.println("first thread"),
				() -> System.out.println("second thread")
				);
		new Thread(ret).start();
	}
}
