package com.example.onmars.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomRover::class,
        parentColumns = ["id"],
        childColumns = ["roverId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomCamera(
    @PrimaryKey val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String,
)