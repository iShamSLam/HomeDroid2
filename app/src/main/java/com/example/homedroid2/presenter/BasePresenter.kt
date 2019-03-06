package com.example.homedroid2.presenter

interface BasePresenter<T> {
    fun attachView(view: T)
    fun detachView()
}
