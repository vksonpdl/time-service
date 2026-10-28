# Feature Specification: UTC to IST Time Conversion API

**Feature Branch**: `002-utc-ist-conversion`

**Created**: 2026-10-28

**Status**: Draft

**Input**: User description: "Create a new REST endpoint for the time-conversion-api that converts UTC to IST."

## Clarifications

### Session 2026-10-28
- Q: How should the UTC timestamp be passed in the request for the time conversion API? → A: HTTP POST with a JSON request body containing the timestamp field.
- Q: What should be the JSON property name for the UTC timestamp in the request body? → A: utcTimestamp (e.g., {"utcTimestamp": "2026-10-28T12:00:00Z"}).
- Q: What fields should be included in the successful JSON response for the UTC to IST conversion API? → A: status, sourceTime, convertedTime, targetTimezone.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Convert UTC Time to IST (Priority: P1)

As a client application, I want to request a time conversion from UTC to Indian Standard Time (IST) via an HTTP POST endpoint with a JSON request body (`utcTimestamp`) so that I can accurately display or process times in the IST timezone (UTC+5:30).

**Why this priority**: Core functionality requested by the user; delivers the primary value of the time conversion service.

**Independent Test**: Can be fully tested by sending an HTTP POST request with a valid UTC timestamp in the `utcTimestamp` JSON body field (or requesting current UTC conversion when body is empty/omitted) and verifying that the response returns `status`, `sourceTime`, `convertedTime`, and `targetTimezone` in JSON format.

**Acceptance Scenarios**:

1. **Given** the time conversion service is running, **When** a client sends an HTTP POST request with `utcTimestamp` in the JSON request body, **Then** the response status is 200 OK and body contains JSON with `status`, `sourceTime`, `convertedTime`, and `targetTimezone`.
2. **Given** the time conversion service is running, **When** a client sends an HTTP POST request with an empty or omitted request body/timestamp, **Then** the system converts the current UTC time to IST and returns the result with status 200 OK.

---

### User Story 2 - Standardized Error Handling for Invalid Inputs (Priority: P2)

As a client application, I want invalid timestamp formats or malformed JSON requests to return a standardized JSON error response containing an error code and error message so that I can handle validation failures gracefully.

**Why this priority**: Ensures compliance with project constitution and robust contract handling for client error states.

**Independent Test**: Can be tested by sending malformed or invalid ISO-8601 UTC timestamp strings in the `utcTimestamp` JSON request body field to the conversion endpoint and verifying that responses contain `errorCode` and `errorMessage` in JSON format.

**Acceptance Scenarios**:

1. **Given** an invalid or malformed UTC timestamp is provided in `utcTimestamp`, **When** a client requests conversion, **Then** the system returns a standardized JSON error response with `errorCode` and `errorMessage` (400 Bad Request).

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: System MUST expose an HTTP POST endpoint for converting UTC time to IST.
- **FR-002**: System MUST accept a JSON request body containing the `utcTimestamp` property (or default to current UTC time if omitted/empty).
- **FR-003**: System MUST correctly calculate IST (`Asia/Kolkata`, UTC+5:30) accounting for timezone rules.
- **FR-004**: Successful responses MUST include `status`, `sourceTime`, `convertedTime`, and `targetTimezone`.
- **FR-005**: All REST API responses (success and failure) MUST be formatted as standard JSON.
- **FR-006**: All failure use cases (such as invalid timestamp formatting) MUST return a standardized JSON error response containing `errorCode` and `errorMessage`.

### Key Entities *(include if feature involves data)*

- **TimeConversionRequest**: Represents the request payload containing the optional `utcTimestamp` string property.
- **TimeConversionResponse**: Represents the successful conversion payload containing `status`, `sourceTime`, `convertedTime`, and `targetTimezone`.
- **ErrorResponse**: Represents failure payload containing `errorCode` and `errorMessage`.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: Clients successfully receive accurate UTC to IST conversions in JSON format via HTTP POST requests.
- **SC-002**: IST offset calculation (+05:30) is 100% accurate for any given valid UTC timestamp.
- **SC-003**: Timestamp values strictly adhere to ISO-8601 formatting standards.
- **SC-004**: 100% of API error responses contain `errorCode` and `errorMessage` fields.

## Assumptions

- IST is fixed at UTC+5:30 as India does not observe Daylight Saving Time.
- ISO-8601 string format is expected for input and output timestamps.
