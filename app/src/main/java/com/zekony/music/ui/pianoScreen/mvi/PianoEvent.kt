package com.zekony.music.ui.pianoScreen.mvi

sealed interface PianoEvent {
    class PlaySound(val code: String) : PianoEvent
    class OnAmplificationChange(val value: Int) : PianoEvent
    class StopSound(val code: String) : PianoEvent
    object InitializeState : PianoEvent
    class EndAnimation(val code: String) : PianoEvent
}
