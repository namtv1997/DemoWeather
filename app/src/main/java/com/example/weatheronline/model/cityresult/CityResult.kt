package com.example.weatheronline.model.cityresult

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityResult(
    @SerializedName("Version")
    @Expose
    val version: Int? = null,

    @SerializedName("Key")
    @Expose
    val key: String? = null,

    @SerializedName("Type")
    @Expose
    val type: String? = null,

    @SerializedName("Rank")
    @Expose
    val rank: Int? = null,

    @SerializedName("LocalizedName")
    @Expose
    val localizedName: String? = null,

    @SerializedName("Country")
    @Expose
    val country: Country = Country(),

    @SerializedName("AdministrativeArea")
    @Expose
    val administrativeArea: AdministrativeArea = AdministrativeArea()
)