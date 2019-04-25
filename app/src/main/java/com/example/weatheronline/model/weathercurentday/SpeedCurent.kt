package com.example.weatheronline.model.weathercurentday

import com.example.weatheronline.model.weatherresult.SpeedDouble
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpeedCurent (
    @SerializedName("Metric")
    @Expose
    val metric: SpeedDouble = SpeedDouble(),
    @SerializedName("Imperial")
    @Expose
    val imperial: SpeedDouble = SpeedDouble()
)