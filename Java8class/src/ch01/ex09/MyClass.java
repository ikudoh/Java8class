package ch01.ex09;

public class MyClass {

	public static void main(String[] args) {

		Collection2<String> names = new Collection2Impl<>();
		names.add("Peter");
		names.add("Paul");
		names.add("Mary");
		names.forEachIf(System.out::println, (name) -> name.startsWith("P"));
		// Q. どのような場面で活用できるか
		// A. 条件(filter)に一致したものにのみ指定の処理(action)を行いたい場合。
		// ここでは Peter と Paul だけ表示したい場合。
	}
}
