package com.code9.tenniscourt.util;

import java.time.LocalDateTime;

public class DateHelper {

    public static boolean isOverlapping(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public static boolean isNotBetween(LocalDateTime givenTime, LocalDateTime startTime, LocalDateTime endTime) {
        return givenTime.isBefore(startTime) || givenTime.isAfter(endTime);
    }

}
