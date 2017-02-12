package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Searcher {

	private static boolean hasSearchWords(Path path, String[] searchWords) {

		Scanner scanner = null;
		try {
			scanner = new Scanner(path, "UTF-8");
			while(scanner.hasNext()) {
				String word = scanner.next();
				for(int i = 0; i < searchWords.length; i++) {
					if(word.equals(searchWords[i])) {
						return true;
					}
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			scanner.close();
		}
	}

	public static void print(Path rootPath, String[] searchWords) {
		// P194 Files.walk() サブディレクトリを含めてすべてを処理する
		try (Stream<Path> stream = Files.walk(rootPath)) {
			stream
			.filter(path -> path.toFile().isFile())
			.filter(path -> path.toFile().getName().endsWith(".java"))
			.filter(path -> hasSearchWords(path, searchWords))
			.forEach(System.out::println);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String[] searchWords =  {"transient", "volatile"};
		Path path = Paths.get("T:/tmp/src");
		print(path, searchWords);
	}
}
