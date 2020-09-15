package com.sample.jet2ttinterview.api.models

import android.os.Parcel
import android.os.Parcelable

data class User(
    val _id: String?,
    val blogId: String?,
    var createdAt: String?,
    var avatar: String?,
    var name: String?,
    var lastname: String?,
    var city: String?,
    var designation: String?,
    var about: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
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
                    writeString(avatar)
                    writeString(name)
                    writeString(lastname)
                    writeString(city)
                    writeString(designation)
                    writeString(about)
                }
            }
        }

    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is String) {
            return this._id == other
        } else if (other is User) {
            return this._id == other._id
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}