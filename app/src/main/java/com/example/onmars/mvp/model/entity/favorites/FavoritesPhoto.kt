package com.example.onmars.mvp.model.entity.favorites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoritesPhoto(
    val id: Int,
    val uri: String,
    val date: String,
    val roverName: String,
    val cameraName: String,
    var isFavorites: Boolean
) : Parcelable