# Quickstart Validation Guide: UTC to IST Time Conversion API

**Branch**: `002-utc-ist-conversion` | **Date**: 2026-10-28

## Prerequisites
- Java 17 installed and configured.
- Apache Maven installed.

## Setup & Build
1. Build the application using Maven:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Validation Scenarios

### 1. Convert Specific UTC Timestamp to IST
- **Request**:
  ```bash
  curl -X POST http://localhost:8080/api/v1/convert/utc-to-ist \
       -H "Content-Type: application/json" \
       -d '{"utcTimestamp": "2026-10-28T12:00:00Z"}'
  ```
- **Expected Response (200 OK)**:
  ```json
  {
    "status": "SUCCESS",
    "sourceTime": "2026-10-28T12:00:00Z",
    "convertedTime": "2026-10-28T17:30:00+05:30",
    "targetTimezone": "Asia/Kolkata"
  }
  ```

### 2. Convert Current UTC Time (Empty Request Body)
- **Request**:
  ```bash
  curl -X POST http://localhost:8080/api/v1/convert/utc-to-ist \
       -H "Content-Type: application/json" \
       -d '{}'
  ```
- **Expected Response (200 OK)**:
  ```json
  {
    "status": "SUCCESS",
    "sourceTime": "<current-utc-timestamp>",
    "convertedTime": "<current-ist-timestamp>",
    "targetTimezone": "Asia/Kolkata"
  }
  ```

### 3. Invalid Timestamp Error Handling
- **Request**:
  ```bash
  curl -X POST http://localhost:8080/api/v1/convert/utc-to-ist \
       -H "Content-Type: application/json" \
       -d '{"utcTimestamp": "invalid-timestamp"}'
  ```
- **Expected Response (400 Bad Request)**:
  ```json
  {
    "errorCode": "INVALID_TIMESTAMP",
    "errorMessage": "Invalid ISO-8601 timestamp format provided."
  }
  ```

## Running Tests
Execute the JUnit 5 test suite:
```bash
mvn test
```
