package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class FridayThe13th {

	public static final int THIRTEENTH = 13;

	public static void main(String[] args) {
		System.out.println("20世紀の13日の金曜日");

		LocalDate start = LocalDate.of(1901, 1, 1);
		LocalDate end = LocalDate.of(2000, 12, 31);

		LocalDate date = start;
		while(date.isBefore(end) || date.equals(end)) {
			date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
			if(THIRTEENTH == date.getDayOfMonth()) {
				System.out.println(date);
			}
			date = date.plusDays(1);
		}
	}
}

