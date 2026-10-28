# Implementation Plan: UTC to IST Time Conversion API

**Branch**: `002-utc-ist-conversion` | **Date**: 2026-10-28 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/002-utc-ist-conversion/spec.md`

## Summary

Implement a new REST POST endpoint (`/api/v1/convert/utc-to-ist`) that accepts an optional UTC timestamp in a JSON request body (`utcTimestamp`) and converts it to Indian Standard Time (IST, `Asia/Kolkata`, UTC+5:30), returning `status`, `sourceTime`, `convertedTime`, and `targetTimezone`. If omitted or empty, it defaults to the current UTC time. Malformed timestamps return a standardized JSON error response with `errorCode` and `errorMessage`.

## Technical Context

**Language/Version**: Java 17

**Primary Dependencies**: Spring Boot, Project Lombok, Jackson, JUnit 5, Mockito

**Storage**: N/A (Stateless time conversion)

**Testing**: JUnit 5, Spring Boot Test, Mockito

**Target Platform**: Java Virtual Machine (JVM 17), Linux/Windows server

**Project Type**: Web Service (Spring Boot REST API)

**Performance Goals**: <50ms response latency for time conversion operations

**Constraints**: Strict adherence to ISO-8601 formatting, standard JSON error response contract (`errorCode`, `errorMessage`)

**Scale/Scope**: Single API endpoint with request validation and timezone conversion logic

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- **Java 17 & Spring Boot Stack**: YES. Built with Java 17 and Spring Boot.
- **Maven & Lombok Boilerplate Reduction**: YES. Maven build tool and Lombok `@Data`, `@Builder`, `@RequiredArgsConstructor` used.
- **JUnit 5 Testing (NON-NEGOTIABLE)**: YES. Unit and integration tests will use JUnit 5.
- **Standard JSON API Responses**: YES. Request and response payloads are standard JSON.
- **Standardized Error Handling**: YES. Error responses contain `errorCode` and `errorMessage`.

## Project Structure

### Documentation (this feature)

```text
specs/002-utc-ist-conversion/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command)
```

### Source Code (repository root)

```text
src/
├── main/java/com/timeservice/
│   ├── controller/TimeController.java
│   ├── service/TimeService.java
│   ├── model/TimeConversionRequest.java
│   ├── model/TimeConversionResponse.java
│   └── exception/GlobalExceptionHandler.java
└── test/java/com/timeservice/
    ├── controller/TimeControllerTest.java
    └── service/TimeServiceTest.java
```

**Structure Decision**: Extending the existing single-project Spring Boot application structure in `src/main/java/com/timeservice/`.
