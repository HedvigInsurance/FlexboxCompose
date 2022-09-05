#!/bin/bash

if $ANDROID_HOME/cmdline-tools/latest/bin/avdmanager list avd | grep -q Pixel_5_API_32; then
    echo "There is an existing an emulator to run screenshot tests"
    exit 0;
fi

echo "Creating a brand new SDCard..."
rm -rf sdcard.img
$ANDROID_HOME/emulator/mksdcard -l e 1G sdcard.img
echo "SDCard created!"

echo "Downloading the image to create the emulator..."
echo no | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "system-images;android-32;google_apis;arm64-v8a"
echo "Image downloaded!"

echo "Creating the emulator to run screenshot tests..."
echo no | $ANDROID_HOME/cmdline-tools/latest/bin/avdmanager create avd -n Pixel_5_API_32 -k "system-images;android-32;google_apis;arm64-v8a" --force --sdcard sdcard.img
echo "Emulator created!"

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cp $DIR/config.ini ~/.android/avd/Pixel_5_API_32.avd/config.ini
cp sdcard.img ~/.android/avd/Pixel_5_API_32.avd/sdcard.img
cp sdcard.img.qcow2 ~/.android/avd/Pixel_5_API_32.avd/sdcard.img.qcow2
