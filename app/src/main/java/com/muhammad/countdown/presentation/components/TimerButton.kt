package com.muhammad.countdown.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.muhammad.countdown.R
import com.muhammad.countdown.domain.Timer
import com.muhammad.countdown.domain.TimerState
import java.util.Locale

@Composable
fun TimerButton(onClick :() -> Unit, timer : Timer) {
    Button(onClick = {
        onClick()
    }, modifier = Modifier.padding(16.dp).height(56.dp).fillMaxWidth()) {
        val text = when(timer.state){
            TimerState.IDLE -> stringResource(R.string.start_timer)
            TimerState.RUNNING -> stringResource(R.string.stop_timer)
            TimerState.FINISHED -> stringResource(R.string.restart_timer)
        }
        Text(text = text.uppercase(Locale.ROOT))
    }
}