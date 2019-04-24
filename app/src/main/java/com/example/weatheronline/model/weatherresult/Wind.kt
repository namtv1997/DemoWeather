package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind(

    @SerializedName("Speed")
    @Expose
    val speed: Speed = Speed(),

    @SerializedName("Direction")
    @Expose
    val direction: Direction = Direction()
)