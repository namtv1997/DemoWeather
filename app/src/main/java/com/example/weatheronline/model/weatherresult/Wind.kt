package com.example.weatheronline.model.weatherresult

import com.example.weatheronline.model.weathercurentday.SpeedCurent
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind(

    @SerializedName("Speed")
    @Expose
    val speed: SpeedCurent = SpeedCurent(),

    @SerializedName("Direction")
    @Expose
    val direction: Direction = Direction()
)