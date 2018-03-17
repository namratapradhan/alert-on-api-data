package com.excercise.alert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {

	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final ZoneId ZONEID_UTC = ZoneId.of("UTC");

	public static LocalDateTime fromEpochToLocalDateTime(Long epoch) {
		return (epoch != null) ? LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZONEID_UTC) : null;
	}

	public static Long fromLocalDateTimeToEpoch(LocalDateTime localDateTime) {
		return (localDateTime != null) ? localDateTime.atZone(ZONEID_UTC).toEpochSecond() : null;
	}

	public static LocalDateTime fromStringToLocalDateTime(String date) {
		ZonedDateTime zdt = LocalDateTime.parse(date, DATE_FORMATTER).atZone(ZONEID_UTC);
		return zdt.toLocalDateTime();
	}

	public static String fromLocalDateTimeToString(LocalDateTime date) {
		return date.format(DATE_FORMATTER);
	}

	public static String fromEpochToString(Long epochDate) {
		return Conversion.fromLocalDateTimeToString(Conversion.fromEpochToLocalDateTime(epochDate));
	}

}
