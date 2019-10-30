# GDG Venezia Showcase App

This is a simple showcase application for the GDG Venezia built using Kotlin Multiplatform.

<p align="center">
<img width="800px" src="https://github.com/GDG-Venezia/gdg-venezia-showcase-app/blob/develop/screens.png?raw=true">
</p>

The application has been developed for the [Kotlin/Everywhere Venice Event](https://www.meetup.com/it-IT/GDG-Venezia/events/265665209/) 

The slide of the event will be available soon.

There is also a backend for this application and guess what, it's written in Kotlin. The source code is available on [Github](https://github.com/GDG-Venezia/gdg-venezia-showcase-app-backend)

## Running the app:

### Android:

Open the root project in Android Studio or IntelliJ, and it will recognise the Android App configuration after a
successful Gradle sync. You can use that configuration to run, debug and profile the app.

To install the app you can use this gradle command:

```groovy
./gradlew installDebug
```

### iOs:

Build the kotlin common code for iOS

```groovy
./gradlew iOsBinaries
```
Update the pods

```bash
pod update
```
Open the workspace located at [ios/GDGVeneziaShowcase.xcworkspace](/ios/GDGVeneziaShowcase.xcworkspace) 
in XCode. 

## Future Work:

- Implement the detail screens

## License

    Copyright 2019 GDG Venezia

    Licensed under the Apache License, Version 2.0 (the "License"); you may 
    not use this file except in compliance with the License. You may obtain a 
    copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
    License for the specific language governing permissions and limitations 
    under the License.
