package com.example.homedroid2.di.Module;

import javax.inject.Singleton;

import com.example.homedroid2.Subnavigation.LocalCiceroneHolder;
import dagger.Module;
import dagger.Provides;

@Module
public class LocalNavigationModule {

    @Provides
    @Singleton
    LocalCiceroneHolder provideLocalNavigationHolder() {
        return new LocalCiceroneHolder();
    }
}
