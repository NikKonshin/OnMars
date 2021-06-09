package com.example.onmars.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Camera(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val roverId: Int,
    @Expose val fullName: String,
) : Parcelable
