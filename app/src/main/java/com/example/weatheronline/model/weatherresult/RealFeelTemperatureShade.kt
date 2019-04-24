package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RealFeelTemperatureShade (
    @SerializedName("Minimum")
    @Expose
    val minimum: Minimum = Minimum(),

    @SerializedName("Maximum")
    @Expose
    val maximum: Maximum = Maximum()
)