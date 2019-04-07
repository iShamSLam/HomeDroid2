package com.example.homedroid2.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.homedroid2.views.DetailsView
import ru.terrakok.cicerone.Router

@InjectViewState
class DetailsPresenter : MvpPresenter<DetailsView>() {

    private lateinit var router: Router

    fun showInfo(title: String, author: String, rate: String, url: String) {
        viewState.showInfo(title, author, rate, url)
    }

    public override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router = viewState.shareRouter()
    }

    fun onPictureClick() = router.navigateTo(com.example.homedroid2.Screen.ZoomScreen())

    fun onBackPressed() {
        router.exit()
    }
}
