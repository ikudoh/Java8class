package ch01.ex11;

public interface I {
	abstract void f1();
	abstract void f2();
	abstract void f3();

	default void f4() {
		System.out.println("I-f4");
	}
	default void f5() {
		System.out.println("I-f5");
	}

	static void f6() {
		System.out.println("I-f6");
	}

}
