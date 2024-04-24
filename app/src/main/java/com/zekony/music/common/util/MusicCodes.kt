package com.zekony.music.common.util

fun getCodesFrequency(code: String): Double {
    return when (code) {
        "E3" -> 164.8
        "F3" -> 174.6
        "A3" -> 220.0
        "B3" -> 247.0
        "D4" -> 293.7
        "E4" -> 329.6
        "F4" -> 349.2
        "A4" -> 440.0
        "B4" -> 493.9
        "D5" -> 587.3
        "E5" -> 659.3
        "F5" -> 698.5
        "A5" -> 880.0
        "B5" -> 987.7
        "D6" -> 1175.0
        "E6" -> 1319.0
        "F6" -> 1397.0
        "A6" -> 1760.0
        "B6" -> 1976.0
        "D7" -> 2349.0
        "E7" -> 2637.0
        "F7" -> 2794.0
        "A7" -> 3520.0
        else -> 0.0
    }
}

fun getKeysCodes(): List<String> {
    return listOf(
        "E3", "F3", "A3", "B3",
        "D4", "E4", "F4", "A4", "B4",
        "D5", "E5", "F5", "A5", "B5",
        "D6", "E6", "F6", "A6", "B6",
        "D7", "E7", "F7", "A7",
        )
}
