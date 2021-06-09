package com.example.onmars.mvp.di.rovers.module

import com.example.onmars.mvp.di.rovers.RoversScope
import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IRoversCache
import com.example.onmars.mvp.model.cache.room.RoomRoversCache
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoversRepo
import com.example.onmars.mvp.model.repo.retrofit.RetrofitRoversRepo
import dagger.Module
import dagger.Provides

@Module
class RoversModule {

    @RoversScope
    @Provides
    fun roversCache(database: Database): IRoversCache =
        RoomRoversCache(database)

    @RoversScope
    @Provides
    fun roversRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoversCache
    ): IRoversRepo = RetrofitRoversRepo(api, networkStatus, cache)
}