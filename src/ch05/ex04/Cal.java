package ch05.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Cal {

	public static void main(String[] args) {
		Integer month;
		Integer year;
		try {
			month = Integer.parseInt(args[0]);
			year = Integer.parseInt(args[1]);
		} catch (NumberFormatException ex) {
			System.out.println("引数には数値<month year>を入れてください");
			return;
		}
		LocalDate date = LocalDate.of(year, month, 1);
//		LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
		int week = date.getDayOfWeek().getValue();

		System.out.println(year + " / " + month);
		for(int i = DayOfWeek.MONDAY.getValue(); i < week; i++) {
			System.out.print("   ");
		}
		while(date.isBefore(lastDay) || date.equals(lastDay)) {
			System.out.print(String.format("%3d", date.getDayOfMonth()));
			if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				System.out.println();
			}
			date = date.plusDays(1);
		}

	}
}

