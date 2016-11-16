package ch01.ex11;

public class IS extends S implements I2 {


	/**
	 * I, Sのfx()メソッド組み合わせ
	 *
	 * メソッド | I         | S
	 * ----------------------------------
	 * f11()    | 抽象      | static
	 * f12()    | 抽象      | instance
	 * f13()    | デフォルト| static
	 * f14()    | デフォルト| instance
	 * f15()    | static    | static
	 * f16()    | static    | instance
	 *
	 */

//	上記の組み合わせで実装したときの本クラスのエラーは以下
	public static void main(String[] args) {
		IS is = new IS();
//		- static メソッド f11() は I2 の抽象メソッドと競合します
//		- 継承メソッド S.f11() は I2 内の public 抽象メソッドを隠蔽できません
		// 実行不可
//		IS.f11();

//		継承メソッド S.f12() は I2 内の public 抽象メソッドを隠蔽できません
		// override必須
		is.f12();

//		- static メソッド f13() は I2 の抽象メソッドと競合します
//		- 継承メソッド S.f13() は I2 内の public 抽象メソッドを隠蔽できません
		// 実行不可
//		is.f13();

//		- 継承メソッド S.f14() は I2 内の public 抽象メソッドを隠蔽できません
		// override必須
		is.f14();

		IS.f15();	// S-f15

		is.f16();	// S-f16
	}

	@Override
	public void f12() {
		super.f12();
	}
	@Override
	public void f14() {
		super.f14();
	}
}
