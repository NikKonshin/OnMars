package com.example.onmars.mvp.model.repo.retrofit

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoversRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRoversRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus
) : IRoversRepo {
    override fun getRovers() =
        api.getRovers().subscribeOn(Schedulers.io())
}