package com.example.onmars.mvp.di.photo.module

import android.widget.ImageView
import com.example.onmars.mvp.App
import com.example.onmars.mvp.di.photo.PhotoScope
import com.example.onmars.mvp.model.cache.IFavoritesPhotoCache
import com.example.onmars.mvp.model.cache.room.RoomFavoritesPhotoCache
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.image.IImageLoader
import com.example.onmars.mvp.model.image.ISaveImage
import com.example.onmars.mvp.model.image.SaveImage
import dagger.Module
import dagger.Provides

@Module
class PhotoModule {
    @Provides
    fun favoritesCache(database: Database): IFavoritesPhotoCache =
        RoomFavoritesPhotoCache(database)

    @PhotoScope
    @Provides
    fun saveImage(il: IImageLoader<ImageView>, app: App): ISaveImage = SaveImage(il, app)
}