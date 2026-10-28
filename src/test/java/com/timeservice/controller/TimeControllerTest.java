package com.timeservice.controller;

import com.timeservice.model.TimeConversionResponse;
import com.timeservice.model.TimeResponse;
import com.timeservice.service.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimeController.class)
class TimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeService timeService;

    @Test
    void testGetTimeEndpoint() throws Exception {
        TimeResponse mockResponse = TimeResponse.builder()
                .status("SUCCESS")
                .timestamp("2026-10-15T14:30:00Z")
                .timezone("UTC")
                .build();

        when(timeService.getCurrentTime()).thenReturn(mockResponse);

        mockMvc.perform(get("/api/v1/time")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.timestamp").value("2026-10-15T14:30:00Z"))
                .andExpect(jsonPath("$.timezone").value("UTC"));
    }

    @Test
    void testConvertUtcToIstEndpoint() throws Exception {
        TimeConversionResponse mockResponse = TimeConversionResponse.builder()
                .status("SUCCESS")
                .sourceTime("2026-10-28T12:00:00Z")
                .convertedTime("2026-10-28T17:30:00+05:30")
                .targetTimezone("Asia/Kolkata")
                .build();

        when(timeService.convertUtcToIst(anyString())).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/convert/utc-to-ist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"utcTimestamp\": \"2026-10-28T12:00:00Z\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.sourceTime").value("2026-10-28T12:00:00Z"))
                .andExpect(jsonPath("$.convertedTime").value("2026-10-28T17:30:00+05:30"))
                .andExpect(jsonPath("$.targetTimezone").value("Asia/Kolkata"));
    }
}
