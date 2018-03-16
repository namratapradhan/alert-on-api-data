package com.excercise.alert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Conversion {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static LocalDateTime toLocalDateTimefromEpoch(Long epoch) {
		return (epoch != null) ? LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.of("UTC")) : null;
	}

	public static Long fromLocalDateTimeToEpoch(LocalDateTime localDateTime) {
		return (localDateTime != null) ? localDateTime.atZone(ZoneId.of("UTC")).toEpochSecond() : null;
	}

	public static LocalDateTime toLocalDateTimeFromString(String date) {
		return LocalDateTime.parse(date, formatter);
	}

	public static String fromLocalDateTimeToString(LocalDateTime date) {
		return date.format(formatter);
	}

}
