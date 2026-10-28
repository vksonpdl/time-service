package com.timeservice.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TimeConversionResponse {
    String status;
    String sourceTime;
    String convertedTime;
    String targetTimezone;
}
