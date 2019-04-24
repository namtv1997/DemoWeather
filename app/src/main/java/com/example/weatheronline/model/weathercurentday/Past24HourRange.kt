package com.example.weatheronline.model.weathercurentday

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Past24HourRange (
    @SerializedName("Minimum")
    @Expose
    val minimum: MinimumCurrent = MinimumCurrent(),

    @SerializedName("Maximum")
    @Expose
    val maximum:MaximumCurrent=MaximumCurrent()
)