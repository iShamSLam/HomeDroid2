package com.example.homedroid2.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.homedroid2.presenter.DetailsPresenter
import com.squareup.picasso.Picasso

class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    @InjectPresenter
    lateinit var mDetailsPresenter: DetailsPresenter

    private var tvTitle: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvRate: TextView? = null
    private var ivPhoto: ImageView? = null
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        tvTitle = findViewById(R.id.tv_dtitle)
        tvAuthor = findViewById(R.id.tv_dauthor)
        tvRate = findViewById(R.id.tv_drate)
        ivPhoto = findViewById(R.id.iv_dphoto)

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
}

