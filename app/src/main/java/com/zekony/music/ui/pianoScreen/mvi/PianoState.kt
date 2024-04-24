package com.zekony.music.ui.pianoScreen.mvi

data class PianoState(
    val whiteCodes: List<String> = emptyList(),
    val amplification: Int = 10000,
    val animatedCodeButtons: Set<String> = emptySet(),
)
