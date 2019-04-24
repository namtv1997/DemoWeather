package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Night(
    @SerializedName("Icon")
    @Expose
    val icon: Int? = null,

    @SerializedName("IconPhrase")
    @Expose
    val iconPhrase: String? = null,

    @SerializedName("ShortPhrase")
    @Expose
    val shortPhrase: String? = null,

    @SerializedName("LongPhrase")
    @Expose
    val longPhrase: String? = null,

    @SerializedName("PrecipitationProbability")
    @Expose
    val precipitationProbability: Int? = null,

    @SerializedName("ThunderstormProbability")
    @Expose
    val thunderstormProbability: Int? = null,

    @SerializedName("RainProbability")
    @Expose
    val rainProbability: Int? = null,

    @SerializedName("SnowProbability")
    @Expose
    val snowProbability: Int? = null,

    @SerializedName("IceProbability")
    @Expose
    val iceProbability: Int? = null,

    @SerializedName("Wind")
    @Expose
    val wind: Wind = Wind(),
    @SerializedName("WindGust")
    @Expose
    val windGust: WindGust = WindGust(),
    @SerializedName("TotalLiquid")
    @Expose
    val totalLiquid: TotalLiquid = TotalLiquid(),

    @SerializedName("Rain")
    @Expose
    val rain: Rain = Rain(),
    @SerializedName("Snow")
    @Expose
    val snow: Snow = Snow(),
    @SerializedName("Ice")
    @Expose
    val ice: Ice = Ice(),

    @SerializedName("HoursOfPrecipitation")
    @Expose
    val hoursOfPrecipitation: Int? = null,

    @SerializedName("HoursOfRain")
    @Expose
    val hoursOfRain: Int? = null,

    @SerializedName("HoursOfSnow")
    @Expose
    val hoursOfSnow: Int? = null,

    @SerializedName("HoursOfIce")
    @Expose
    val hoursOfIce: Int? = null,

    @SerializedName("CloudCover")
    @Expose
    val cloudCover: Int? = null
)