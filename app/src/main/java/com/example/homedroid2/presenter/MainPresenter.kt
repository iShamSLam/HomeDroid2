@file:Suppress("DEPRECATION")

package com.example.homedroid2.presenter

import android.icu.util.ULocale
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.homedroid2.models.GoodreadsApiService
import com.example.homedroid2.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import okhttp3.OkHttpClient




@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {
    private val BASE_URL = "https://www.goodreads.com/"
    private lateinit var okHttpClient: OkHttpClient.Builder

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
            .client(okHttpClient.build())
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

    fun createCache(file: File, REWRITE_CACHE_CONTROL_INTERCEPTOR : Interceptor) {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(file, cacheSize)
        okHttpClient = OkHttpClient.Builder().cache(cache)
        okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR)
    }
}
