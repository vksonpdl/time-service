# Data Model: UTC to IST Time Conversion API

**Branch**: `002-utc-ist-conversion` | **Date**: 2026-10-28

## Entities

### 1. TimeConversionRequest
Represents the incoming JSON request payload for time conversion.
- **Fields**:
  - `utcTimestamp` (String, optional): ISO-8601 formatted UTC timestamp (e.g., `2026-10-28T12:00:00Z`). If omitted or blank, current UTC time is used.

### 2. TimeConversionResponse
Represents the successful JSON response payload containing conversion results.
- **Fields**:
  - `status` (String): Status indicator (e.g., `SUCCESS`).
  - `sourceTime` (String): ISO-8601 formatted timestamp of the source UTC time.
  - `convertedTime` (String): ISO-8601 formatted timestamp of the converted IST time (`Asia/Kolkata`).
  - `targetTimezone` (String): Target timezone identifier (`Asia/Kolkata`).

### 3. ErrorResponse
Represents the failure payload (from project constitution).
- **Fields**:
  - `errorCode` (String): Machine-readable error code (e.g., `INVALID_TIMESTAMP`).
  - `errorMessage` (String): Human-readable error description.
