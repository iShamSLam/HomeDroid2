@file:Suppress("DEPRECATION")

package com.example.homedroid2.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.homedroid2.models.Book
import com.example.homedroid2.models.Interactor
import com.example.homedroid2.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    private val interactor: Interactor = Interactor()
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()


    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadXML()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

    fun onBookClick(book: Book) = viewState.navigateToDetailsView(book)


    fun loadXML(query: String = "", page: String = "1") {
        mCompositeDisposable?.add(
            interactor.returnService().search(page, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(viewState::handleResponse, viewState::handleError)
        )
    }

    fun createCache(file: File) {
        val cacheSize: Long = interactor.returnCashSize() // 10 MB
        val cache = Cache(file, cacheSize)
        var client = interactor.returnClient()
        client = OkHttpClient.Builder()
            .cache(cache)
            .build()
    }
}
