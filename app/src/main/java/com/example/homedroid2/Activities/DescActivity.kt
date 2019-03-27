package com.example.homedroid2.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.homedroid2.R
import kotlinx.android.synthetic.main.activity_desc.*

class DescActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)

        tv_desc_title.text = intent.getStringExtra("title")
        tv_desc_content.text = intent.getStringExtra("body")
    }
}
