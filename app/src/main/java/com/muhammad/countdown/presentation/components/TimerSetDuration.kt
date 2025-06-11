package com.muhammad.countdown.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muhammad.countdown.utils.formatDuration
import java.time.Duration

@SuppressLint("NewApi")
@Composable
fun TimerSetDuration(
    textDuration : Duration,
    onAddTime: (Duration) -> Unit,
    onRemoveTime: (Duration) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Row(horizontalArrangement = Arrangement.SpaceAround){
            Icon(imageVector = Icons.Outlined.Add,contentDescription = null, modifier = Modifier.size(56.dp).clickable {
                onAddTime(Duration.ofMinutes(1))
            }.padding(8.dp))
            Spacer(Modifier.width(96.dp))
            Icon(imageVector = Icons.Outlined.Add, contentDescription = null, modifier = Modifier.size(56.dp).clickable {
                onAddTime(Duration.ofSeconds(1))
            }.padding(8.dp))
        }
        TimerText(timerText = textDuration.formatDuration())
        Row(horizontalArrangement = Arrangement.SpaceAround){
            Icon(imageVector = Icons.Outlined.Remove,contentDescription = null, modifier = Modifier.size(56.dp).clickable {
                onRemoveTime(Duration.ofMinutes(1))
            }.padding(8.dp))
            Spacer(Modifier.width(96.dp))
            Icon(imageVector = Icons.Outlined.Remove, contentDescription = null, modifier = Modifier.size(56.dp).clickable {
                onRemoveTime(Duration.ofSeconds(1))
            }.padding(8.dp))
        }
    }
}