package com.example.homedroid2.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.homedroid2.views.DetailsView

@InjectViewState
class DetailsPresenter : MvpPresenter<DetailsView>() {
    fun showInfo(title: String, author: String, rate: String, url: String) {
        viewState.showInfo(title, author, rate, url)
    }
}
