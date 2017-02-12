package ch08.ex01;

public class Calc {

	public static void main(String[] args) {
		// 加算
		int i = Integer.MAX_VALUE;
		int j = 1;
		System.out.println(Integer.toUnsignedLong(i+j));

		// 減算
		i = Integer.MAX_VALUE + 2;
		j = 1;
		System.out.println(Integer.toUnsignedLong(i - j));

		// 除算
		i = Integer.MAX_VALUE*2;
		j = 3;
		System.out.println(
				Integer.divideUnsigned(i, j) + " ... "
				+ Integer.remainderUnsigned(i, j)
				);

		// 比較
		i = Integer.MAX_VALUE;
		j = Integer.MAX_VALUE+1;		// 符号有だと-2147483648、
										// 符号無だと 2147483648
		System.out.println(Integer.compareUnsigned(i, j));

		// Q. divideUnsigned と remainderUnsigned を使う理由は？
		// A. 今までの / や % だと符号なしの値として解釈されず、
		// 正しい結果が得られない。
		System.out.println(i/j);
		System.out.println(i%j);
	}
}
