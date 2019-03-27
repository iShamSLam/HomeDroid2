package com.example.homedroid2.DI

import com.example.homedroid2.UI
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class)])
interface ViewModelInjector {

    fun inject(ListViewModel: RecViewModel)

    fun inject(ViewModel: MealViewModel)

    @Component.Builder
    interface Builder {

        fun build(): ViewModelInjector

        fun netModule(netModule: NetModule): Builder
    }
}