package com.example.onmars.mvp.di.modules

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoversRepo
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
}