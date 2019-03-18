package com.example.homedroid2

import android.app.Application
import com.arellomobile.mvp.MvpFacade
import com.example.homedroid2.di.Component.AppComponent
import com.example.homedroid2.di.Module.AppModule
import com.example.homedroid2.di.Module.NetModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .build()
    }

    companion object {

        var appComponent: AppComponent? = null
            private set
    }
}
