package com.tracker.pcr.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

@Service
public class MarketStatusService {

    public boolean isMarketOpen() {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        DayOfWeek day = now.getDayOfWeek();
        LocalTime time = now.toLocalTime();

        boolean isWeekday = day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;

        LocalTime open = LocalTime.of(9, 15);
        LocalTime close = LocalTime.of(15, 30);

        return isWeekday && time.isAfter(open) && time.isBefore(close);
    }
}
