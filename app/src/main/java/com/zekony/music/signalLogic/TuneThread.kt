package com.zekony.music.signalLogic

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.atan
import kotlin.math.sin

class TuneThread : Thread() {

    private val state = MutableStateFlow(TuneThreadState())

    override fun run() {
        super.run()
        state.value.isRunning = true

        val audioFormat = AudioFormat.ENCODING_PCM_16BIT
        val buffsize = AudioTrack.getMinBufferSize(
            state.value.sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            audioFormat
        )

        val audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(audioFormat)
                    .setSampleRate(state.value.sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                    .build()
            )
            .setBufferSizeInBytes(buffsize)
            .build()

        val samples = ShortArray(buffsize)
        var complexity = 0.0
        audioTrack.play()
        while (state.value.isRunning) {
            val fr = state.value.tuneFreq
            for (samplesIndex in 0 until buffsize) {
                samples[samplesIndex] = (state.value.amplitude * sin(complexity)).toInt().toShort()
                complexity += (8.0 * atan(1.0)) * fr / state.value.sampleRate
            }
            audioTrack.write(samples, 0, buffsize)
        }
        audioTrack.stop()
        audioTrack.release()
    }

    fun stopTune() {
        state.value.isRunning = false
        try {
            this.join()
            interrupt()
        } catch (_: InterruptedException) {

        }
    }

    fun setThreadTuneFreq(freq: Double) {
        state.value.tuneFreq = freq
    }

    fun setThreadTuneAmpl(ampl: Int) {
        state.value.amplitude = ampl
    }
}
