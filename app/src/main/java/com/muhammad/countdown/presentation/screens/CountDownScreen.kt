@file:Suppress("DEPRECATION")

package com.muhammad.countdown.presentation.screens

import android.content.Context
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.muhammad.countdown.domain.Timer
import com.muhammad.countdown.domain.TimerAction
import com.muhammad.countdown.presentation.viewModel.TimerViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import com.muhammad.countdown.R
import com.muhammad.countdown.domain.TimerState
import com.muhammad.countdown.presentation.components.BackgroundGradient
import com.muhammad.countdown.presentation.components.DeterminateProgressBar
import com.muhammad.countdown.presentation.components.FlashingTimerText
import com.muhammad.countdown.presentation.components.TimerButton
import com.muhammad.countdown.presentation.components.TimerSetDuration
import com.muhammad.countdown.presentation.components.TimerText
import com.muhammad.countdown.utils.formatDuration
import java.time.Duration

@Composable
fun CountDownScreen() {
    val navHostController = rememberNavController()
    val viewModel = viewModel<TimerViewModel>()
    val state by viewModel.state.observeAsState(initial = Timer())
    val context = LocalContext.current
    LaunchedEffect(navHostController) {
        var mediaPlayer: MediaPlayer? = null
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        viewModel.timerActionChannel.receiveAsFlow().collect { action ->
            when (action) {
                TimerAction.TimerFinished -> {
                    startVibrating(vibrator)
                    mediaPlayer = MediaPlayer.create(context, R.raw.alarm_2)
                    playSound(mediaPlayer!!)
                }

                TimerAction.TimerReset -> {
                    stopVibrating(vibrator)
                    stopPlayingSound(mediaPlayer)
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundGradient()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            TimerSection(state = state, onAddTime = {duration ->
                viewModel.addTime(duration)
            }, onRemoveTime = {duration ->
                viewModel.removeTime(duration)
            })
        }
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).navigationBarsPadding()){
            TimerButton(timer = state, onClick = {
                viewModel.timerButtonPressed()
            })
        }
    }
}

@Composable
fun TimerSection(state: Timer, onAddTime: (Duration) -> Unit, onRemoveTime: (Duration) -> Unit) {
    val configuration = LocalConfiguration.current
    when(state.state){
        TimerState.IDLE -> {
            TimerSetDuration(textDuration = state.duration, onAddTime, onRemoveTime)
        }
        TimerState.RUNNING ->{
            when(configuration.orientation){
                Configuration.ORIENTATION_LANDSCAPE ->{
                    TimerText(timerText = state.durationRemaining.formatDuration())
                }
                else ->{
                    DeterminateProgressBar(progress = state.getPercentageComplete()) {
                        TimerText(timerText = state.durationRemaining.formatDuration())
                    }
                }
            }
        }
        TimerState.FINISHED -> {
            FlashingTimerText(timerText = "00:00")
        }
    }
}

fun startVibrating(vibrator: Vibrator) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val vibratorPattern = longArrayOf(0, 400, 800, 600, 800, 800, 800, 1000)
        val amplitudes = intArrayOf(0, 255, 0, 255, 0, 255, 0, 255)
        vibrator.vibrate(
            VibrationEffect.createWaveform(
                vibratorPattern,
                amplitudes,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        vibrator.vibrate(500)
    }
}

fun playSound(mediaPlayer: MediaPlayer) {
    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release()
    }
    mediaPlayer.start()
}

fun stopPlayingSound(mediaPlayer: MediaPlayer?) {
    if (mediaPlayer?.isPlaying == true) {
        mediaPlayer.stop()
    } else{
        return
    }
}

fun stopVibrating(vibrator: Vibrator) {
    if(vibrator.hasVibrator()){
        return
    } else{
        vibrator.cancel()
    }
}
