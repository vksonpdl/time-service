<!--
Sync Impact Report:
- Version change: [NEW] → 1.0.0
- Added Core Principles:
  1. I. Java 17 & Spring Boot Stack
  2. II. Maven & Lombok Boilerplate Reduction
  3. III. JUnit 5 Testing (NON-NEGOTIABLE)
  4. IV. Standard JSON API Responses
  5. V. Standardized Error Handling (errorCode & errorMessage)
- Added Sections: Technology Stack & Architectural Constraints, Development Workflow & Quality Gates, Governance.
- Follow-up TODOs: None.
-->

# Time Service Constitution

## Core Principles

### I. Java 17 & Spring Boot Stack
The project must be built using Java 17 and the latest Spring Boot version compatible with Java 17. All enterprise features, dependency injection, and web server configurations leverage Spring Boot and Java 17 idioms.

### II. Maven & Lombok Boilerplate Reduction
Maven is the designated build tool and dependency manager for all modules. Lombok annotations (`@Data`, `@Getter`, `@Setter`, `@Builder`, `@RequiredArgsConstructor`, etc.) must be used across domain models, DTOs, and services to reduce boilerplate code and maintain clean, readable classes.

### III. JUnit 5 Testing (NON-NEGOTIABLE)
All unit and integration testing must be implemented using JUnit 5 (alongside Mockito and Spring Boot Test utilities where appropriate). Comprehensive test coverage is required for business logic, controller endpoints, and failure cases.

### IV. Standard JSON API Responses
All REST API endpoints must consume and produce standard JSON formatted payloads. Headers, content negotiation, and serialization must adhere to standard RESTful conventions.

### V. Standardized Error Handling
All REST API failure use cases (client errors, server errors, validation failures) must return a standardized JSON error response containing at minimum an `errorCode` and an `errorMessage`.

## Technology Stack & Architectural Constraints

The Time Service application architecture strictly adheres to:
- **Language**: Java 17
- **Framework**: Latest Spring Boot compatible with Java 17
- **Build Tool**: Apache Maven
- **Boilerplate Reduction**: Project Lombok
- **Testing Framework**: JUnit 5
- **API Contract**: JSON request/response with mandated `errorCode` and `errorMessage` structure for all error states.

## Development Workflow & Quality Gates

- **Code Quality**: Code reviews must verify adherence to Java 17 conventions and Lombok usage.
- **Testing Gate**: All builds and pull requests must successfully execute all JUnit 5 test suites without failure.
- **Error Contract Verification**: API error handling logic must be explicitly tested for all failure paths to ensure `errorCode` and `errorMessage` are correctly populated.

## Governance

This constitution supersedes all other practices and informal agreements within the project. Amendments require documentation, team review, and an explicit version update. All pull requests and code reviews must verify compliance with these constitutional principles.

**Version**: 1.0.0 | **Ratified**: 2026-10-15 | **Last Amended**: 2026-10-15
