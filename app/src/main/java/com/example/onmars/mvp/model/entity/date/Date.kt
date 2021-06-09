package com.example.onmars.mvp.model.entity.date

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Date(
    var year: Int = 0,
    var month: Int = 0,
    var day: Int = 0,
    var dateString: String = ""
) : Parcelable