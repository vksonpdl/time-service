# Research: UTC to IST Time Conversion API

**Branch**: `002-utc-ist-conversion` | **Date**: 2026-10-28

## Phase 0 Research & Technical Decisions

### 1. Java Time API (`java.time`) for UTC to IST Conversion
- **Decision**: Use `java.time.Instant`, `java.time.ZonedDateTime`, `java.time.ZoneId`, and `java.time.format.DateTimeFormatter`.
- **Rationale**: Java 17 provides robust, thread-safe, and immutable date-time classes in `java.time`. `ZoneId.of("Asia/Kolkata")` correctly handles IST (UTC+5:30) without DST complexity.
- **Alternatives considered**: `java.util.Date` and `java.util.Calendar` (deprecated/legacy; prone to timezone bugs).

### 2. Request Validation & Defaulting
- **Decision**: Accept `TimeConversionRequest` DTO with an optional `utcTimestamp` string. If null or blank, default to `Instant.now()`.
- **Rationale**: Meets both acceptance scenarios (explicit UTC timestamp conversion vs current UTC time conversion).
- **Alternatives considered**: Making timestamp mandatory (rejected per user story requiring default to current UTC time).

### 3. Error Handling & Parsing Failures
- **Decision**: Catch `DateTimeParseException` and other validation errors via `GlobalExceptionHandler` and return 400 Bad Request with `errorCode: "INVALID_TIMESTAMP"` and `errorMessage`.
- **Rationale**: Aligns with project constitution on standardized error handling (`errorCode` and `errorMessage`).
- **Alternatives considered**: Unhandled 500 errors (rejected; violates robust error contract standards).
