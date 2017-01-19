package ch05.ex09;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZone {

	public static void showTimeDifference(LocalDateTime baseDateTime) {
		ZonedDateTime utcNow = ZonedDateTime.of(baseDateTime, Clock.systemUTC().getZone());

		ZoneId.getAvailableZoneIds().stream()
		    .map(ZoneId::of)
		    .map(zone -> ZonedDateTime.of(baseDateTime, zone))
		    .filter(time -> Duration.between(time, utcNow).toMinutes() % 60 != 0)
		    .forEach(System.out::println);
	}

	public static void main(String[] args) {
		TimeZone.showTimeDifference(LocalDateTime.now());
	}
}
