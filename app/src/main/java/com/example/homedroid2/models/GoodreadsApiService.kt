package com.example.homedroid2.models

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodreadsApiService {

    @GET("search/index.xml?key=BURnIfVQ4bM0d3vrdW0Flg")
    fun search(@Query("page") page: String, @Query("q") query: String): Single<DataModel>
}
