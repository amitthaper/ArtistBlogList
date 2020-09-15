package com.sample.jet2ttinterview.api.models

import android.os.Parcel
import android.os.Parcelable

data class Media(
    val _id: String?,
    val blogId: String?,
    var createdAt: String?,
    var image: String?,
    var title: String?,
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let { parcel ->
            {
                with(parcel) {
                    writeString(_id)
                    writeString(blogId)
                    writeString(createdAt)
                    writeString(image)
                    writeString(title)
                    writeString(url)
                }
            }
        }

    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Media> {
        override fun createFromParcel(parcel: Parcel): Media {
            return Media(parcel)
        }

        override fun newArray(size: Int): Array<Media?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is String) {
            return this._id == other
        } else if (other is Media) {
            return this._id == other._id
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}