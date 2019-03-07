package com.example.homedroid2.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.homedroid2.views.ZoomedView

@InjectViewState
class ZoomedPresenter : MvpPresenter<ZoomedView>() {
    fun showImage(url: String) {
        viewState.showImage(url)
    }
}