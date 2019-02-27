package com.example.homedroid2.models

import android.database.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodreadsApiService {

    @GET("search/index.xml?key=BURnIfVQ4bM0d3vrdW0Flg")
    fun search(@Query("q") query: String): Call<DataModel>
}