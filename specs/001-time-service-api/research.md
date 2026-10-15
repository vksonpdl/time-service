# Research: Time Service REST API

## Technical Decisions & Rationale

### 1. Java Time API (`java.time.Instant` & `java.time.ZoneId`)
- **Decision**: Use `java.time.ZonedDateTime` or `java.time.Instant` combined with `ZoneId.systemDefault()` to capture server time and timezone.
- **Rationale**: Modern Java 17 time API provides robust, thread-safe, and ISO-8601 compliant datetime formatting out of the box.

### 2. Immutable DTOs with Lombok
- **Decision**: Use Lombok `@Value` or `@Builder` with `@Getter` and `final` fields for `TimeResponse` and `ErrorResponse`.
- **Rationale**: Ensures immutability of data transfer objects while minimizing boilerplate code as mandated by project constitution.

### 3. Global Exception Handling for Error Contracts
- **Decision**: Implement a `@ControllerAdvice` (`GlobalExceptionHandler`) catching `Exception` and custom exceptions to return JSON error responses containing `errorCode` and `errorMessage`.
- **Rationale**: Ensures 100% compliance with constitutional error handling rules across all failure pathways.

### 4. Controller Unit Testing with MockMvc & JUnit 5
- **Decision**: Use `@WebMvcTest` with JUnit 5 and `MockMvc` for controller testing, and pure unit tests for `TimeService`.
- **Rationale**: Validates HTTP status, JSON structure, and error handling without requiring a full server startup for unit tests.
