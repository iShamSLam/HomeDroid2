package com.example.homedroid2.views

import com.arellomobile.mvp.MvpView
import com.example.homedroid2.models.DataModel

interface MainView : MvpView, BaseView {
    fun getBooks(query: String?)
    fun handleResponse(dataModel: DataModel?)
    fun handleError(error: Throwable)
   // fun navigateToDetailsView(books: Book)
}
