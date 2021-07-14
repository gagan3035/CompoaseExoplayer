package com.example.helloexo.player

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class HSPlayerModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindsHSPlayer(hsExoPlayerImpl: HSExoPlayerImpl):HSPlayerController
}