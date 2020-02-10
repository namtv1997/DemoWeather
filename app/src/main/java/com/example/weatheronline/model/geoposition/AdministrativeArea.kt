package com.example.weatheronline.model.geoposition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class AdministrativeArea {
    @SerializedName("ID")
    @Expose
     val iD: String? = null
    @SerializedName("LocalizedName")
    @Expose
     val localizedName: String? = null
    @SerializedName("EnglishName")
    @Expose
     val englishName: String? = null
    @SerializedName("Level")
    @Expose
     val level: Int? = null
    @SerializedName("LocalizedType")
    @Expose
     val localizedType: String? = null
    @SerializedName("EnglishType")
    @Expose
     val englishType: String? = null
    @SerializedName("CountryID")
    @Expose
     val countryID: String? = null
}