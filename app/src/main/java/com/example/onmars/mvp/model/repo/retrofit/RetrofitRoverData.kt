package com.example.onmars.mvp.model.repo.retrofit

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoverData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRoverData(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
) : IRoverData{
    override fun getRoverData(roverName: String): Single<Rover> =
        api.getRover(roverName).subscribeOn(Schedulers.io())

}