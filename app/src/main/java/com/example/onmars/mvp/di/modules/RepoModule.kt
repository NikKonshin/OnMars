package com.example.onmars.mvp.di.modules

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IPhotoCache
import com.example.onmars.mvp.model.cache.IRoverCache
import com.example.onmars.mvp.model.cache.IRoversCache
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IPhotoRepo
import com.example.onmars.mvp.model.repo.IRoverData
import com.example.onmars.mvp.model.repo.IRoversRepo
import com.example.onmars.mvp.model.repo.retrofit.RetrofitPhotoFromCamera
import com.example.onmars.mvp.model.repo.retrofit.RetrofitRoverData
import com.example.onmars.mvp.model.repo.retrofit.RetrofitRoversRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun roversRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoversCache
    ): IRoversRepo = RetrofitRoversRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun roverRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoverCache
    ): IRoverData = RetrofitRoverData(api, networkStatus, cache)

    @Singleton
    @Provides
    fun photosRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IPhotoCache
    ): IPhotoRepo = RetrofitPhotoFromCamera(api, networkStatus, cache)
}