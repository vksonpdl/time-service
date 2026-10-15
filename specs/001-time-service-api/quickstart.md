# Quickstart Validation Guide: Time Service REST API

**Feature**: Time Service REST API (`001-time-service-api`)

## Prerequisites
- Java 17 installed
- Apache Maven installed

## Build & Test
1. Run unit and integration tests (JUnit 5):
   ```bash
   mvn clean test
   ```
2. Verify all tests pass successfully without errors.

## Running the Application
1. Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
2. In a separate terminal, test the time endpoint using `curl`:
   ```bash
   curl -i http://localhost:8080/api/v1/time
   ```
3. Verify expected 200 OK JSON response:
   ```json
   {
     "status": "SUCCESS",
     "timestamp": "2026-10-15T14:30:00Z",
     "timezone": "UTC"
   }
   ```
