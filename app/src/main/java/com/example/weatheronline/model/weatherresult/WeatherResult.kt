package com.example.weatheronline.model.weatherresult

import com.example.weatheronline.model.weatherresult.DailyForecast
import com.example.weatheronline.model.weatherresult.Headline
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherResult(


    @SerializedName("Headline")
    @Expose
    val Headline: Headline = Headline(),

    @SerializedName("DailyForecasts")
    @Expose
    val DailyForecasts: List<DailyForecast>? = null

) : Serializable




