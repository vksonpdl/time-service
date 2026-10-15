package com.timeservice.service;

import com.timeservice.model.TimeResponse;
import org.junit.jupiter.api.Test;

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
}
