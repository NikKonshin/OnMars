package com.example.onmars.mvp.model.entity.room.dao

import androidx.room.*
import com.example.onmars.mvp.model.entity.room.RoomPhoto

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: RoomPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photos: RoomPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<RoomPhoto>)

    @Update
    fun update(photo: RoomPhoto)

    @Update
    fun update(vararg photo: RoomPhoto)

    @Update
    fun update(photos: List<RoomPhoto>)

    @Delete
    fun delete(photo: RoomPhoto)

    @Delete
    fun delete(vararg photo: RoomPhoto)

    @Delete
    fun delete(photos: List<RoomPhoto>)

    @Query("SELECT * FROM RoomPhoto WHERE date = :date AND cameraId = :cameraId")
    fun getAll(date: String, cameraId: Int): List<RoomPhoto>

    @Query("SELECT * FROM RoomPhoto WHERE id = :id")
    fun findByRoverId(id: Int): List<RoomPhoto>
}
