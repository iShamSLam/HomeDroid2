@file:Suppress("DEPRECATION")

package com.example.homedroid2.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.homedroid2.models.GoodreadsApiService
import com.example.homedroid2.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {
    private val BASE_URL = "https://www.goodreads.com/"


    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadXML()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun loadXML(query: String = "") {
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build().create(GoodreadsApiService::class.java)

//        activity.mCompositeDisposable?.add(
        requestInterface.search(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(viewState::handleResponse, viewState::handleError)
//        )
    }
}
