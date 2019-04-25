package com.example.weatheronline.model.weathercurentday

import com.example.weatheronline.model.weatherresult.Speed
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pressure (
    @SerializedName("Metric")
    @Expose
    val metric: SpeedCurent = SpeedCurent(),
    @SerializedName("Imperial")
    @Expose
    val imperial: SpeedCurent = SpeedCurent()
)