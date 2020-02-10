package com.example.weatheronline.model.geoposition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class SupplementalAdminArea {
    @SerializedName("Level")
    @Expose
     val level: Int? = null
    @SerializedName("LocalizedName")
    @Expose
     val localizedName: String? = null
    @SerializedName("EnglishName")
    @Expose
     val englishName: String? = null
}