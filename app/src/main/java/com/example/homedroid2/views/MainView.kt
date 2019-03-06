package com.example.homedroid2.views

import com.arellomobile.mvp.MvpView
import com.example.homedroid2.models.DataModel
import java.io.File

interface MainView : MvpView {
    fun getBooks(query:String?)
    fun handleResponse(dataModel: DataModel?)
    fun handleError(error: Throwable)
}
