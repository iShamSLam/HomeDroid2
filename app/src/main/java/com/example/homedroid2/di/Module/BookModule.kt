package com.example.homedroid2.di.Module

import com.example.homedroid2.DataAdapter
import com.example.homedroid2.models.Book
import com.example.homedroid2.presenter.DetailsPresenter
import com.example.homedroid2.presenter.MainPresenter
import com.example.homedroid2.presenter.ZoomedPresenter
import com.example.homedroid2.views.DetailsView
import dagger.Module
import dagger.Provides

@Module
class BookModule{

    @Provides
    fun provideDataAdapter(presenter : MainPresenter): DataAdapter = DataAdapter{presenter.onBookClick(it)}

    @Provides
    fun provideMainPresenter(): MainPresenter = MainPresenter()

    @Provides
    fun provideZoomPresenter() : ZoomedPresenter = ZoomedPresenter()

    @Provides
    fun provideDetails() : DetailsPresenter = DetailsPresenter()

}