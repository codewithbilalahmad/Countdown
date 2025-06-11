package com.muhammad.countdown.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = white,
    inversePrimary = gray_10,
    secondary = orange
)

private val LightColorScheme = lightColorScheme(
    primary = black,
    inversePrimary = gray_90,
    secondary = orange
)

@Composable
fun CountdownTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography, shapes = shapes,
        content = content
    )
}