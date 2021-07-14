package com.example.helloexo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.helloexo.player.HSExoPlayerImpl
import com.example.helloexo.player.HSPlayerController
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.video.VideoSize
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HSPlayerEventListenerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val hsPlayerController: HSPlayerController
) : ViewModel(),
    Player.Listener {

    private val _errorLiveData = MutableLiveData<Boolean>()
    val errorLiveData : LiveData<Boolean>
    get() = _errorLiveData

    private val _playbackState = MutableLiveData<String>()
    val playbackState : LiveData<String>
    get() = _playbackState

    fun addListener(){
        Log.d("GAGAN", "addListener: ")
        (hsPlayerController as HSExoPlayerImpl).hsPlayer?.addListener(this)
    }

    fun removeListener(){
        Log.d("GAGAN", "removeListener: ")
        (hsPlayerController as HSExoPlayerImpl).hsPlayer?.removeListener(this)
    }

    override fun onPlaybackStateChanged(state: Int) {
        Log.d("GAGAN", "onPlaybackStateChanged: $state")
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        Log.d("GAGAN", "onPlayerError: ${error.localizedMessage}")
    }

    override fun onVideoSizeChanged(videoSize: VideoSize) {
        super.onVideoSizeChanged(videoSize)
        Log.d("GAGAN","onVideoSizeChanges ${videoSize.height} and width is ${videoSize.width}")
    }
}