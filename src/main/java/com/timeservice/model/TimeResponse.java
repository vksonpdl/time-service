package com.timeservice.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TimeResponse {
    String status;
    String timestamp;
    String timezone;
}
