package ch08.ex09;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

// 出力したものを目視で確認…(--;
public class StreamMakerTest {

	@Test
	public void testConvertForWordStream() {
		Scanner scanner = new Scanner("aaa bbb ccc ddd eee");
		Stream<String> ss  = StreamMaker.convertForWordStream(scanner);
		ss.forEach(System.out::println);
	}

	@Test
	public void testConvertForLineStream() {
		Scanner scanner = new Scanner("a aaa\nbb bbb\nccc cc");
		Stream<String> ss  = StreamMaker.convertForLineStream(scanner);
		ss.forEach(System.out::println);
	}

	@Test
	public void testConvertForIntStream() {
		Scanner scanner = new Scanner("1234567890 111 222 333 444 555 asd 666 kjhgfd 777lkjhgfds");	// 文字が入った個所以降はintと判断されない
		IntStream is = StreamMaker.convertForIntStream(scanner);
		is.forEach(System.out::println);
	}

	@Test
	public void convertForDoubleStream() {
		Scanner scanner = new Scanner("1.2345 6.7 8 90 11.111111111 2.22a23w3 4.444 5.555");	// 文字が入った個所以降はdoubleと判断されない
		DoubleStream ds = StreamMaker.convertForDoubleStream(scanner);
		ds.forEach(System.out::println);
	}
}
