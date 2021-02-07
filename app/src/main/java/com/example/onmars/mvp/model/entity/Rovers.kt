package com.example.onmars.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rovers(
    @Expose val rovers: List<Rover>
): Parcelable