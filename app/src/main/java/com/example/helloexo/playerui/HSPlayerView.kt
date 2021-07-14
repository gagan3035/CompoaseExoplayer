package com.example.helloexo.playerui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloexo.viewmodel.HSPlayerEventListenerViewModel
import com.example.helloexo.viewmodel.PlayerViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes

@Composable
fun HSPlayerView(
    videoUrl: String,
    playerViewModel: PlayerViewModel = viewModel(),
    playerListenerViewModel: HSPlayerEventListenerViewModel = viewModel(),
    content: @Composable (() -> Unit)? = null
) {

    var context = LocalContext.current
    var lifecycleOwner = LocalLifecycleOwner.current
    var orientationConfig = LocalConfiguration.current

    LaunchedEffect(videoUrl) {
        val mediaItem: MediaItem = MediaItem.Builder()
            .setUri(videoUrl)
            .setMimeType(MimeTypes.APPLICATION_MPD)
            .build()
        playerViewModel.setMediaItem(mediaItem)
        playerViewModel?.playVideo()
    }

    DisposableEffect(true) {
        onDispose {
            playerViewModel?.releasePlayer()
        }
    }

    Box() {
        AndroidView(
            factory = {
                playerViewModel.initialisePlayer()

                lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_START)
                    fun onStart() {
                        playerListenerViewModel.addListener()
                        playerViewModel?.playVideo()
                    }

                    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                    fun onStop() {
                        playerViewModel?.pauseVideo()
                        playerListenerViewModel.removeListener()
                    }
                })

                PlayerView(context).apply {
                    player = playerViewModel.getHSExoPlayer()
                    playerViewModel.playVideo()
                    this.useController = false
                }
            },
            update = {
                if (orientationConfig.orientation == ORIENTATION_LANDSCAPE) {
                    it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    it.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                } else {
                    it.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
            }
        )
        if (content == null) {
            PlayerControllerViews()
        } else {
            content()
        }
    }


}