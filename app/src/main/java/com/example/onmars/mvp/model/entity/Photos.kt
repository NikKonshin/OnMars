package com.example.onmars.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos(
    @Expose val photos: List<Photo>
): Parcelable