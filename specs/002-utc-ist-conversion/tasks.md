# Tasks: UTC to IST Time Conversion API

**Input**: Design documents from `/specs/002-utc-ist-conversion/`

**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2)
- Include exact file paths in descriptions

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [x] T001 Verify project structure and Maven dependencies for Java 17 and Spring Boot in pom.xml

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

- [x] T002 [P] Create TimeConversionRequest model in src/main/java/com/timeservice/model/TimeConversionRequest.java with optional `utcTimestamp` string property
- [x] T003 [P] Create TimeConversionResponse model in src/main/java/com/timeservice/model/TimeConversionResponse.java with `status`, `sourceTime`, `convertedTime`, and `targetTimezone` properties
- [x] T004 Configure global exception handler or validation handling in GlobalExceptionHandler.java for parsing and validation errors returning standard ErrorResponse (`errorCode`, `errorMessage`)

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Convert UTC Time to IST (Priority: P1) 🎯 MVP

**Goal**: Expose an HTTP POST endpoint at `/api/v1/convert/utc-to-ist` that converts a provided UTC timestamp (or current UTC time if omitted/empty) to IST (`Asia/Kolkata`, UTC+5:30) and returns the conversion result in standard JSON.

**Independent Test**: Can be fully tested by sending an HTTP POST request with a valid UTC timestamp in `utcTimestamp` (or empty body) and verifying 200 OK with `status`, `sourceTime`, `convertedTime`, and `targetTimezone`.

### Implementation for User Story 1

- [x] T005 [US1] Implement time conversion business logic in TimeService.java using `java.time.Instant`, `ZoneId.of("Asia/Kolkata")`, and `DateTimeFormatter` supporting explicit UTC input or current UTC fallback
- [x] T006 [US1] Implement HTTP POST endpoint `/api/v1/convert/utc-to-ist` in TimeController.java accepting `TimeConversionRequest` and returning `TimeConversionResponse`
- [x] T007 [P] [US1] Create unit tests for time conversion logic in TimeServiceTest.java under src/test/java/com/timeservice/service/TimeServiceTest.java
- [x] T008 [P] [US1] Create controller tests for POST `/api/v1/convert/utc-to-ist` in TimeControllerTest.java under src/test/java/com/timeservice/controller/TimeControllerTest.java

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently (MVP)

---

## Phase 4: User Story 2 - Standardized Error Handling for Invalid Inputs (Priority: P2)

**Goal**: Ensure invalid timestamp formats or malformed requests return a standardized JSON error response (`errorCode` and `errorMessage`) with 400 Bad Request status.

**Independent Test**: Can be tested by sending malformed ISO-8601 timestamps and verifying 400 Bad Request with `errorCode` and `errorMessage`.

### Implementation for User Story 2

- [x] T009 [US2] Implement exception handling for `DateTimeParseException` and invalid datetime payloads in GlobalExceptionHandler.java returning 400 Bad Request with `errorCode: "INVALID_TIMESTAMP"` and `errorMessage`
- [x] T010 [P] [US2] Add negative test cases for invalid timestamps in TimeControllerErrorTest.java under src/test/java/com/timeservice/controller/TimeControllerErrorTest.java

**Checkpoint**: At this point, User Stories 1 AND 2 should both work independently and robustly

---

## Phase 5: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [x] T011 Code cleanup and formatting verification across all new classes
- [x] T012 Run full Maven test suite (`mvn test`) to ensure 100% test passing
- [x] T013 Verify quickstart.md validation scenarios via curl or unit tests

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3+)**: All depend on Foundational phase completion
  - User Story 1 (P1) is the MVP
  - User Story 2 (P2) depends on foundational error handling / can build upon US1
- **Polish (Final Phase)**: Depends on all desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2)
- **User Story 2 (P2)**: Can start after Foundational (Phase 2) and integrates with TimeController / GlobalExceptionHandler

### Within Each User Story

- Models before services
- Services before endpoints
- Core implementation before testing/validation

### Parallel Opportunities

- T002 and T003 can run in parallel
- T007 and T008 can run in parallel
- T010 can run in parallel with polish tasks

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational (Models & Handlers)
3. Complete Phase 3: User Story 1 (Service & Controller endpoint)
4. **STOP and VALIDATE**: Test User Story 1 independently via `mvn test`

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test independently (MVP!)
3. Add User Story 2 → Test error handling & validation
4. Run Polish & Quickstart validation
