package com.example.onmars.mvp.di.modules

import com.example.onmars.mvp.model.api.IDataSource
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
        networkStatus: INetworkStatus
    ): IRoversRepo = RetrofitRoversRepo(api, networkStatus)

    @Singleton
    @Provides
    fun roverRepo(
        api: IDataSource,
        networkStatus: INetworkStatus
    ): IRoverData = RetrofitRoverData(api, networkStatus)

    @Singleton
    @Provides
    fun photosRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
    ): IPhotoRepo = RetrofitPhotoFromCamera(api, networkStatus)
}