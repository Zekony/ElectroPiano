package com.zekony.music.ui.util

import android.graphics.Paint
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zekony.music.R
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun CustomCircularSlider(
    modifier: Modifier = Modifier,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    secondaryColor: Color = MaterialTheme.colorScheme.secondary,
    currentValue: Int,
    minValue: Int = 0,
    maxValue: Int = 100,
    circularRadius: Float,
    onValueChange: (Int) -> Unit,
    addLines: Boolean = false
) {
    Box(
        modifier = Modifier.then(modifier)
    ) {
        CanvasCircularSlider(
            primaryColor,
            secondaryColor,
            currentValue,
            minValue,
            maxValue,
            circularRadius,
            onValueChange,
            addLines,
        )
    }
}

@Composable
private fun CanvasCircularSlider(
    primaryColor: Color,
    secondaryColor: Color,
    currentValue: Int,
    minValue: Int,
    maxValue: Int,
    circularRadius: Float,
    onValueChange: (Int) -> Unit,
    addLines: Boolean
) {
    val animatedNumber by animateIntAsState(
        targetValue = currentValue,
        animationSpec = tween(durationMillis = 500, easing = EaseIn),
        label = stringResource(R.string.animated_number)
    )
    val workingValue = (currentValue - minValue) / ((maxValue - minValue) / 100)
    val convertedMaxValue = (maxValue - minValue) / ((maxValue - minValue) / 100)

    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    var positionValue by remember {
        mutableIntStateOf(workingValue)
    }
    var oldPositionValue by remember {
        mutableIntStateOf(workingValue)
    }
    var changeAngle by remember {
        mutableFloatStateOf(0f)
    }
    var dragStartedAngle by remember {
        mutableFloatStateOf(0f)
    }
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = { offset ->
                        dragStartedAngle = -atan2(
                            x = circleCenter.y - offset.y,
                            y = circleCenter.x - offset.x
                        ) * (180f / PI).toFloat()
                        dragStartedAngle = (dragStartedAngle + 180f).mod(360f)
                    },
                    onDrag = { change, _ ->
                        var touchAngle = -atan2(
                            x = circleCenter.y - change.position.y,
                            y = circleCenter.x - change.position.x
                        ) * (180f / PI).toFloat()
                        touchAngle = (touchAngle + 180f).mod(360f)

                        val currentAngle = oldPositionValue * 360f / convertedMaxValue
                        changeAngle = touchAngle - currentAngle

                        val lowerThreshold = currentAngle - (360f / convertedMaxValue * 5)
                        val higherThreshold = currentAngle + (360f / convertedMaxValue * 5)

                        if (dragStartedAngle in lowerThreshold..higherThreshold) {
                            positionValue =
                                (oldPositionValue + (changeAngle / (360f / convertedMaxValue)).roundToInt())
                            onValueChange(positionValue * ((maxValue - minValue) / 100) + minValue)
                        }
                    },
                    onDragEnd = {
                        oldPositionValue = positionValue
                    }
                )
            },
    ) {
        val width = size.width
        val height = size.height
        val circleThickness = width / 20f
        circleCenter = Offset(x = width / 2f, y = height / 2f)

        drawCircle(
            brush = Brush.radialGradient(
                listOf(
                    primaryColor.copy(0.45f),
                    secondaryColor.copy(0.15f)
                )
            ),
            radius = circularRadius,
            center = circleCenter
        )

        drawCircle(
            style = Stroke(
                width = circleThickness
            ),
            color = secondaryColor,
            radius = circularRadius,
            center = circleCenter
        )

        filledSliderLine(
            positionValue,
            convertedMaxValue,
            circleThickness,
            circularRadius,
            primaryColor
        )

        if (addLines) {
            drawLines(
                circularRadius,
                circleThickness,
                positionValue,
                currentValue,
                circleCenter,
                primaryColor
            )
        }

        centerText(animatedNumber, circleCenter)
    }
}

fun DrawScope.centerText(animatedNumber: Int, circleCenter: Offset) {
    drawContext.canvas.nativeCanvas.apply {
        drawText(
            animatedNumber.toString(),
            circleCenter.x,
            circleCenter.y + 45.dp.toPx() / 3f,
            Paint().apply {
                textSize = 38.sp.toPx()
                textAlign = Paint.Align.CENTER
                color = Color.White.toArgb()
                isFakeBoldText = true
            }

        )
    }
}

private fun DrawScope.filledSliderLine(
    positionValue: Int,
    convertedMaxValue: Int,
    circleThickness: Float,
    circularRadius: Float,
    color: Color
) {
    drawArc(
        color = color,
        startAngle = 90f,
        sweepAngle = (360f / convertedMaxValue) * positionValue.toFloat(),
        style = Stroke(
            width = circleThickness,
            cap = StrokeCap.Round
        ),
        useCenter = false,
        size = Size(
            width = circularRadius * 2f,
            height = circularRadius * 2f
        ),
        topLeft = Offset(
            size.width / 2f - circularRadius,
            size.height / 2f - circularRadius,
        )
    )
}

fun DrawScope.drawLines(
    circularRadius: Float,
    circleThickness: Float,
    positionValue: Int,
    currentValue: Int,
    circleCenter: Offset,
    color: Color
) {
    val outerRadius = circularRadius + circleThickness / 2f
    val gap = 25f
    for (degreeValue in 0 until currentValue) {
        val angleInDegrees = degreeValue * 360f / currentValue
        val angleInRad = angleInDegrees * PI / 180f + PI / 2f

        val yGapAdjustment = cos(angleInDegrees * PI / 180f) * gap
        val xGapAdjustment = -sin(angleInDegrees * PI / 180f) * gap

        val start = Offset(
            x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
            y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment).toFloat()
        )
        val end = Offset(
            x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
            y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment + circleThickness).toFloat()
        )

        rotate(
            degrees = angleInDegrees,
            pivot = start
        ) {
            drawLine(
                color = if (degreeValue < positionValue) color else color.copy(0.3f),
                start = start,
                end = end,
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}
