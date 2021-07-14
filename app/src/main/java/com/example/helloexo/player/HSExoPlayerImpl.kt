package com.example.helloexo.player

import android.content.Context
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 *
 */


class HSExoPlayerImpl @Inject constructor(@ApplicationContext val context: Context) : HSPlayerController {
    var hsPlayer: SimpleExoPlayer? = null


    override fun initialise() {
        initPlayer()
    }

    private fun initPlayer() {
        if (hsPlayer == null) {
            hsPlayer = buildPlayer(context)
        }
    }

    private fun buildPlayer(context: Context): SimpleExoPlayer? {
        var trackSelector = DefaultTrackSelector(context)
        return SimpleExoPlayer.Builder(context).setTrackSelector(trackSelector).build()
    }

    override fun play() {
        hsPlayer?.playWhenReady = true
        hsPlayer?.prepare()
    }

    override fun pause() {
        hsPlayer?.playWhenReady = false
    }

    override fun setVolume(level: Float) {
        hsPlayer?.volume = level
    }

    override fun fastForward(time: Long) {
        hsPlayer?.seekTo(hsPlayer?.currentPosition?.plus(time)!!)
    }

    override fun rewind(time: Long) {
        hsPlayer?.seekTo(hsPlayer?.currentPosition?.minus(time)!!)
    }

    override fun release() {
        hsPlayer?.release()
        hsPlayer == null
    }

}