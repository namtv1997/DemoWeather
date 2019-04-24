package com.example.weatheronline.model.weathercurentday

import com.example.weatheronline.model.weatherresult.Speed
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Past24Hours (
    @SerializedName("Metric")
    @Expose
    val metric: Speed = Speed(),
    @SerializedName("Imperial")
    @Expose
    val imperial: Speed = Speed()
)