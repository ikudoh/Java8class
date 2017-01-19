package ch05.ex08;

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
		    .map(zone -> {
		    	System.out.print(zone + " : ");
		    	return ZonedDateTime.of(baseDateTime, zone);
		    })
		    .map(time -> Duration.between(time, utcNow))
		    .forEach(duration -> System.out.println(duration));
	}

	public static void main(String[] args) {
		TimeZone.showTimeDifference(LocalDateTime.now());
	}
}

