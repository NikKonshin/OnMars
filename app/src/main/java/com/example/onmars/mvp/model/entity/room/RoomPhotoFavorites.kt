package com.example.onmars.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomPhotoFavorites(
    @PrimaryKey val id: Int,
    val uri: String,
    val date: String,
    val roverName: String,
    val cameraName: String,
    val isFavorites: Boolean
)