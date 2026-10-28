package com.timeservice.service;

import com.timeservice.model.TimeConversionResponse;
import com.timeservice.model.TimeResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");

    public TimeResponse getCurrentTime() {
        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        
        return TimeResponse.builder()
                .status("SUCCESS")
                .timestamp(DateTimeFormatter.ISO_INSTANT.format(now))
                .timezone(zoneId.getId())
                .build();
    }

    public TimeConversionResponse convertUtcToIst(String utcTimestamp) {
        Instant sourceInstant;
        if (utcTimestamp == null || utcTimestamp.trim().isEmpty()) {
            sourceInstant = Instant.now();
        } else {
            sourceInstant = Instant.parse(utcTimestamp.trim());
        }

        ZonedDateTime istZonedDateTime = sourceInstant.atZone(IST_ZONE);

        return TimeConversionResponse.builder()
                .status("SUCCESS")
                .sourceTime(DateTimeFormatter.ISO_INSTANT.format(sourceInstant))
                .convertedTime(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(istZonedDateTime))
                .targetTimezone("Asia/Kolkata")
                .build();
    }
}
