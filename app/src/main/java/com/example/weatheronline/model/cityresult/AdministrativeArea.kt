package com.example.weatheronline.model.cityresult

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class AdministrativeArea(
    @SerializedName("ID")
    @Expose
    val id: String? = null,

    @SerializedName("LocalizedName")
    @Expose
    val localizedName: String? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(localizedName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AdministrativeArea> {
        override fun createFromParcel(parcel: Parcel): AdministrativeArea {
            return AdministrativeArea(parcel)
        }

        override fun newArray(size: Int): Array<AdministrativeArea?> {
            return arrayOfNulls(size)
        }
    }
}