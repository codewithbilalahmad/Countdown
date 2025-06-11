package com.muhammad.countdown.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muhammad.countdown.presentation.theme.orange

@Composable
fun DeterminateProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = orange,
    background: Color = MaterialTheme.colorScheme.onSurface.copy(0.2f),
    content: @Composable () -> Unit,
) {
    val drawStyle = remember { Stroke(width = 24.dp.value, cap = StrokeCap.Round) }
    val brushColor = remember { SolidColor(progressColor) }
    val brushBackground = remember { SolidColor(background) }
    val animatedCurrentProgress by animateFloatAsState(
        targetValue = progress, animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )
    val progressDegrees = animatedCurrentProgress * 360f
    Box {
        Canvas(
            modifier = modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            drawArc(
                brush = brushBackground,
                startAngle = 90f,
                sweepAngle = 360f,
                useCenter = false,
                style = drawStyle
            )
            drawArc(
                brush = brushColor,
                startAngle = 270f,
                sweepAngle = progressDegrees,
                useCenter = false,
                style = drawStyle
            )
        }
        Box(modifier = Modifier.align(Alignment.Center)){
            content()
        }
    }
}

@Preview
@Composable
fun PreviewProgressBar() {
    DeterminateProgressBar(modifier = Modifier.size(100.dp), progress = 0.75f) {
        Text("00:01")
    }
}