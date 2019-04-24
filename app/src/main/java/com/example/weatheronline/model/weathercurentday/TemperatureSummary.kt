package com.example.weatheronline.model.weathercurentday

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TemperatureSummary(
    @SerializedName("Past6HourRange")
    @Expose
    val past6HourRange: Past6HourRange = Past6HourRange(),

    @SerializedName("Past12HourRange")
    @Expose
    val past12HourRange: Past12HourRange = Past12HourRange(),

    @SerializedName("Past24HourRange")
    @Expose
    val past24HourRange: Past24HourRange = Past24HourRange()
)