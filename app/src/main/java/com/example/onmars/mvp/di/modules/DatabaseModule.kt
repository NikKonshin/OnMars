package com.example.onmars.mvp.di.modules

import androidx.room.Room
import com.example.onmars.mvp.App
import com.example.onmars.mvp.model.cache.IPhotoCache
import com.example.onmars.mvp.model.cache.IRoverCache
import com.example.onmars.mvp.model.cache.IRoversCache
import com.example.onmars.mvp.model.cache.room.RoomPhotoCache
import com.example.onmars.mvp.model.cache.room.RoomRoverCache
import com.example.onmars.mvp.model.cache.room.RoomRoversCache
import com.example.onmars.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) =
        Room.databaseBuilder(
            app,
            Database::class.java,
            Database.DB_NAME
        ).build()

    @Singleton
    @Provides
    fun roverCache(database: Database): IRoverCache =
        RoomRoverCache(database)

    @Singleton
    @Provides
    fun roversCache(database: Database): IRoversCache =
        RoomRoversCache(database)

    @Singleton
    @Provides
    fun photoCache(database: Database): IPhotoCache =
        RoomPhotoCache(database)
}