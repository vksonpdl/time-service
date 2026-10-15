# Tasks: Time Service REST API

**Input**: Design documents from `/specs/001-time-service-api/`

**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2)
- Include exact file paths in descriptions

---

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [x] T001 Create Maven project structure (`pom.xml`, `src/main/java`, `src/test/java`) per implementation plan
- [x] T002 Configure Java 17, Spring Boot parent, and Maven dependencies (Spring Web, Lombok, JUnit 5, Spring Boot Test) in `pom.xml`
- [x] T003 [P] Configure application properties in `src/main/resources/application.yml`

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

- [x] T004 [P] Create `ErrorResponse` immutable DTO with `errorCode` and `errorMessage` fields using Lombok in `src/main/java/com/timeservice/model/ErrorResponse.java`
- [x] T005 [P] Create `GlobalExceptionHandler` using `@ControllerAdvice` to handle exceptions and return standardized `ErrorResponse` JSON with appropriate error codes in `src/main/java/com/timeservice/exception/GlobalExceptionHandler.java`

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Retrieve Current Server Time (Priority: P1) 🎯 MVP

**Goal**: Expose HTTP GET `/api/v1/time` returning server time, timezone, and status in JSON.

**Independent Test**: Send GET `/api/v1/time`, verify 200 OK and JSON response containing `status`, `timestamp` (ISO-8601), and `timezone`.

### Tests for User Story 1 (JUnit 5 / MockMvc)

- [x] T006 [P] [US1] Create unit tests for `TimeService` in `src/test/java/com/timeservice/service/TimeServiceTest.java`
- [x] T007 [P] [US1] Create integration/controller tests using MockMvc for `TimeController` in `src/test/java/com/timeservice/controller/TimeControllerTest.java`

### Implementation for User Story 1

- [x] T008 [P] [US1] Create `TimeResponse` immutable DTO with `status`, `timestamp`, and `timezone` using Lombok in `src/main/java/com/timeservice/model/TimeResponse.java`
- [x] T009 [US1] Implement `TimeService` business logic to fetch system time and server `ZoneId` in `src/main/java/com/timeservice/service/TimeService.java`
- [x] T010 [US1] Implement `TimeController` exposing HTTP GET `/api/v1/time` delegating to `TimeService` in `src/main/java/com/timeservice/controller/TimeController.java`
- [x] T011 [US1] Create main Spring Boot application entry point `TimeServiceApplication.java` in `src/main/java/com/timeservice/TimeServiceApplication.java`

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently (MVP)

---

## Phase 4: User Story 2 - Standardized Error Handling (Priority: P2)

**Goal**: Ensure all failure use cases return standardized JSON error response with `errorCode` and `errorMessage`.

**Independent Test**: Trigger error condition or exception, verify response contains `errorCode` and `errorMessage` in JSON format.

### Tests for User Story 2

- [x] T012 [P] [US2] Add error contract test in `src/test/java/com/timeservice/controller/TimeControllerErrorTest.java` verifying `errorCode` and `errorMessage` on failure.

### Implementation for User Story 2

- [x] T013 [US2] Verify and refine global exception handlers to ensure 100% of failure paths return `errorCode` and `errorMessage` in JSON format.

**Checkpoint**: All user stories should now be independently functional

---

## Phase 5: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [x] T014 [P] Add documentation updates in docs/ or README.md
- [x] T015 Run full test suite (`mvn clean test`) and execute quickstart.md validation steps

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3+)**: All depend on Foundational phase completion
- **Polish (Final Phase)**: Depends on all user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 2 (P2)**: Can start after Foundational (Phase 2) - Integrates with US1 error cases

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational
3. Complete Phase 3: User Story 1
4. **STOP and VALIDATE**: Test User Story 1 independently

### Incremental Delivery

1. Complete Setup + Foundational
2. Add User Story 1 → Test independently (MVP!)
3. Add User Story 2 → Test error handling contract
4. Run full test suite and quickstart validation
