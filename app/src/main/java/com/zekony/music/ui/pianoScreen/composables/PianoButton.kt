package com.zekony.music.ui.pianoScreen.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zekony.music.R
import com.zekony.music.ui.pianoScreen.mvi.PianoEvent
import com.zekony.music.ui.pianoScreen.mvi.PianoState


@Composable
fun PianoButton(code: String, state: PianoState, onEvent: (PianoEvent) -> Unit) {

    val color by animateColorAsState(
        targetValue = if (state.animatedCodeButtons.contains(code)) Color.Green else Color.White,
        animationSpec = tween(durationMillis = 150, easing = EaseInCirc),
        label = stringResource(R.string.animated_color)
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp),
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .height(150.dp)
            .width(30.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {}
            .pointerInput(true) {
                awaitEachGesture {
                    val down: PointerInputChange = awaitFirstDown(requireUnconsumed = false)
                    var pointerId = down.id

                    onEvent(PianoEvent.PlaySound(code))

                    while (true) {
                        val event: PointerEvent = awaitPointerEvent()

                        val anyPressed = event.changes.any {
                            it.pressed
                        }

                        if (anyPressed) {
                            val pointerInputChange =
                                event.changes.firstOrNull { it.id == pointerId }
                                    ?: event.changes.first()

                            pointerId = pointerInputChange.id
                        } else {
                            onEvent(PianoEvent.StopSound(code))
                            break
                        }
                    }
                }
            }
    ) {}
}
