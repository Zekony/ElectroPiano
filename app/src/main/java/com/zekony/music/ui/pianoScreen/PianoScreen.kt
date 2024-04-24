package com.zekony.music.ui.pianoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zekony.music.R
import com.zekony.music.ui.pianoScreen.composables.PianoButton
import com.zekony.music.ui.pianoScreen.mvi.PianoEvent
import com.zekony.music.ui.pianoScreen.mvi.PianoState
import com.zekony.music.ui.util.CustomCircularSlider

@Composable
fun PianoScreen(state: PianoState, onEvent: (PianoEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(R.string.amplification),
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold
        )
        CustomCircularSlider(
            modifier = Modifier.size(200.dp),
            currentValue = state.amplification,
            minValue = 0,
            maxValue = 35000,
            circularRadius = 200f,
            onValueChange = { value ->
                onEvent(PianoEvent.OnAmplificationChange(value))
            }
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            items(state.whiteCodes) { code: String ->
                PianoButton(code = code, state = state, onEvent = onEvent)
            }
        }
    }
}
