package com.example.homedroid2.UI

import android.arch.lifecycle.ViewModel
import android.support.v7.widget.RecyclerView
import com.example.homedroid2.DI.NetModule
import com.example.homedroid2.DI.ViewModelInjector

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .netModule(NetModule)
        .build()

    private fun inject(){
        when(this){
            is RecViewModel -> injector.inject(this)
        }
    }
}