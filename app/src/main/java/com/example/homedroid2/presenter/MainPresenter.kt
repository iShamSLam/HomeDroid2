@file:Suppress("DEPRECATION")

package com.example.homedroid2.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.homedroid2.models.Book
import com.example.homedroid2.models.GoodreadsApiService
import com.example.homedroid2.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.File


@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    private val BASE_URL = "https://www.goodreads.com/"
    private lateinit var okHttpClient: OkHttpClient
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()
    private lateinit var requestInterface: GoodreadsApiService

    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        buildRequest()
        loadXML()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

    fun onBookClick(book: Book) = viewState.navigateToDetailsView(book)

    fun buildRequest() {
        requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build().create(GoodreadsApiService::class.java)
    }

    fun loadXML(query: String = "", page: String = "1") {
        mCompositeDisposable?.add(
            requestInterface.search(page, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(viewState::handleResponse, viewState::handleError)
        )
    }

    fun createCache(file: File) {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(file, cacheSize)

        okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .build()
    }
}
