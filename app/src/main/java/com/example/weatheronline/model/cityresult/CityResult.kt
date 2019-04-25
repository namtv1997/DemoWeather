package com.example.weatheronline.model.cityresult

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityResult(
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

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readParcelable(Country::class.java.classLoader)!!,
        parcel.readParcelable(AdministrativeArea::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(version)
        parcel.writeString(key)
        parcel.writeString(type)
        parcel.writeValue(rank)
        parcel.writeString(localizedName)
        parcel.writeParcelable(country, flags)
        parcel.writeParcelable(administrativeArea, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityResult> {
        override fun createFromParcel(parcel: Parcel): CityResult {
            return CityResult(parcel)
        }

        override fun newArray(size: Int): Array<CityResult?> {
            return arrayOfNulls(size)
        }
    }
}