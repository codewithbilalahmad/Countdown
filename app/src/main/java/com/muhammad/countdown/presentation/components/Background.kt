package com.muhammad.countdown.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import com.muhammad.countdown.presentation.theme.lightPurple
import com.muhammad.countdown.presentation.theme.orange
import com.muhammad.countdown.presentation.theme.peach

@Composable
fun BackgroundGradient(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val animationSpec: InfiniteRepeatableSpec<Color> = infiniteRepeatable(
        animation = tween(durationMillis = 3000, easing = FastOutSlowInEasing),
        repeatMode = RepeatMode.Reverse
    )
    val colorFirst by infiniteTransition.animateColor(
        initialValue = orange,
        targetValue = peach,
        animationSpec = animationSpec
    )
    val colorSecond by infiniteTransition.animateColor(
        initialValue = peach,
        targetValue = lightPurple,
        animationSpec = animationSpec
    )
    val colorThird by infiniteTransition.animateColor(
        initialValue = lightPurple,
        targetValue = orange,
        animationSpec = animationSpec
    )
    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val numberOfBubbles = 23
    val bubbleInfo = remember {
        val bubbleInfos = mutableListOf<BubbleInfo>()
        for (i in 0..numberOfBubbles) {
            val offset = Offset(x = Math.random().toFloat(), Math.random().toFloat())
            val offsetEnd = Offset(x = Math.random().toFloat(), y = Math.random().toFloat())
            val radius = Math.random().toFloat() * 50
            val radiusEnd = Math.random().toFloat() * 50
            val bubblePoint = BubbleInfo(
                point = offset,
                pointEnd = offsetEnd,
                alpha = Math.random().toFloat(),
                radius, radiusEnd
            )
            bubbleInfos.add(bubblePoint)
        }
        bubbleInfos
    }
    Canvas(modifier = modifier.fillMaxSize()) {
        val gradient = Brush.verticalGradient(
            listOf(colorFirst, colorSecond, colorThird),
            0f, size.height.toDp().toPx(), TileMode.Mirror
        )
        drawRect(brush = gradient)
        for (bubble in bubbleInfo) {
            val offsetAnimated =
                lerp(start = bubble.point, fraction = animatedProgress, stop = bubble.pointEnd)
            val radiusAnimated = com.muhammad.countdown.utils.lerp(
                start = bubble.radius,
                stop = bubble.radiusEnd,
                fraction = animatedProgress
            )
            val sizeScaled = size * 1.4f
            drawCircle(
                color = orange,
                radius = radiusAnimated * density,
                center = Offset(
                    x = offsetAnimated.x * sizeScaled.width,
                    y = offsetAnimated.y * sizeScaled.height
                ), alpha = bubble.alpha
            )
        }
    }
}

data class BubbleInfo(
    val point: Offset,
    val pointEnd: Offset,
    val alpha: Float,
    val radius: Float,
    val radiusEnd: Float,
)

@Preview
@Composable
private fun BackgroundGradientPreview() {
    BackgroundGradient()
}