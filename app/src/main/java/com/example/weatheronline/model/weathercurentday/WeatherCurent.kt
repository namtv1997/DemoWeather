package com.example.weatheronline.model.weathercurentday

import com.example.weatheronline.model.weatherresult.Wind
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class WeatherCurent:Serializable{
    @SerializedName("LocalObservationDateTime")
    @Expose
    val localObservationDateTime: String? = null

    @SerializedName("EpochTime")
    @Expose
    val epochTime: Int? = null

    @SerializedName("WeatherText")
    @Expose
    val weatherText: String? = null

    @SerializedName("WeatherIcon")
    @Expose
    val weatherIcon: Int? = null

    @SerializedName("HasPrecipitation")
    @Expose
    val hasPrecipitation: Boolean = false

    @SerializedName("PrecipitationType")
    @Expose
    val precipitationType: Objects? = null

    @SerializedName("IsDayTime")
    @Expose
    val isDayTime: Boolean = false

    @SerializedName("Temperature")
    @Expose
    val temperature: TemperatureCurrent = TemperatureCurrent()

    @SerializedName("RealFeelTemperature")
    @Expose
    val realFeelTemperature: RealFeelTemperatureCurrent = RealFeelTemperatureCurrent()
    @SerializedName("RealFeelTemperatureShade")
    @Expose
    val realFeelTemperatureShade: RealFeelTemperatureShadeCurrent = RealFeelTemperatureShadeCurrent()

    @SerializedName("RelativeHumidity")
    @Expose
    val relativeHumidity: Int? = null

    @SerializedName("DewPoint")
    @Expose
    val dewPoint: DewPoint = DewPoint()

    @SerializedName("Wind")
    @Expose
    val wind: Wind = Wind()
    @SerializedName("WindGust")
    @Expose
    val windGust: WindGustCurrent = WindGustCurrent()

    @SerializedName("UVIndex")
    @Expose
    val uVIndex: Int? = null

    @SerializedName("UVIndexText")
    @Expose
    val uVIndexText: String? = null

    @SerializedName("Visibility")
    @Expose
    val visibility: Visibility = Visibility()

    @SerializedName("ObstructionsToVisibility")
    @Expose
    val obstructionsToVisibility: String? = null

    @SerializedName("CloudCover")
    @Expose
    val cloudCover: Int? = null

    @SerializedName("Ceiling")
    @Expose
    val ceiling: Ceiling = Ceiling()

    @SerializedName("Pressure")
    @Expose
    val pressure: Pressure = Pressure()

    @SerializedName("PressureTendency")
    @Expose
    val pressureTendency: PressureTendency = PressureTendency()

    @SerializedName("Past24HourTemperatureDeparture")
    @Expose
    val past24HourTemperatureDeparture: Past24HourTemperatureDeparture = Past24HourTemperatureDeparture()

    @SerializedName("ApparentTemperature")
    @Expose
    val apparentTemperature: ApparentTemperature = ApparentTemperature()

    @SerializedName("WindChillTemperature")
    @Expose
    val windChillTemperature: WindChillTemperature = WindChillTemperature()

    @SerializedName("WetBulbTemperature")
    @Expose
    val wetBulbTemperature: WetBulbTemperature = WetBulbTemperature()

    @SerializedName("Precip1hr")
    @Expose
    val precip1hr: Precip1hr = Precip1hr()

    @SerializedName("PrecipitationSummary")
    @Expose
    val precipitationSummary: PrecipitationSummary = PrecipitationSummary()

    @SerializedName("TemperatureSummary")
    @Expose
    val temperatureSummary: TemperatureSummary = TemperatureSummary()
    @SerializedName("MobileLink")
    @Expose
    val mobileLink: String? = null

    @SerializedName("Link")
    @Expose
    val link: String? = null
}