package ch09.ex03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public class ExceptionThrower {

	// Q. キャッチした例外を再度スローする場合、メソッドのthrow宣言にはどう書く？
	// A. 同じエラーもしくはまとめたエラーをそのままthrowsに記載する。
	public void process() throws IOException {
		try {
			File file = new File("hoge");
			FileInputStream is = new FileInputStream(file);
			is.close();
		} catch (FileNotFoundException | UnknownHostException ex) {
			System.out.println("process() : エラーをキャッチしました。");
			throw ex;
		}
	}

	public static void main(String[] args) {
		try {
			new ExceptionThrower().process();
		} catch (IOException ex) {
			System.out.println("main() : process()からthrowされたエラーをキャッチしました。");
		}
	}
}
