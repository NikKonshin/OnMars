package com.example.onmars.mvp.model.repo.retrofit

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IRoversCache
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoversRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRoversRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val roversCache: IRoversCache
) : IRoversRepo {
    override fun getRovers() =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getRovers().flatMap {
                    roversCache.insert(it).toSingleDefault(it)
                }
            } else {
                roversCache.getAllRovers()
            }
        }.subscribeOn(Schedulers.io())
}