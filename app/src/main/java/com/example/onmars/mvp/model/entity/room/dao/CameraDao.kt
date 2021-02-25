package com.example.onmars.mvp.model.entity.room.dao

import androidx.room.*
import com.example.onmars.mvp.model.entity.room.RoomCamera

@Dao
interface CameraDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(camera: RoomCamera)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg camera: RoomCamera)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cameras: List<RoomCamera>)

    @Update
    fun update(camera: RoomCamera)

    @Update
    fun update(vararg camera: RoomCamera)

    @Update
    fun update(cameras: List<RoomCamera>)

    @Delete
    fun delete(camera: RoomCamera)

    @Delete
    fun delete(vararg camera: RoomCamera)

    @Delete
    fun delete(cameras: List<RoomCamera>)

    @Query("SELECT * FROM RoomCamera")
    fun getAll(): List<RoomCamera>

    @Query("SELECT * FROM RoomCamera WHERE roverId = :id")
    fun findByRoverId(id: Int): List<RoomCamera>
}