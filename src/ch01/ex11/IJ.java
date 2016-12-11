package ch01.ex11;

// I, Jの実装
public class IJ implements I, J {

	/**
	 * I, Jのfx()メソッド組み合わせ
	 *
	 * メソッド | I         | J          | 結果
	 * -------------------------------------------------------
	 * f1()     | 抽象      | 抽象       | override必須
	 * f2()     | 抽象      | デフォルト | Iのf2() override必須。
	 * f3()     | 抽象      | static     | Iのf3() override必須。
	 * f4()     | デフォルト| デフォルト | どちらかを選択してoverride必須。
	 * f5()     | デフォルト| static     | override不要。new MyClass.f5() で Iのf5()呼び出し。
	 * f6()     | static    | static     | override不要。
	 */

	@Override
	public void f1() {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void f2() {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void f3() {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void f4() {
		// TODO 自動生成されたメソッド・スタブ
		J.super.f4();
	}

	public static void main(String[] args) {
		IJ ij = new IJ();
		ij.f5();	//　I-f5
//		ij.f6();	// どっちもstaticなので呼び出せない。
	}
}
