package com.timeservice.controller;

import com.timeservice.model.TimeResponse;
import com.timeservice.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
