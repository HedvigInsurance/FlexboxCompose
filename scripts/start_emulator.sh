#!/bin/bash

if ! "$ANDROID_HOME/"tools/android list avd | grep -q Pixel_5_API_32; then
    echo "No emulator for screenshot tests found, creating one..."
    ./scripts/create_emulator.sh
fi

if "$ANDROID_HOME"/platform-tools/adb devices -l | grep -q emulator; then
    echo "Emulator already running"
    exit 0
fi

echo "Starting emulator..."
echo "no" | "$ANDROID_HOME"/emulator/emulator "-avd" "Pixel_5_API_32" "-no-audio" "-no-boot-anim" &

./scripts/wait_for_emulator.sh

echo "Emulator ready, disabling animations!"

./scripts/disable_animations.sh

echo "Emulator started and ready to rock!"