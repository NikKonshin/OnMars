package com.example.onmars.mvp.model.repo.retrofit

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IRoverCache
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoverData
import io.reactivex.rxjava3.core.Single

class RetrofitRoverData(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val roversCache: IRoverCache
) : IRoverData {
    override fun getRoverData(roverName: String): Single<Rover> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                roverName.let { name ->
                    api.getRover(name).flatMap {
                        roversCache.insert(it).toSingleDefault(it)
                    }
                }
            } else {
                roversCache.getRover(roverName)
            }
        }
}