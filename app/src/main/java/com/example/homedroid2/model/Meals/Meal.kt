package com.example.homedroid2.model.Meals

import android.databinding.Bindable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meal {

    @SerializedName("idMeal")
    @Expose
    @Bindable
    var idMeal: String? = null
    @SerializedName("strMeal")
    @Expose
    @Bindable
    var strMeal: String? = null
    @SerializedName("strDrinkAlternate")
    @Expose
    @Bindable
    var strDrinkAlternate: Any? = null
    @SerializedName("strCategory")
    @Expose
    @Bindable
    var strCategory: String? = null
    @SerializedName("strArea")
    @Expose
    @Bindable
    var strArea: String? = null
    @SerializedName("strInstructions")
    @Expose
    @Bindable
    var strInstructions: String? = null
    @SerializedName("strMealThumb")
    @Expose
    @Bindable
    var strMealThumb: String? = null
    @SerializedName("strTags")
    @Bindable
    var strSource: Any? = null
    @SerializedName("dateModified")
    @Expose
    @Bindable
    var dateModified: Any? = null
}