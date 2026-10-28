.PHONY: spec-switch spec-check-plan spec-check-implement

# --- Spec Kit Hybrid Wrapper Commands ---

# Usage: make spec-switch feat=update-email-api
spec-switch:
	@chmod +x scripts/sdd.sh
	@./scripts/sdd.sh switch $(feat)

# Validates that the spec phase is complete before planning
spec-check-plan:
	@chmod +x scripts/sdd.sh
	@./scripts/sdd.sh check-plan

# Validates that the planning and task phases are complete before coding
spec-check-implement:
	@chmod +x scripts/sdd.sh
	@./scripts/sdd.sh check-implement

