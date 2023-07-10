package com.example.javacoretraining.data.model.listModel

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @ColumnInfo
    val avatar: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val first_name: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val last_name: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatar)
        parcel.writeString(email)
        parcel.writeString(first_name)
        parcel.writeInt(id)
        parcel.writeString(last_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}
