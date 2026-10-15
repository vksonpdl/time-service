package com.timeservice.service;

import com.timeservice.model.TimeResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    public TimeResponse getCurrentTime() {
        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        
        return TimeResponse.builder()
                .status("SUCCESS")
                .timestamp(DateTimeFormatter.ISO_INSTANT.format(now))
                .timezone(zoneId.getId())
                .build();
    }
}
