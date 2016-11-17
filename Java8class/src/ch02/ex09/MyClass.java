package ch02.ex09;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

	static <T> ArrayList<String> getSummarizedData1(List<ArrayList<String>> lists) {
		ArrayList<String> list = lists.stream()
				.reduce(
						new ArrayList<String>(),
						(x, y) -> {
							x.addAll(y);
							return x;
						});
		return list;
	}

	static <T> ArrayList<String> getSummarizedData2(List<ArrayList<String>> lists) {
		ArrayList<String> list = lists.stream()
				.reduce(
						(x, y) -> {
							x.addAll(y);
							return x;
						}).orElse(null);
		return list;
	}

	static <T> ArrayList<String> getSummarizedData3(List<ArrayList<String>> lists) {
		ArrayList<String> list = lists.stream()
				.reduce(
						new ArrayList<String>(),
						(x, y) -> {
							x.addAll(y);
							return x;
						},
						(x, z) -> {
							x.addAll(z);
							return x;
						});
		return list;
	}
}
