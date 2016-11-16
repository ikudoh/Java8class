package ch01.ex03;

import java.io.File;

public class DirectoryManager {

	/**
	 * ラムダ式を使って指定した拡張子を持つファイル一覧を取得します.
	 * (File.listFiles(FilenameFilter)の場合)
	 * @param path 検索対象ディレクトリパス
	 * @param extension 取得対象ファイルの拡張子
	 * @return 指定した拡張子のファイル一覧
	 */
	public File[] getFilesByLambda(String path, String extension) {
		return new File(path).listFiles((dir, name) -> name.endsWith("." + extension));
	}

	/**
	 * ラムダ式を使って指定した拡張子を持つファイル一覧を取得します.
	 * (File.list(FilenameFilter)の場合)
	 * @param path 検索対象ディレクトリパス
	 * @param extension 取得対象ファイルの拡張子
	 * @return 指定した拡張子のファイル一覧
	 */
	public File[] getFilesByLambda01(String path, String extension) {
		String[] fileNames = new File(path).list((dir, name) -> name.endsWith(extension));
		File[] retFiles = new File[fileNames.length];
		for(int i = 0; i < fileNames.length; i++) {		// TODO ここ短くなるような…
			retFiles[i] = new File(fileNames[i]);
		}
		return retFiles;
	}
}
