package ch01.ex11;

public interface J {
	void f1();
	default void f2() {
		System.out.println("J-f2");
	}
	static void f3() {
		System.out.println("J-f3");
	}

	default void f4() {
		System.out.println("J-f4");
	}
	static void f5() {
		System.out.println("J-f5");
	}

	static void f6() {
		System.out.println("J-f6");
	}
}
