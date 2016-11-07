package ch01.ex02;

import java.io.File;

public class DirectoryManager {

	/**
	 * ラムダ式を使ってサブディレクトリ一覧を取得します.
	 * @param path 検索対象ディレクトリパス
	 * @return サブディレクトリ一覧
	 */
	public File[] getSubDirectorysByLambda(String path) {
		return new File(path).listFiles(file -> file.isDirectory());
	}

	/**
	 * メソッド参照を使ってサブディレクトリ一覧を取得します.
	 * @param path 検索対象ディレクトリパス
	 * @return サブディレクトリ一覧
	 */
	public File[] getSubDirectorysByMethodReference(String path) {
		return new File(path).listFiles(File::isDirectory);
	}


}
