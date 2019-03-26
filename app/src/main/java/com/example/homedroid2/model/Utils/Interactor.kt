package com.example.homedroid2.model.Utils

import com.example.homedroid2.model.Api.FoodApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

private const val TAG: String = "Interactor"
private const val cashSize: Long = 10485760

class Interactor {
    private val service: FoodApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build().create(FoodApiService::class.java)
    }

    fun returnService(): FoodApiService = service

    fun returnClient(): OkHttpClient = okHttpClient

    fun returnCashSize(): Long = cashSize

    companion object {
        private val BASE_URL = "https://www.goodreads.com/"
        private  var okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
    }
}