package com.zekony.music.signalLogic

data class TuneThreadState(
    var isRunning: Boolean = false,
    var sampleRate: Int = 88100,
    var tuneFreq: Double = 0.0,
    var amplitude: Int = 10000
)