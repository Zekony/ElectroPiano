# ElectroPiano
Small app that can play simple sounds.
I created this app because i wanted to create something similar to electric piano.

## Technologies used in this project

- Language: Kotlin
- UI: Jetpack Compose
- Threads
- Koin
- android.media

 ## Content

 In my app, users can click on different buttons and change amplification to control the sound buttons create.

 ## How it works
I have a list of many different freqencies, each button uses its own. Then a thread is started for each button, which lives as long as button is pressed. Inside this thread i use AudioTrack from android.media to create sound. On the screen there is a custom circular slider to change amplification of a sound which is used in a thread as a parameter. In the button modifier code pointerInput and awaitEachGesture funtions are used to recognize gestures and understand when sound should stop.
