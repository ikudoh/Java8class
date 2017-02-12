package ch08.ex02;

public class Negation {

	public static void main(String[] args) {
		// Integer.MIN_VALUEの否定は 2^31
		// 正の整数の最大Integer.MAX_VALUEは2^31-1のためオーバーフローしてしまう

		// 実行結果：
		//		Exception in thread "main" java.lang.ArithmeticException: integer overflow
		//		at java.lang.Math.negateExact(Math.java:977)
		//		at ch08.ex02.Calc.main(Calc.java:6)
		Math.negateExact(Integer.MIN_VALUE);
	}
}
