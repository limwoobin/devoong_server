package com.drogbalog.server.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy.MM.dd";

    public static String toString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    public static String toString(LocalDateTime localDateTime) {
        return toString(localDateTime, DATE_TIME_PATTERN);
    }

    public static LocalDateTime toLocalDateTime(String dateTime, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime, format);
    }

    public static LocalDateTime toLocalDateTime(String dateTime) {
        return toLocalDateTime(dateTime, DATE_TIME_PATTERN);
    }

    public static LocalDateTime getEndOfToday() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        return start.plusDays(1);
    }
}
