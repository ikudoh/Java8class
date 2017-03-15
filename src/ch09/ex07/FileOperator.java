package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileOperator {

	public static void getWebContents(URL input, Path output) {
		try (InputStream is = input.openStream()){
			Files.copy(is, output, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("completed : " + output.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			URL input = new URL("http://www.nomadworks.co.jp/htmlsample/sample/2_info/info01/info01.html");
			String parentPath = System.getProperty("user.dir") + "/src/ch09/ex07";
			Path output = Paths.get(parentPath + "/outContents.html");
			FileOperator.getWebContents(input, output);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
