#!/bin/bash
# sdd.sh - Hybrid Wrapper Engine for Spec Kit

set -e # Exit immediately on error

SPEC_DIR=".specify"
FEATURE_JSON="$SPEC_DIR/feature.json"

function print_usage() {
  echo "Usage:"
  echo "  ./sdd.sh switch <feature-name>  - Scaffold folders and update feature.json pointer"
  echo "  ./sdd.sh check-plan             - Gatekeeper: Ensure spec.md exists before planning"
  echo "  ./sdd.sh check-implement        - Gatekeeper: Ensure tasks.md exists before coding"
}

function switch_feature() {
  FEATURE_NAME=$1
  if [ -z "$FEATURE_NAME" ]; then
    echo " Error: Feature name required. Usage: make spec-switch feat=<name>"
    exit 1
  fi

  # 1. Enforce Git Hygiene (Optional but recommended to prevent accidental overwrites)
  if ! git diff-index --quiet HEAD --; then
    echo "  Warning: Working tree is dirty. Ensure you aren't overwriting uncommitted work."
  fi

  # 2. Scaffold Feature Directory
  FEATURE_PATH="$SPEC_DIR/features/$FEATURE_NAME"
  mkdir -p "$FEATURE_PATH"

  # 3. Update the active JSON pointer (Automates the manual context-switch)
  cat > "$FEATURE_JSON" <<EOF
{
  "activeFeature": "FEATURENAME","featureDirectory":"FEATURE_PATH"
}
EOF

  echo " Spec Kit is now locked to feature: '$FEATURE_NAME'"
  echo " Next Step: Open your AI chat and run '/speckit.specify' (if new) or '/speckit.plan' (if returning)."
}

function check_file_exists() {
  FILE_PATH=$1
  ERROR_MSG=$2
  if [ ! -f "$FILE_PATH" ]; then
    echo " SDD Pipeline Blocked: $ERROR_MSG"
    exit 1
  fi
}

function enforce_pipeline() {
  STAGE=$1

  if [ ! -f "$FEATURE_JSON" ]; then
    echo " Error: No active feature. Run 'make spec-switch feat=<name>' first."
    exit 1
  fi

  # Extract the active directory safely
  ACTIVE_DIR=$(grep '"featureDirectory"' "$FEATURE_JSON" | awk -F'"' '{print $4}')

  if [ "$STAGE" == "plan" ]; then
    check_file_exists "$ACTIVE_DIR/spec.md" "Missing spec.md. You must run /speckit.specify (and /speckit.clarify) first."
    echo " Gate Passed: spec.md found. You may proceed with '/speckit.plan' in your IDE."

  elif [ "$STAGE" == "implement" ]; then
    check_file_exists "$ACTIVE_DIR/spec.md" "Missing spec.md."
    check_file_exists "$ACTIVE_DIR/plan.md" "Missing plan.md. You must run /speckit.plan first."
    check_file_exists "$ACTIVE_DIR/tasks.md" "Missing tasks.md. You must run /speckit.tasks first."
    echo " Gate Passed: All specifications locked. You may proceed with '/speckit.implement' in your IDE."
  fi
}

COMMAND=$1
case $COMMAND in
  switch)
    switch_feature "$2"
    ;;
  check-plan)
    enforce_pipeline "plan"
    ;;
  check-implement)
    enforce_pipeline "implement"
    ;;
  *)
    print_usage
    ;;
esac

