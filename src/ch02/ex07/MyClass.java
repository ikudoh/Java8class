package ch02.ex07;

import java.util.stream.Stream;

public class MyClass {


	public static <T> boolean isFinite(Stream<T> stream) {
		long count = stream.count();
		return  0 < count && count < Long.MAX_VALUE ;
	}
	// Q. isFinite()メソッドを作るよう要求した上司の考えは良いか悪いか。
	// A. 悪い。↑だと無限だったとき値を取れない。
	// また、終端操作の実行が完了するとそのストリーム・パイプラインは消費済とみなされ、
	// 以降使用できなくなるので。
}
