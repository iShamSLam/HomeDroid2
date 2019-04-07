package com.example.homedroid2.views

import com.arellomobile.mvp.MvpView
import ru.terrakok.cicerone.Router

interface BaseView : MvpView {
    fun shareRouter() : Router
}