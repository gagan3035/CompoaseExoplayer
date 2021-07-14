package com.example.helloexo.playerui

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.helloexo.R

@Composable
fun BoxScope.PlayerControllerViews(
    modifier: Modifier =Modifier
) {
        //play pause button view
        PlayPausePlayerControllerView(modifier.align(Alignment.Center))
        // expand collapse view
        PlayerExpandCollapseController(modifier.align(Alignment.TopStart))
        // mute unmute view
        MuteUnMuteControllerView(modifier.align(Alignment.TopEnd))
        // settings icon
        IconButton(onClick = {

        }, modifier.align(Alignment.TopCenter)) {
            Icon(
                painterResource(id = R.drawable.ic_player_settings_18dp),
                contentDescription = "setting",
                tint = Color.White
            )
        }
}