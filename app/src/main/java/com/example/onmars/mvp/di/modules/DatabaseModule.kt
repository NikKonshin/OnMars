package com.example.onmars.mvp.di.modules

import androidx.room.Room
import com.example.onmars.mvp.App
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
}