package com.example.onmars.mvp.di.rover.module

import com.example.onmars.mvp.di.rover.RoverScope
import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IRoverCache
import com.example.onmars.mvp.model.cache.room.RoomRoverCache
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoverData
import com.example.onmars.mvp.model.repo.retrofit.RetrofitRoverData
import dagger.Module
import dagger.Provides

@Module
class RoverModule {
    @Provides
    fun roverCache(database: Database): IRoverCache =
        RoomRoverCache(database)

    @RoverScope
    @Provides
    fun roverRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoverCache
    ): IRoverData = RetrofitRoverData(api, networkStatus, cache)
}