package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DayChecker {

	private static final LocalDate BIRTHDAY = LocalDate.of(1986,  2, 18);

	public static void main(String[] args) {
		long days = BIRTHDAY.until(LocalDate.now(), ChronoUnit.DAYS);
		System.out.println("生きてきた日数：" + days);
	}
}

