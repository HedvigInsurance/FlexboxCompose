#!/bin/bash

set -e

echo -e "🤖 Checking the correct state of our software \n${NC}"
./scripts/start_emulator.sh

echo -e "🤖 Evaluating our app module build, lint and tests execution:${NC}"
./gradlew executeScreenshotTests

echo -e "🤖 CI tasks execution finished properly"