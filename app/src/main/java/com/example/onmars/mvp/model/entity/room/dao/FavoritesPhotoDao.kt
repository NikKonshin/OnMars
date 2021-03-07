package com.example.onmars.mvp.model.entity.room.dao

import androidx.room.*
import com.example.onmars.mvp.model.entity.room.RoomPhotoFavorites

@Dao
interface FavoritesPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: RoomPhotoFavorites)

    @Delete
    fun delete(photo: RoomPhotoFavorites)

    @Query("SELECT * FROM RoomPhotoFavorites")
    fun getAllPhoto(): List<RoomPhotoFavorites>
}