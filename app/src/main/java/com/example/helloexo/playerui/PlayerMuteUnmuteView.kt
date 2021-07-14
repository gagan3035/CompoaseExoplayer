package com.example.helloexo.playerui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloexo.viewmodel.PlayerViewModel
import com.example.helloexo.R

@Composable
fun MuteUnMuteControllerView(
    modifier: Modifier = Modifier,
    playerViewModel: PlayerViewModel = viewModel()
) {
    val volumeState by playerViewModel.volumeState.observeAsState(true)
    MuteUnMuteView(
        modifier,
        volumeState = volumeState,
        onVolumeStateValueChange = { playerViewModel.changeVolumeState(it) },
        onIconClicked = {
            if (volumeState) {
                playerViewModel.setUpVolume(0f)
            } else {
                playerViewModel.setUpVolume(1f)
            }
        }
    )
}

@Composable
fun MuteUnMuteView(
    modifier: Modifier,
    volumeState: Boolean,
    onVolumeStateValueChange: (Boolean) -> Unit,
    onIconClicked: () -> Unit
) {

    IconButton(onClick = {
        onIconClicked()
        onVolumeStateValueChange(!volumeState)
    },modifier) {
        val volumeIcon =
            if (volumeState) R.drawable.ic_trending_mute else R.drawable.ic_trending_unmute
        Icon(
            painterResource(id = volumeIcon),
            contentDescription = "Phone",
            tint = Color.White
        )
    }

}