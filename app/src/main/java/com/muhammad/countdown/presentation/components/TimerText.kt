package com.muhammad.countdown.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FlashingTimerText(timerText: String) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    TimerText(modifier = Modifier.alpha(alpha), timerText = timerText)
}

@Composable
fun TimerText(modifier: Modifier = Modifier, timerText: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        style = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center),
        text = timerText
    )
}
@Preview
@Composable
fun TimerTextPreview() {
    TimerText(timerText = "00:21")
}

@Preview
@Composable
fun FlashingTimerTextPreview() {
    FlashingTimerText(timerText = "00:00")
}