package com.example.onmars.mvp.model.entity.room.dao

import androidx.room.*
import com.example.onmars.mvp.model.entity.room.RoomRover

@Dao
interface RoverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rover: RoomRover)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg rover: RoomRover)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rovers: List<RoomRover>)

    @Update
    fun update(rover: RoomRover)

    @Update
    fun update(vararg rover: RoomRover)

    @Update
    fun update(rovers: List<RoomRover>)

    @Delete
    fun delete(rover: RoomRover)

    @Delete
    fun delete(vararg rover: RoomRover)

    @Delete
    fun delete(rovers: List<RoomRover>)

    @Query("SELECT * FROM RoomRover")
    fun getAll(): List<RoomRover>

    @Query("SELECT * FROM RoomRover WHERE name = :name LIMIT 1")
    fun findByName(name: String): RoomRover
}