package com.example.weatheronline.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class City {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("coord")
    @Expose
    val coord: Coord? = null

    @SerializedName("country")
    @Expose
     val country: String? = null
}