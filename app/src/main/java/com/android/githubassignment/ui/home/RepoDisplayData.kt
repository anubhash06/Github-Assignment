package com.android.githubassignment.ui.home

import android.os.Parcel
import android.os.Parcelable

class RepoDisplayData(val name : String,
                           val number : Int,
                           val description : String,
                           val userName : String,
                           val userImage : String ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(number)
        parcel.writeString(description)
        parcel.writeString(userName)
        parcel.writeString(userImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoDisplayData> {
        override fun createFromParcel(parcel: Parcel): RepoDisplayData {
            return RepoDisplayData(parcel)
        }

        override fun newArray(size: Int): Array<RepoDisplayData?> {
            return arrayOfNulls(size)
        }
    }
}