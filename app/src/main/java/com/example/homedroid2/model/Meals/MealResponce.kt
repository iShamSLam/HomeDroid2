package com.example.homedroid2.model.Meals

import com.example.homedroid2.model.Meals.Meal
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MealResponce {

    @SerializedName("meals")
    @Expose
    var meals: List<Meal>? = null

}