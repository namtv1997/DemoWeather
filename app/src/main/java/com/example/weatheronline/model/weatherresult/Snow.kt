package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Snow (
    @SerializedName("Value")
    @Expose
    val value: Int? = null,

    @SerializedName("Unit")
    @Expose
    val unit: String? = null,

    @SerializedName("UnitType")
    @Expose
    val unitType: Int? = null
)