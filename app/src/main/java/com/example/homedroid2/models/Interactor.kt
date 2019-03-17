package com.example.homedroid2.models

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

private const val TAG: String = "Interactor"
private const val cashSize: Long = 10485760

class Interactor {
    private val service: GoodreadsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build().create(GoodreadsApiService::class.java)
    }

    fun returnService(): GoodreadsApiService = service

    fun returnClient(): OkHttpClient = okHttpClient

    fun returnCashSize(): Long = cashSize

    companion object {
        private val BASE_URL = "https://www.goodreads.com/"
        private  var okHttpClient: OkHttpClient = OkHttpClient()
    }
}
