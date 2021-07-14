package com.example.helloexo.player

interface HSPlayerController {

    fun initialise()
    fun play()
    fun pause()
    fun setVolume(level:Float)
    fun fastForward(time :Long)
    fun rewind(time:Long)
    fun release()
}