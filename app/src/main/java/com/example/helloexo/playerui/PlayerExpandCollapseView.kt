package com.example.helloexo.playerui

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.helloexo.R

@Composable
fun PlayerExpandCollapseController(
    modifier: Modifier = Modifier
) {
    var currActivity = LocalContext.current as Activity
    var fullScreenState by remember { mutableStateOf(false) }

    IconButton(onClick = {
        if (fullScreenState) {
            currActivity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        } else {
            currActivity?.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }
        fullScreenState = !fullScreenState

    }, modifier) {
        val icon =
            if (fullScreenState) R.drawable.ic_full_screen_close else R.drawable.action_expand
        Icon(
            painterResource(id = icon),
            contentDescription = "expand",
            tint = Color.White
        )
    }
}