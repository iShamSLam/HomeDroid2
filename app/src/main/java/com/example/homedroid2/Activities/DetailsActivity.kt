package com.example.homedroid2.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.homedroid2.R
import com.example.homedroid2.Screen
import com.example.homedroid2.presenter.DetailsPresenter
import com.example.homedroid2.views.DetailsView
import com.squareup.picasso.Picasso
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    @InjectPresenter
    lateinit var mDetailsPresenter: DetailsPresenter

    @Inject
    public lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var tvTitle: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvRate: TextView? = null
    private var ivPhoto: ImageView? = null
    private lateinit var url: String
    private val navigator =
        object : SupportAppNavigator(this, com.example.homedroid2.R.id.container) {
            override fun applyCommands(commands: Array<Command>) {
                super.applyCommands(Array<Command>(1) { Replace(Screen.ZoomScreen())})
                supportFragmentManager.executePendingTransactions()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent: Intent = getIntent()
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val rate = intent.getStringExtra("rate")
        url = intent.getStringExtra("url")

        mDetailsPresenter.showInfo(title, author, rate, url)
    }

    override fun showInfo(title: String, author: String, rate: String, url: String) {
        tvTitle?.setText("Title: " + title)
        tvAuthor?.setText("Author: " + author)
        tvRate?.setText(rate)
        Picasso.get().load(url).into(ivPhoto)
    }

    fun imageClick(view: View) {
        val intent = Intent(this, ZoomedActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun shareRouter(): Router {
        return router
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }
}

