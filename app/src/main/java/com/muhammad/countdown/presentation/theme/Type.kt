package com.muhammad.countdown.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.muhammad.countdown.R

val fontFamily = FontFamily(
    Font(R.font.ibm_plexmono_regular),
    Font(R.font.ibm_plexmono_light, FontWeight.Light),
    Font(R.font.ibm_plexmono_bold, FontWeight.Bold),
    Font(R.font.ibm_plexmono_semibold, FontWeight.SemiBold),
    Font(R.font.ibm_plexmono_italic, style = FontStyle.Italic),
    Font(R.font.ibm_plexmono_medium, weight = FontWeight.Medium),
)
val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal
    ),
    displayMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal
    ),
    displaySmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
    )
)