package ch03.ex07;

import java.util.Comparator;

public class MyComparator {

	/**
	 * 比較関数を提供します
	 * @param reversion true:逆順、false:正順
	 * @param ignoreCase true:大文字小文字を無視する、 false:無視しない
	 * @param ignoreSpace　true:空白を無視して比較する、false:無視しない
	 * @return 比較関数
	 */
	public static Comparator<String> comparator(
			boolean reversion,
			boolean ignoreCase,
			boolean ignoreSpace) {

		return (x, y) -> {
			if(reversion) {
				// 比較対象文字列を逆順にする
				StringBuffer sb = new StringBuffer(y);
				y = sb.reverse().toString();
			}
			if (ignoreCase) {
				// 小文字に統一する
				x = x.toLowerCase();
				y = y.toLowerCase();
			}
			if(ignoreSpace) {
				// 空白削除する
				x = x.replace(" ", "");
				y = y.replace(" ", "");
			}
			return x.compareTo(y);
		};
	}

	public static void main(String[] args) {
		String testStr1 = "test Str";
		String testStr2 = "Test Str";
		// 同じ文字列を比較
		System.out.println("文字列比較（"
				+ "最初の引数が2番目の引数より小さい場合は負の整数、"
				+ "両方が等しい場合は0、"
				+ "最初の引数が2番目の引数より大きい場合は正の整数）");
		System.out.println("比較条件：(逆順で比較するか, 大文字小文字を無視するか, 空白を無視して比較するか)");

		System.out.println("0個の条件組み合わせ");
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(false, false, false) : " +
				MyComparator.comparator(false, false, false).compare(testStr1, testStr2));

		System.out.println("1個の条件組み合わせ");
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(true, false, false) : " +
				MyComparator.comparator(true, false, false).compare(testStr1, testStr2));
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(false, true, false) : " +
				MyComparator.comparator(false, true, false).compare(testStr1, testStr2));
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(false, false, true) : " +
				MyComparator.comparator(false, false, true).compare(testStr1, testStr2));

		System.out.println("2個の条件組み合わせ");
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(true, true, false) : " +
				MyComparator.comparator(true, true, false).compare(testStr1, testStr2));
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(true, false, true) : " +
				MyComparator.comparator(true, false, true).compare(testStr1, testStr2));
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(false, true, true) : " +
				MyComparator.comparator(false, true, true).compare(testStr1, testStr2));

		System.out.println("3個の条件組み合わせ");
		System.out.println(testStr1 + " v.s " + testStr2 + " … 比較結果(true, true, true) : " +
				MyComparator.comparator(true, true, true).compare(testStr1, testStr2));

		String testStr = "test Str";
		String rtStset = "rtS tseT";
		System.out.println(testStr + " v.s " + rtStset + " … 比較結果(true, true, true) : " +
				MyComparator.comparator(true, true, true).compare(testStr, rtStset));



	}
}
