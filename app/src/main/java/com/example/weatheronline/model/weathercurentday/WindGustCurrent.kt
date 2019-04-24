package com.example.weatheronline.model.weathercurentday

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WindGustCurrent(
    @SerializedName("Speed")
    @Expose
    val speed: SpeedCurrent = SpeedCurrent()
)