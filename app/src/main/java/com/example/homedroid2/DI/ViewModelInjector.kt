package com.example.homedroid2.DI

import dagger.Component
import shgbievi.gmail.com.jobsapp.di.modules.NetModule
import shgbievi.gmail.com.jobsapp.ui.jobs.JobViewModel
import shgbievi.gmail.com.jobsapp.ui.jobsList.JobsListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class)])
interface ViewModelInjector {

    fun inject(jobsListViewModel: JobsListViewModel)

    fun inject(jobViewModel: JobViewModel)

    @Component.Builder
    interface Builder {

        fun build(): ViewModelInjector

        fun netModule(netModule: NetModule): Builder
    }
}