package com.example.onmars.mvp.model.entity.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onmars.mvp.model.entity.room.dao.CameraDao
import com.example.onmars.mvp.model.entity.room.dao.PhotoDao
import com.example.onmars.mvp.model.entity.room.dao.RoverDao
@Database(
    entities = [RoomRover::class, RoomPhoto::class, RoomCamera::class],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract val roverDao: RoverDao
    abstract val cameraDao: CameraDao
    abstract val photoDao: PhotoDao

    companion object{
        const val DB_NAME = "database.db"
    }
}