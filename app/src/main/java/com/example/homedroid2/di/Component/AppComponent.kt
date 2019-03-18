package com.example.homedroid2.di.Component

import android.content.Context
import com.example.homedroid2.di.Module.AppModule
import com.example.homedroid2.di.Module.NetModule
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {
    fun provideApp(): Context
}