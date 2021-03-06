package ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextWriter {

	public void print() {
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Paths.get("/user/share/dict/words"));
			out = new PrintWriter("/tmp/out.txt");
			while(in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
		}
	}
}
