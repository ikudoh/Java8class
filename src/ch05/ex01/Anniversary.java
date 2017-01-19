package ch05.ex01;

import java.time.LocalDate;

public class Anniversary {

	public static final int PROGRAMMERS_DAY = 256;
	
	public static LocalDate getProgrammersDay(LocalDate targetYear) {
		return targetYear.withDayOfYear(PROGRAMMERS_DAY);
	}
}
