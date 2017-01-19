package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

public class DateChecker {

	public static TemporalAdjuster next(Predicate<LocalDate> baseDay) {
		return w -> {
			LocalDate result = (LocalDate)w;
			do {
				result = result.plusDays(1);
			} while (!baseDay.test(result));
			return result;
		};
	}
}

