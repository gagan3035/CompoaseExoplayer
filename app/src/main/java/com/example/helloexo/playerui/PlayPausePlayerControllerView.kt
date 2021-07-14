package com.example.helloexo.playerui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloexo.viewmodel.PlayerViewModel
import com.example.helloexo.R

@Composable

fun PlayPausePlayerControllerView(
    modifier: Modifier = Modifier,
    playerViewModel: PlayerViewModel = viewModel()
) {
    val currContext = LocalContext.current
    val playbackState by playerViewModel.playbackState.observeAsState(true)
    PlayPausePlayerView(
        modifier,
        currContext,
        playerViewModel,
        playbackState,
        onPlaybackStateValueChange = { playerViewModel.changePlaybackState(it) },
    )
}

@Composable
fun PlayPausePlayerView(
    modifier: Modifier = Modifier,
    context: Context,
    playerViewModel: PlayerViewModel,
    playbackState: Boolean,
    onPlaybackStateValueChange: (Boolean) -> Unit,
) {
    Row(modifier) {
        IconButton(onClick = {
            Toast.makeText(context, "rewind Clicked", Toast.LENGTH_SHORT)
                .show()
            playerViewModel.rewind(10)
        }) {
            Icon(
                painterResource(id = R.drawable.ic_player_replay),
                contentDescription = "Share",
                tint = Color.White
            )
        }

        IconButton(onClick = {
            if (!playbackState) {
                playerViewModel.playVideo()
            } else {
                playerViewModel.pauseVideo()
            }
            onPlaybackStateValueChange(!playbackState)
        }) {
            val playPauseIcon =
                if (playbackState) R.drawable.ic_player_pause else R.drawable.ic_player_play
            Icon(
                painterResource(id = playPauseIcon),
                contentDescription = "Play",
                tint = Color.White
            )
        }
        IconButton(onClick = {
            Toast.makeText(context, "forward Clicked", Toast.LENGTH_SHORT)
                .show()
            playerViewModel.forwardVideo(10000)
        }) {
            Icon(
                painterResource(id = R.drawable.ic_player_forward),
                contentDescription = "Phone",
                tint = Color.White
            )
        }
    }
}
