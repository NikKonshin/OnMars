package com.example.onmars.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomRover(
    @PrimaryKey val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String,
    val maxSol: Int,
    val maxDate: String,
    val totalPhotos: Int,
)