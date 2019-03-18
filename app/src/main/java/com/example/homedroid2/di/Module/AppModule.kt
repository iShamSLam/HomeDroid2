package com.example.homedroid2.di.Module

import android.content.Context
import com.example.homedroid2.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApp(): Context = app
}
