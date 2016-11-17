package ch02.ex06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyClass {

	public static Stream<Character> characterStream(String s) {
//		List<Character> result = new ArrayList<>();
//		for(char c : s.toCharArray()) {
//			result.add(c);
//		}
//		return result.stream();
		return IntStream.range(0, s.length()).mapToObj(s::charAt);
	}
}
