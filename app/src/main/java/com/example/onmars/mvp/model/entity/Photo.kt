package com.example.onmars.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo (
    @Expose val id: Int,
    @Expose val sol: Int,
    @Expose val camera: Camera,
    @Expose val imgSrc: String,
    @Expose val rover: Rover,
):Parcelable