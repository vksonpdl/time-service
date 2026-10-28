package com.timeservice.controller;

import com.timeservice.model.TimeConversionRequest;
import com.timeservice.model.TimeConversionResponse;
import com.timeservice.model.TimeResponse;
import com.timeservice.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TimeController {

    private final TimeService timeService;

    @GetMapping("/time")
    public ResponseEntity<TimeResponse> getTime() {
        TimeResponse response = timeService.getCurrentTime();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/convert/utc-to-ist")
    public ResponseEntity<TimeConversionResponse> convertUtcToIst(@RequestBody(required = false) TimeConversionRequest request) {
        String utcTimestamp = request != null ? request.getUtcTimestamp() : null;
        TimeConversionResponse response = timeService.convertUtcToIst(utcTimestamp);
        return ResponseEntity.ok(response);
    }
}
