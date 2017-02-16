package ch08.ex09;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamMaker {

	/**
	 * 単語のストリームへ変換
	 * @param scanner
	 * @return
	 */
	public static Stream<String> convertForWordStream(Scanner scanner) {
		return createStream(scanner);
	}

	/**
	 * 行のストリームへ変換
	 * @param scanner
	 * @return
	 */
	public static Stream<String> convertForLineStream(Scanner scanner) {
		Iterator<String> iter = new Iterator<String>() {

			@Override
			public boolean hasNext() {
				return scanner.hasNextLine();
			}

			@Override
			public String next() {
					return scanner.nextLine();
			}
		};
		return createStream(iter);
	}

	/**
	 * 整数のストリームへ変換
	 * @param scanner
	 * @return
	 */
	public static IntStream convertForIntStream(Scanner scanner) {
		Iterator<Integer> iter = new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return scanner.hasNextInt();
			}

			@Override
			public Integer next() {
					return scanner.nextInt();
			}
		};
		return createStream(iter).mapToInt(Integer::intValue);
	}

	/**
	 * doubleのストリームへ変換
	 * @param scanner
	 * @return
	 */
	public static DoubleStream convertForDoubleStream(Scanner scanner) {
		Iterator<Double> iter = new Iterator<Double>() {

			@Override
			public boolean hasNext() {
				return scanner.hasNextDouble();
			}

			@Override
			public Double next() {
					return scanner.nextDouble();
			}
		};
		return createStream(iter).mapToDouble(Double::doubleValue);
	}

	/**
	 * イテレーターからストリームを生成する
	 * @param iter
	 * @return
	 */
	public static <T> Stream<T> createStream(Iterator<T> iter) {
		//memo:ソースの要素をトラバースおよびパーティション化するためのオブジェクト
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				iter, Spliterator.ORDERED), false);
	}
}
