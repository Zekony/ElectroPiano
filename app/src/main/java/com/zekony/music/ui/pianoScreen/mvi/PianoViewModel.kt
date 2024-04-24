package com.zekony.music.ui.pianoScreen.mvi

import android.util.Log
import com.zekony.music.common.util.getCodesFrequency
import com.zekony.music.common.util.getKeysCodes
import com.zekony.music.signalLogic.TuneThread
import com.zekony.music.ui.MviViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class PianoViewModel : MviViewModel<PianoState, Any, PianoEvent>(
    initialState = PianoState()
) {

    init {
        dispatch(PianoEvent.InitializeState)
    }

    private var tuneThread: TuneThread? = null

    override fun dispatch(event: PianoEvent) {
        Log.d("Jeky", "event $event")
        when (event) {
            is PianoEvent.PlaySound -> playLongSound(event.code)
            is PianoEvent.OnAmplificationChange -> onAmplificationChange(event.value)
            is PianoEvent.StopSound -> stopSound(event.code)
            PianoEvent.InitializeState -> initializeState()
            is PianoEvent.EndAnimation -> endAnimation(event.code)
        }
    }

    private fun initializeState() = intent {
        val listOfCodes = getKeysCodes()
        reduce {
            state.copy(
                whiteCodes = listOfCodes,
            )
        }
    }

    private fun onAmplificationChange(value: Int) = intent {
        reduce {
            state.copy(
                amplification = value
            )
        }
    }

    private fun playLongSound(code: String) = intent {
        if (tuneThread == null) {
            tuneThread = TuneThread()
            tuneThread?.setThreadTuneFreq(getCodesFrequency(code))
            tuneThread?.setThreadTuneAmpl(state.amplification)
            tuneThread?.start()
            reduce {
                state.copy(
                    animatedCodeButtons = state.animatedCodeButtons.toMutableSet()
                        .apply { this.add(code) }
                )
            }
        }
    }

    private fun endAnimation(code: String) = intent {
            val codeIndex = state.animatedCodeButtons.indexOf(code)
            reduce {
                state.copy(
                    animatedCodeButtons = state.animatedCodeButtons.toMutableSet()
                        .filter { state.animatedCodeButtons.indexOf(it) > codeIndex }.toSet()
                )
            }

    }

    private fun stopSound(code: String) = intent {
        if (tuneThread != null) {
            tuneThread?.stopTune()
            tuneThread = null
            delay(200)
            dispatch(PianoEvent.EndAnimation(code))
        }
    }
}


