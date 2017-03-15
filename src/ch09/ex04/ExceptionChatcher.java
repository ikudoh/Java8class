package ch09.ex04;

public class ExceptionChatcher {

	// Q. 複数例外キャッチの恩恵を得られる状況に遭遇したライブラリ
	//    もしくは共通の例外となるスーパークラスにより恩恵を得られる状況に
	//    遭遇したライブラリには何がある？
	// A. ファイル操作やリフレクションなどのException発生要素が多い操作に関するライブラリ。
	public void reflectionOperator() {
		try {
			Class<?> clazz = Class.forName("hoge");
			clazz.getMethod("fuga");

			// 以下のchatch節どちらもOK
			// } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
		} catch ( ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

}
