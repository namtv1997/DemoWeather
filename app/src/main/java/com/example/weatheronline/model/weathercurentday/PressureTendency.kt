package com.example.weatheronline.model.weathercurentday

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PressureTendency(
    @SerializedName("LocalizedText")
    @Expose
    val localizedText: String? = null,
    
    @SerializedName("Code")
    @Expose
    val code: String? = null


)