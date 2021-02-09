package com.example.onmars.mvp.model.repo.retrofit

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.entity.Photos
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IPhotoRepo
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitPhotoFromCamera(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
) : IPhotoRepo {
//    override fun getPhotosFromCameraDate(cameraName: String, roverName: String): Single<Photos> =
//        api.getPhotoFromCameraDate(roverName, cameraName).subscribeOn(Schedulers.io())

    override fun getPhotosFromCameraDate(
        cameraName: String,
        date: String,
        roverName: String
    ): Single<Photos> =
        api.getPhotoFromCameraDate(roverName,date,cameraName).subscribeOn(Schedulers.io())


}