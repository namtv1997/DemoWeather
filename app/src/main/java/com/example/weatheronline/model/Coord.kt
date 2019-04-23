package com.example.weatheronline.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Coord {
    @SerializedName("lat")
    @Expose
     val lat: Double? = null
    @SerializedName("lon")
    @Expose
    val lon: Double? = null
}