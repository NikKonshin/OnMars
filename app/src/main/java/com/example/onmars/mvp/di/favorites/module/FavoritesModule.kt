package com.example.onmars.mvp.di.favorites.module

import com.example.onmars.mvp.di.favorites.FavoritesScope
import com.example.onmars.mvp.model.cache.IFavoritesPhotoCache
import com.example.onmars.mvp.model.cache.room.RoomFavoritesPhotoCache
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.entity.scroll.ISaveScroll
import com.example.onmars.mvp.model.entity.scroll.SaveScroll
import dagger.Module
import dagger.Provides

@Module
class FavoritesModule {

    @FavoritesScope
    @Provides
    fun position(): ISaveScroll = SaveScroll()

    @FavoritesScope
    @Provides
    fun favoritesCache(database: Database): IFavoritesPhotoCache =
        RoomFavoritesPhotoCache(database)
}