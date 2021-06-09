package com.example.onmars.mvp.model.repo.retrofit

import com.example.onmars.mvp.model.api.IDataSource
import com.example.onmars.mvp.model.cache.IPhotoCache
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Photos
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.network.INetworkStatus
import com.example.onmars.mvp.model.repo.IPhotoRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitPhotoFromCamera(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val repoPhoto: IPhotoCache
) : IPhotoRepo {
    override fun getPhotosFromCameraDate(
        camera: Camera,
        date: String,
        rover: Rover
    ): Single<Photos> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getPhotoFromCameraDate(rover.name, date, camera.name).flatMap { photos ->
                    repoPhoto.insert(photos, camera, date).toSingleDefault(photos)
                }
            } else {
                repoPhoto.getAllPhotoFromCamera(rover, camera, date)
            }
        }.subscribeOn(Schedulers.io())
}