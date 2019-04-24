package com.example.weatheronline.model.weathercurentday

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PrecipitationSummary(
    @SerializedName("Precipitation")
    @Expose
    val precipitation: Precipitation = Precipitation(),

    @SerializedName("PastHour")
    @Expose
    val pastHour: PastHour = PastHour(),

    @SerializedName("Past3Hours")
    @Expose
    val past3Hours: Past3Hours = Past3Hours(),

    @SerializedName("Past6Hours")
    @Expose
    val past6Hours: Past6Hours = Past6Hours(),

    @SerializedName("Past9Hours")
    @Expose
    val past9Hours: Past9Hours = Past9Hours(),

    @SerializedName("Past12Hours")
    @Expose
    val past12Hours: Past12Hours = Past12Hours(),

    @SerializedName("Past18Hours")
    @Expose
    val past18Hours: Past18Hours = Past18Hours(),

    @SerializedName("Past24Hours")
    @Expose
    val past24Hours: Past24Hours = Past24Hours()
)