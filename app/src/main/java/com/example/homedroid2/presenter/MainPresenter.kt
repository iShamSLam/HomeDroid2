@file:Suppress("DEPRECATION")

package com.example.homedroid2.presenter

import com.example.homedroid2.models.GoodreadsApiService
import com.example.homedroid2.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


class MainPresenter(val view1: MainView) {


    private val BASE_URL = "https://www.goodreads.com/"

    internal fun loadXML() {

        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build().create(GoodreadsApiService::class.java)

        view1?.mCompositeDisposable?.add(
            requestInterface.search("Anime")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(view1::handleResponse, view1::handleError)
        )
    }
}
