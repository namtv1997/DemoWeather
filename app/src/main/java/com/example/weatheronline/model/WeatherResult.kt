package com.example.weatheronline.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherResult(
    @SerializedName("cod")
    @Expose
    val cod: String? = null,

    @SerializedName("message")
    @Expose
    val message: Double? = null,

    @SerializedName("cnt")
    @Expose
    private val cnt: Int? = null,

    @SerializedName("list")
    @Expose
    val list: List<ListWeatherInfor>? = null,

    @SerializedName("city")
    @Expose
    val city: City = City()

): Serializable




