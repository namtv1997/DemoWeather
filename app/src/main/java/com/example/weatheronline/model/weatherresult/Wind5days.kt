package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind5days (
    @SerializedName("Speed")
    @Expose
    val speed: SpeedDouble = SpeedDouble(),

    @SerializedName("Direction")
    @Expose
    val direction: Direction = Direction()
)