package ch01.ex11;

public interface I2 {

//	abstract void f11();
	abstract void f12();
//
//	default void f13() {
//		System.out.println("I-f13");
//	}
	default void f14() {
		System.out.println("I-f14");
	}
//
	static void f15() {
		System.out.println("I-f15");
	}
	static void f16() {
		System.out.println("I-f16");
	}
}
