package com.example.helloexo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.helloexo.player.HSExoPlayerImpl
import com.example.helloexo.player.HSPlayerController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor( private val savedStateHandle: SavedStateHandle,private val hsPlayerController: HSPlayerController) : ViewModel() {



    private val _playbackState = MutableLiveData(true)
    val playbackState: LiveData<Boolean> = _playbackState

    private val _volumeState = MutableLiveData(true)
    val volumeState: LiveData<Boolean> = _volumeState

    fun initialisePlayer(){
        hsPlayerController.initialise()
    }

    fun playVideo(){
        hsPlayerController.play()
    }

    fun pauseVideo(){
        hsPlayerController.pause()
    }

    fun forwardVideo(time: Long){
        hsPlayerController.fastForward(time)
    }

    fun rewind(time: Long){
        hsPlayerController.rewind(time)
    }

    fun setUpVolume(level : Float){
        hsPlayerController.setVolume(level)
    }

    fun releasePlayer(){
        hsPlayerController.release()
    }

    fun setMediaItem(mediaItem: MediaItem){
        (hsPlayerController as HSExoPlayerImpl).hsPlayer?.setMediaItem(mediaItem)
    }

    fun getHSExoPlayer(): Player? {
        return (hsPlayerController as HSExoPlayerImpl).hsPlayer
    }

    fun changePlaybackState(state:Boolean){
        _playbackState.value = state
    }

    fun changeVolumeState(state:Boolean){
        _volumeState.value = state
    }
}