package ch08.ex14;

import java.util.Objects;

public class MyClass {
	public String directions = null;
	public static String goal = "home";	// ゴール
	public static int distance = 10;		// goalまでの距離

	public int getRestDistance(int runDistance) {
		return distance - runDistance;
	}


	public static void main(String[] args) {
		MyClass mc = new MyClass();
		String directions = null;
		// Q. null時のrequireNonNull()メソッドがもっと役立つエラーメッセージとなる方法は？
		// A. 思いつかない…エラーになる直前の情報を取得できる、操作できる？
		mc.directions = Objects.requireNonNull(directions,
				() -> "directions for " + goal + "(rest distance : "
		+  mc.getRestDistance(0) + " km)" + " must not be null."
			);
	}
}
