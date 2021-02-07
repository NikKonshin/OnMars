package com.example.onmars.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rover(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val landingDate: String,
    @Expose val launchDate: String,
    @Expose val status: String,
    @Expose val maxSol: Int,
    @Expose val maxDate: String,
    @Expose val totalPhotos: Int,
    @Expose val cameras: List<Camera>,
) : Parcelable