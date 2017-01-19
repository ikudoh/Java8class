package ch03.ex19;

public class MyClass {


	public static void main(String[] args) {
		// Q. Stream reduce()のBiFunction<U,? super T,U>をBiFunction<? super U,? super T,U>と宣言すべき？
		// A. ↑のようには宣言できない。
		// reduceの引数の型Uはその型ズバリが内部reduce実装の中で使われる。
		// 親クラスがUで実際の型が他の型にしても、型Uになってしまう。
		// （以下JavaDoc抜粋）
//		reduce
//		<U> U reduce(U identity,
//		             BiFunction<U,? super T,U> accumulator,
//		             BinaryOperator<U> combiner)
//
//		指定された単位元、累積関数、および結合的関数を使用して、このストリームの要素に対してリダクションを実行します。これは、次の操作に相当します。
//		     U result = identity;
//		     for (T element : this stream)
//		         result = accumulator.apply(result, element)
//		     return result;
//
//		ただし、順次実行の制約が課されるわけではありません。
//		identity値はコンバイナ関数の単位元でなければいけません。つまり、すべてのuについて、combiner(identity, u)がuに等しくなります。さらに、combiner関数はaccumulator関数と互換性がある必要があります。すべてのuとtについて、次が成り立つ必要があります。
//
//		     combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
	}
}
