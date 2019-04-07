package com.example.homedroid2.di.Module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router>

    init {
        cicerone = Cicerone.create()
    }

    @Provides
    @Singleton
    internal fun provideRouter(): Router = cicerone.router

    @Provides
    @Singleton
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
