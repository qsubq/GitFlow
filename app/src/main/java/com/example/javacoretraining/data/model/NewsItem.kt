package com.example.javacoretraining.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("imgId")
    val imgId: Int,

    @SerializedName("category")
    val category: Int,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("date")
    val date: String?,

    @SerializedName("fond")
    val fond: String?,

    @SerializedName("place")
    val place: String?,

    @SerializedName("numbers")
    val numbers: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(imgId)
        parcel.writeInt(category)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(date)
        parcel.writeString(fond)
        parcel.writeString(place)
        parcel.writeString(numbers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel): NewsItem {
            return NewsItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsItem?> {
            return arrayOfNulls(size)
        }
    }
}
