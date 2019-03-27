package com.example.homedroid2.model.Api

import com.example.homedroid2.model.Meals.MealResponce
import io.reactivex.Single
import retrofit2.http.GET

interface FoodApiService {

    @GET("latest.php")
    fun search(): Single<MealResponce>
}