package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileOperator {

	public static void writeToReverse(Path input, Path output) {
		try {
			List<String> lines = Files.readAllLines(input);
			Collections.reverse(lines);
			Files.write(output, lines);
			System.out.println("completed : " + output.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String parentPath = System.getProperty("user.dir") + "/src/ch09/ex06";
		Path input = Paths.get(parentPath + "/Twinkle.txt");
		Path output = Paths.get(parentPath + "/ReverseTwinkle.txt");
		FileOperator.writeToReverse(input, output);
	}
}
