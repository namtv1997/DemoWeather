package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Direction(
    @SerializedName("Degrees")
    @Expose
    val degrees: Int? = null,

    @SerializedName("Localized")
    @Expose
    val localized: String? = null,

    @SerializedName("English")
    @Expose
    val english: String? = null
)