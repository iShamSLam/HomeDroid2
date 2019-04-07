package com.example.homedroid2

import android.app.Application
import com.example.homedroid2.di.Component.AppComponent
import com.example.homedroid2.di.Component.BookComponent


class App : Application() {

    private lateinit var appComponent: BookComponent

    companion object {

        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    fun getAppComponent(): BookComponent {
        if(appComponent==null){
            appComponent = DaggerAppComponent.builder().build()
        }
        return appComponent
    }
}