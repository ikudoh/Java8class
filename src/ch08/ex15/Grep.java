package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Grep {

	public static void print(Path path, String word) {
		Pattern pattern = Pattern.compile(word);
		Stream<String> stream;
		try {
			// P192 Files.lines
			stream = Files.lines(path);
			// P203 Pattern.asPredicate()
			stream.filter(pattern.asPredicate())
			.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.dir") + "/src/ch08/ex15/Alice.txt");
		String word = "Alice";
		print(path, word);
	}

}
