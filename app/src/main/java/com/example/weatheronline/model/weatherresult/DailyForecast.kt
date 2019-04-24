package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DailyForecast(
    @SerializedName("Date")
    @Expose
    val date: String? = null,

    @SerializedName("EpochDate")
    @Expose
    val epochDate: Int? = null,

    @SerializedName("Sun")
    @Expose
    val sun: Sun = Sun(),

    @SerializedName("Moon")
    @Expose
    val moon: Moon = Moon(),

    @SerializedName("Temperature")
    @Expose
    val temperature: Temperature = Temperature(),

    @SerializedName("RealFeelTemperature")
    @Expose
    val realFeelTemperature: RealFeelTemperature = RealFeelTemperature(),

    @SerializedName("RealFeelTemperatureShade")
    @Expose
    val realFeelTemperatureShade: RealFeelTemperatureShade = RealFeelTemperatureShade(),

    @SerializedName("HoursOfSun")
    @Expose
    val hoursOfSun: Double? = null,

    @SerializedName("DegreeDaySummary")
    @Expose
    val degreeDaySummary: DegreeDaySummary = DegreeDaySummary(),

    @SerializedName("AirAndPollen")
    @Expose
    val airAndPollen: List<AirAndPollen>? = null,

    @SerializedName("Day")
    @Expose
    val day: Day = Day(),
    @SerializedName("Night")
    @Expose
    val night:Night=Night(),

    @SerializedName("Sources")
    @Expose
    val sources:List<String>? = null,

    @SerializedName("MobileLink")
    @Expose
    val mobileLink: String? = null,

    @SerializedName("Link")
    @Expose
    val link: String? = null
)