package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DegreeDaySummary(

    @SerializedName("Heating")
    @Expose
    val heating: Heating = Heating(),

    @SerializedName("Cooling")
    @Expose
    val cooling:Cooling=Cooling()
)