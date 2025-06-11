package com.muhammad.countdown.domain

sealed interface TimerAction {
    data object TimerFinished : TimerAction
    data object TimerReset : TimerAction
}