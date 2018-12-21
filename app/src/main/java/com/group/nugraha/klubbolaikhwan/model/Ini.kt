package com.group.nugraha.klubbolaikhwan.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ini (
    val namaklub: String, val logoklub: Int, val keteranganklub: String
) : Parcelable
