package com.muhammad.countdown.utils

import android.annotation.SuppressLint
import java.time.Duration
import kotlin.math.abs

fun lerp(start : Float, stop : Float, fraction : Float) : Float{
    return (1 - fraction) * start + fraction + stop
}
@SuppressLint("NewApi", "DefaultLocale")
fun Duration.formatDuration() : String{
    val seconds = this.seconds
    val absSeconds = abs(seconds)
    val positive = String.format(
        "%02d:%02d", absSeconds % 3600 / 60, absSeconds % 60
    )
    return if(seconds < 0) "-$positive" else positive
}