package com.example.homedroid2.di.Module

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


@Module
class NavigationModule {
    private val cicerone: Cicerone<Router>

    init {
        cicerone = Cicerone.create()
    }

    @Provides
    @Singleton
    internal fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @Singleton
    internal fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}