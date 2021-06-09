package com.example.onmars.mvp.di.photos.module

import com.example.onmars.mvp.di.photos.PhotosScope
import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IPhotoCache
import com.example.onmars.mvp.model.cache.room.RoomPhotoCache
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.entity.scroll.ISaveScroll
import com.example.onmars.mvp.model.entity.scroll.SaveScroll
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IPhotoRepo
import com.example.onmars.mvp.model.repo.retrofit.RetrofitPhotoFromCamera
import dagger.Module
import dagger.Provides

@Module
class PhotosModule {
    @PhotosScope
    @Provides
    fun photoCache(database: Database): IPhotoCache =
        RoomPhotoCache(database)

    @PhotosScope
    @Provides
    fun photosRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IPhotoCache
    ): IPhotoRepo = RetrofitPhotoFromCamera(api, networkStatus, cache)

    @PhotosScope
    @Provides
    fun position(): ISaveScroll = SaveScroll()
}