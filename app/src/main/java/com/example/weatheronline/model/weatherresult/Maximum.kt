package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Maximum(
    @SerializedName("Value")
    @Expose
    val value: Float? = null,

    @SerializedName("Unit")
    @Expose
    val unit: String? = null,
    @SerializedName("UnitType")
    @Expose
    val unitType: Int? = null
)