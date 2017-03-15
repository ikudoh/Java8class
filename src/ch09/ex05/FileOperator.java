package ch09.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperator {

	public static void writeToReverse(Path input, Path output) {
		try {
			byte[] bytes = Files.readAllBytes(input);
			String tmpContent = new String(bytes, StandardCharsets.UTF_8);
			StringBuilder content = new StringBuilder(tmpContent);
			content.reverse();
			Files.write(output, content.toString().getBytes(StandardCharsets.UTF_8));
			System.out.println("completed : " + output.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String parentPath = System.getProperty("user.dir") + "/src/ch09/ex05";
		Path input = Paths.get(parentPath + "/Twinkle.txt");
		Path output = Paths.get(parentPath + "/ReverseTwinkle.txt");
		FileOperator.writeToReverse(input, output);
	}
}
