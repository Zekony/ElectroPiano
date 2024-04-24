package com.zekony.music.ui.pianoScreen

import androidx.compose.runtime.Composable
import com.zekony.music.ui.pianoScreen.mvi.PianoViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PianoEntry() {
    val viewModel: PianoViewModel = koinViewModel()
    val state = viewModel.collectAsState().value

    PianoScreen(state = state, onEvent = viewModel::dispatch)
}
