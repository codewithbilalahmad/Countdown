package com.muhammad.countdown.presentation.viewModel

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.countdown.domain.Timer
import com.muhammad.countdown.domain.TimerAction
import com.muhammad.countdown.domain.TimerState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.time.Duration

@SuppressLint("NewApi")
class TimerViewModel : ViewModel() {
    private val _state = MutableLiveData<Timer>()
    val state : LiveData<Timer>
        get() = _state
    private var timer: CountDownTimer? = null
    val timerActionChannel = Channel<TimerAction>()

    init {
        _state.value = Timer()
    }

    private fun startTimer(duration: Duration) {
        timer = object : CountDownTimer(duration.toMillis(), 10) {
            override fun onTick(millisUntilFinished: Long) {
                _state.value = _state.value!!.copy(
                    state = TimerState.RUNNING,
                    durationRemaining = Duration.ofMillis(millisUntilFinished)
                )
            }

            override fun onFinish() {
                _state.value = _state.value!!.copy(
                    state = TimerState.FINISHED,
                    durationRemaining = Duration.ZERO
                )
                viewModelScope.launch {
                    timerActionChannel.send(TimerAction.TimerFinished)
                }
            }

        }
        timer?.start()
    }

    private fun stopTimer() {
        timer?.cancel()
        _state.value = _state.value!!.copy(
            state = TimerState.IDLE,
            durationRemaining = _state.value!!.duration
        )
    }

    fun timerButtonPressed() {
        when (_state.value!!.state) {
            TimerState.IDLE -> {
                startTimer(_state.value!!.duration)
            }

            TimerState.RUNNING -> {
                stopTimer()
            }

            TimerState.FINISHED -> {
                resetTimer()
            }
        }
    }
    fun removeTime(duration : Duration){
        if(_state.value!!.duration.minus(duration).isNegative){
            return
        }
        _state.value = _state.value!!.copy(duration = _state.value!!.duration.minus(duration))
    }
     fun addTime(duration : Duration){
        _state.value = _state.value!!.copy(duration = _state.value!!.duration.plus(duration ))
    }
    private fun resetTimer() {
        _state.value = _state.value!!.copy(
            state = TimerState.IDLE,
            durationRemaining = _state.value!!.duration
        )
        viewModelScope.launch {
            timerActionChannel.send(TimerAction.TimerReset)
        }
    }
}