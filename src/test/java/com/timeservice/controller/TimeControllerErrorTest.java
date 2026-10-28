package com.timeservice.controller;

import com.timeservice.service.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeParseException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimeController.class)
class TimeControllerErrorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeService timeService;

    @Test
    void testErrorHandlingReturnsErrorCodeAndMessage() throws Exception {
        when(timeService.getCurrentTime()).thenThrow(new RuntimeException("Server time failure"));

        mockMvc.perform(get("/api/v1/time")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorCode").value("INTERNAL_SERVER_ERROR"))
                .andExpect(jsonPath("$.errorMessage").value("Server time failure"));
    }

    @Test
    void testConvertUtcToIstInvalidTimestampReturnsBadRequest() throws Exception {
        when(timeService.convertUtcToIst(anyString())).thenThrow(new DateTimeParseException("Text cannot be parsed", "invalid-timestamp", 0));

        mockMvc.perform(post("/api/v1/convert/utc-to-ist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"utcTimestamp\": \"invalid-timestamp\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INVALID_TIMESTAMP"))
                .andExpect(jsonPath("$.errorMessage").exists());
    }
}
