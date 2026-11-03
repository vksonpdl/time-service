# SpecKit Workflow: Time Conversion API

This document outlines the step-by-step process of using GitHub Spec Kit to design and implement a new API endpoint.

## 1. Specify
**Command:** `/speckit.specify`

*   **Input:** "Create a new REST endpoint for the time-conversion-api that converts UTC to IST."
*   **Action:** The AI reads the context, negotiates requirements if needed, and generates the initial specification.
*   **Output:** Generates a `spec.md` file inside the `.specify/features/time-conversion-api/` directory.

## 2. Clarify
**Command:** `/speckit.clarify`

*   **Input:** "What is the request structure for this new API?"
*   **Action:** The AI refines the specification based on the follow-up question.
*   **Output:** Updates the existing `spec.md` file.

## 3. Plan
**Command:** `/speckit.plan`

*   **Action:** The AI automatically reads the finalized `spec.md` and generates an architectural plan. This plan outlines the exact steps required, including:
    *   Existing files to modify (e.g., adding the route to `TimeConversionController.java`).
    *   New files to create (e.g., new DTOs for the request/response).
    *   Required dependencies or utility classes for the UTC to IST conversion.
*   **Implementation Plan Path:** `specs/002-utc-ist-conversion/plan.md`

**Generated Artifacts:**
*   `specs/002-utc-ist-conversion/plan.md`
*   `specs/002-utc-ist-conversion/research.md`
*   `specs/002-utc-ist-conversion/data-model.md`
*   `specs/002-utc-ist-conversion/contracts/time-conversion-api.json`
*   `specs/002-utc-ist-conversion/quickstart.md`

## 4. Tasks
**Command:** `/speckit.tasks`

*   **Action:** Breaks down the implementation plan into actionable items.
*   **Output:** Creates a `tasks.md` file.

## 5. Implement
**Command:** `/speckit.implement`

*   **Action:** Executes the tasks outlined in the previous step.
*   **Output:** Generates the actual application source code and corresponding JUnit tests.