package ch06.ex02;

public class IdManager {

	// Q. 増加するID列を生成するためにLongAdderは役立つか？
	// A. 役立たない。
	// LongAdderは複数の変数から構成され、その合計値をチェックする。
	// ある１つの変数を更新してから取得した値は他の変数の更新の影響を受けているかもしれず
	// 必ず連番である保証がない。
}