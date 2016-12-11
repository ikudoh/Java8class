package ch02.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class MyClassTest {

	@Test
	public void testCharacterStream() {
		String expect = "expectString";
		Stream<Character> ret = MyClass.characterStream(expect);
		String result = ret.map(Object::toString).collect(Collectors.joining());
		assertThat(expect, is(result));
	}

}
