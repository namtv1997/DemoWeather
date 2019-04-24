package com.example.weatheronline.model.weatherresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Headline(
    @SerializedName("EffectiveDate")
    @Expose
    val effectiveDate: String? = null,

    @SerializedName("EffectiveEpochDate")
    @Expose
    val effectiveEpochDate: Int? = null,

    @SerializedName("Severity")
    @Expose
    val severity: Int? = null,

    @SerializedName("Text")
    @Expose
    val text: String? = null,

    @SerializedName("Category")
    @Expose
    val category: String? = null,

    @SerializedName("EndDate")
    @Expose
    val endDate: String? = null,

    @SerializedName("EndEpochDate")
    @Expose
    val endEpochDate: Int? = null,

    @SerializedName("MobileLink")
    @Expose
    val mobileLink: String? = null,

    @SerializedName("Link")
    @Expose
    val link: String? = null
)