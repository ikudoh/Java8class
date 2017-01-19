package ch05.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.LocalDate;

import org.junit.Test;

public class AnniversaryTest {

	@Test
	public void testGetProgrammersDay_notLeapYear() {
		// うるう年以外の年(2017年)
		LocalDate expect = LocalDate.of(2017, 9, 13);
		LocalDate actual = Anniversary.getProgrammersDay(LocalDate.of(2017, 1, 1));
		assertThat(actual, is(expect));
	}

	@Test
	public void testGetProgrammersDay_leapYear() {
		// うるう年(2020年)
		LocalDate expect = LocalDate.of(2020, 9, 12);
		LocalDate actual = Anniversary.getProgrammersDay(LocalDate.of(2020, 1, 1));
		assertThat(actual, is(expect));
	}
}
