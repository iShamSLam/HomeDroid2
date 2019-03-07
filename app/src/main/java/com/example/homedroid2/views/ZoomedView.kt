package com.example.homedroid2.views

import com.arellomobile.mvp.MvpView

interface ZoomedView : MvpView {
    fun showImage(url: String)
}