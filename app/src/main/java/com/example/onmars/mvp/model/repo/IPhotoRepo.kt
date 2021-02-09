package com.example.onmars.mvp.model.repo

import com.example.onmars.mvp.model.entity.Photos
import io.reactivex.rxjava3.core.Single

interface IPhotoRepo {
    fun getPhotosFromCameraDate(cameraName: String,date: String, roverName: String): Single<Photos>
}