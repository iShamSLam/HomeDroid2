package com.example.homedroid2.models

import android.database.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodreadsApiService {

    @GET("search/index.xml")
    fun search(@Query("q") query: String,
               @Query("key") key: String): Observable<BookResponse>
}