package com.example.weatheronline.model.geoposition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Metric {
    @SerializedName("Value")
    @Expose
     val value: Int? = null
    @SerializedName("Unit")
    @Expose
     val unit: String? = null
    @SerializedName("UnitType")
    @Expose
     val unitType: Int? = null
}