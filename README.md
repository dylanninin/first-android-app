KotlinDemo

#### Development

build from command line

```shell
# list all tasks
./gradlew tasks

# build debug apk
./gradlew assembleDebug

# apk path
ls -lh app/build/outputs/apk/debug
```

deploy to emulator

```shell
# ensure path in bash_profile/zshrc/...
# see https://stackoverflow.com/a/49511666/1448139
# e.g.:
export ANDROID_SDK=~/Library/Android/sdk
export PATH=${ANDROID_SDK}/emulator:${ANDROID_SDK}/tools:${ANDROID_SDK}/cmdline-tools/5.0/bin:${ANDROID_SDK}/platform-tools:$PATH

# list all emulators
emulator -list-avds

# start emulator
emulator -avd Pixel_5_API_30

# install use adb
adb install app/build/outputs/apk/debug/app-debug.apk
```


#### Reference
- [Build your app from the command line](https://developer.android.com/studio/build/building-cmdline)
- [Build Your First Android App in Kotlin](https://developer.android.com/codelabs/build-your-first-android-app-kotlin)