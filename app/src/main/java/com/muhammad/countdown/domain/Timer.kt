package com.muhammad.countdown.domain

import android.annotation.SuppressLint
import java.time.Duration

@SuppressLint("NewApi")
data class Timer(
    val duration : Duration = Duration.ofSeconds(10),
    val durationRemaining : Duration = duration,
    val state: TimerState = TimerState.IDLE
){
    fun getPercentageComplete() : Float{
        return (durationRemaining.toMillis().toFloat() / duration.toMillis().toFloat())
    }
}

enum class TimerState{
    IDLE, RUNNING, FINISHED
}