# Data Models: Time Service REST API

## 1. TimeResponse (Immutable DTO)
Represents the successful response payload returned by `/api/v1/time`.

- **Fields**:
  - `status` (String): Status indicator (e.g., `"SUCCESS"`).
  - `timestamp` (String): ISO-8601 formatted string representing current server time (e.g., `"2026-10-15T14:30:00Z"` or offset format).
  - `timezone` (String): Server timezone identifier (e.g., `"UTC"` or `"America/New_York"`).

- **Lombok annotations**: `@Value`, `@Builder` (ensuring immutability).

## 2. ErrorResponse (Immutable DTO)
Represents the standardized error response payload returned on failure.

- **Fields**:
  - `errorCode` (String): Machine-readable error code (e.g., `"INTERNAL_SERVER_ERROR"` or `"BAD_REQUEST"`).
  - `errorMessage` (String): Human-readable description of the failure.

- **Lombok annotations**: `@Value`, `@Builder`.
