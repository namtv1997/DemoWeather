package com.example.weatheronline.model.geoposition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GeoPositionSearch {
    @SerializedName("Version")
    @Expose
     val version: Int? = null
    @SerializedName("Key")
    @Expose
     val key: String? = null
    @SerializedName("Type")
    @Expose
     val type: String? = null
    @SerializedName("Rank")
    @Expose
     val rank: Int? = null
    @SerializedName("LocalizedName")
    @Expose
     val localizedName: String? = null
    @SerializedName("EnglishName")
    @Expose
     val englishName: String? = null
    @SerializedName("PrimaryPostalCode")
    @Expose
     val primaryPostalCode: String? = null
    @SerializedName("Region")
    @Expose
     val region: Region? = null
    @SerializedName("Country")
    @Expose
     val country: Country? = null
    @SerializedName("AdministrativeArea")
    @Expose
     val administrativeArea: AdministrativeArea? = null
    @SerializedName("TimeZone")
    @Expose
     val timeZone: TimeZone? = null
    @SerializedName("GeoPosition")
    @Expose
     val geoPosition: GeoPosition? = null
    @SerializedName("IsAlias")
    @Expose
     val isAlias: Boolean? = null
    @SerializedName("SupplementalAdminAreas")
    @Expose
     val supplementalAdminAreas: List<SupplementalAdminArea>? = null
    @SerializedName("DataSets")
    @Expose
     val dataSets: List<String>? = null

}