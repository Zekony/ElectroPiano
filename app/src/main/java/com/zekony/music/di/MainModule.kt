package com.zekony.music.di

import com.zekony.music.ui.pianoScreen.mvi.PianoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object MainModule {
    val module = module {
        viewModelOf(::PianoViewModel)
    }
}