package ch08.ex16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {

	public static void printAddress(Path path) {
		Pattern pattern = Pattern.compile(
				// P203を変更 memo : 正規表現チェックに便利なサイト → http://rubular.com/
				"(?<city>[A-Z,a-z]+),(?<state>[A-Z]{2}),(?<zipCode>[0-9]{5}(-[0-9]{4})?)"
				);
		try {
			for (String line : Files.readAllLines(path)) {
				Matcher matcher = pattern.matcher(line);
				if (matcher.matches()) {
					System.out.print("city : "         + matcher.group("city"));
					System.out.print(", site : "      + matcher.group("state"));
					System.out.println(", zipCode : " + matcher.group("zipCode"));
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.dir") + "/src/ch08/ex16/address.txt");
		printAddress(path);
	}
}
