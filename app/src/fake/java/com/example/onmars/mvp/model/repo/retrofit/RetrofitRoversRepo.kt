package com.example.onmars.mvp.model.repo.retrofit

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IRoversCache
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.Rovers
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IRoversRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRoversRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val roversCache: IRoversCache
) : IRoversRepo {
    override fun getRovers() =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            getAllRovers()
        }.subscribeOn(Schedulers.io())

   private fun getAllRovers(): Single<Rovers> = Single.fromCallable {
           val camera = Camera(
                0,
                "",
                0,
                ""
            )
       val cameras = listOf<Camera>(camera)

       val rovers = listOf<Rover>(
           Rover(0,"curiosity","2021-05-07","2020-05-07","",0,"2021-05-07",0,cameras),
           Rover(0,"spirit","2021-05-07","2020-05-07","",0,"2021-05-07",0,cameras),
           Rover(0,"opportunity","2021-05-07","2020-05-07","",0,"2021-05-07",0,cameras),
           Rover(0,"perseverance","2021-05-07","2020-05-07","",0,"2021-05-07",0,cameras)
       )

        Rovers(
            rovers
        )

    }.subscribeOn(Schedulers.io())
}