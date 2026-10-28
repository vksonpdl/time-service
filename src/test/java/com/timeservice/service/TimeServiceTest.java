package com.timeservice.service;

import com.timeservice.model.TimeConversionResponse;
import com.timeservice.model.TimeResponse;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class TimeServiceTest {

    private final TimeService timeService = new TimeService();

    @Test
    void testGetCurrentTimeReturnsValidResponse() {
        TimeResponse response = timeService.getCurrentTime();

        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getTimestamp());
        assertNotNull(response.getTimezone());
    }

    @Test
    void testConvertUtcToIstValidTimestamp() {
        TimeConversionResponse response = timeService.convertUtcToIst("2026-10-28T12:00:00Z");

        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());
        assertEquals("2026-10-28T12:00:00Z", response.getSourceTime());
        assertEquals("2026-10-28T17:30:00+05:30", response.getConvertedTime());
        assertEquals("Asia/Kolkata", response.getTargetTimezone());
    }

    @Test
    void testConvertUtcToIstNullOrBlankDefaultsToCurrentTime() {
        TimeConversionResponse response = timeService.convertUtcToIst(null);

        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getSourceTime());
        assertNotNull(response.getConvertedTime());
        assertEquals("Asia/Kolkata", response.getTargetTimezone());
    }

    @Test
    void testConvertUtcToIstInvalidTimestampThrowsException() {
        assertThrows(DateTimeParseException.class, () -> {
            timeService.convertUtcToIst("invalid-timestamp");
        });
    }
}
