package com.example.homedroid2.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.homedroid2.R
import com.squareup.picasso.Picasso

class DetailsView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_view)
        val tvTitle: TextView = findViewById(R.id.tv_dtitle)
        val tvAuthor: TextView = findViewById(R.id.tv_dauthor)
        val tvRate: TextView = findViewById(R.id.tv_drate)
        val ivPhoto: ImageView = findViewById(R.id.iv_dphoto)

        val intent: Intent = getIntent()
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val rate = intent.getStringExtra("rate")
        val url = intent.getStringExtra("url")

        tvTitle.text = "Title: " + title
        tvAuthor.text = "Author: " + author
        tvRate.text = rate
        Picasso.get().load(url).into(ivPhoto)
    }
}
