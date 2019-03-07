package com.example.homedroid2.views

import com.arellomobile.mvp.MvpView


interface DetailsView : MvpView {
    fun showInfo(title: String, author: String, rate: String, url: String)
}