package ch08.ex08;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class Checker {

	public static void main(String[] args) throws IOException {
//		P191-192
		Queue<Path> q = new ArrayDeque<>();
		q = Collections.checkedQueue(q, Path.class);
		Path aliceContents = Paths.get(System.getProperty("user.dir") + "/src/ch08/ex08/Alice.txt");
		if(q.offer(aliceContents)) {
			// Path型はOK。ここに入る。
			System.out.println("aliceContentsはPath型だよ");
		}
		String str = "abcdefg";
		// strはPath型じゃないのでコンパイルエラー
//		if(q.offer(str)) {
//			System.out.println("abcdefgはPathだよ");
//		}
		// 実行時ではなく、コンパイルエラーで型チェックの結果を
		// 教えてくれるので安全性が増す。

	}
}
