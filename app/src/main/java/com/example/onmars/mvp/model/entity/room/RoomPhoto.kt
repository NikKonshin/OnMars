package com.example.onmars.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
   foreignKeys =  [ForeignKey(
        entity = RoomCamera::class,
        parentColumns = ["id"],
        childColumns = ["cameraId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomPhoto(
    @PrimaryKey val id: Int,
    val sol: Int,
    val imgSrc: String,
    val cameraId: Int,
    val date: String
)