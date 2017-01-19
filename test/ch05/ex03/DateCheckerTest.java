package ch05.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DateCheckerTest {

	@Test
	public void testNext() {
		LocalDate today = LocalDate.of(2017,1,20);
		LocalDate actual = today.with(DateChecker.next(w -> w.getDayOfWeek().getValue() < 6));
		LocalDate expect = LocalDate.of(2017, 1, 23);
		assertThat(actual, is(expect));
	}
}
