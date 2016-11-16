package ch01.ex12;

import java.util.ArrayList;

public class MyClass<E> extends ArrayList<E> {

	// Q. 新たなstreamメソッドが古いコードのコンパイルを失敗させるシナリオ
	// A. 古いコードに同じ名前のstream()メソッドがあったとき。

	//	ex: stream()があった場合のエラー： 戻りの型は Collection<E>.stream() と互換性がありません。
	//	public void stream() {
	//		System.out.println("hoge");
	//	}

	// Q. バイナリ互換性、jar互換性
	// A. ある。
	//	バイナリ互換性
	//	Java SE 8には、このページの下部に示す非互換性を除き、Java SE 7とのバイナリ互換性があります。
	//	記載された非互換性を除けば、Java SE 7コンパイラによりビルドされたクラス・ファイルはJava SE 8で正常に実行されます。
	//	Java SE 8コンパイラでビルドされたクラス・ファイルは、以前のJava SEリリースでは実行できません。
	//	jar互換性
	//	ソース互換性はある。
	//	ただし、一部のJava SE 8機能の実装では変更が必要であり、
	//	それによってJava SE 7でコンパイルされたコードがJava SE 8でコンパイルできなくなる場合がありました。
	//	http://www.oracle.com/technetwork/jp/java/javase/overview/8-compatibility-guide-2156366-ja.html#A999081

}
