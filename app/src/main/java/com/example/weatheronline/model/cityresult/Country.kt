package com.example.weatheronline.model.cityresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Country(

    @SerializedName("ID")
    @Expose
    val id: String? = null,

    @SerializedName("LocalizedName")
    @Expose
    val localizedName: String? = null
)