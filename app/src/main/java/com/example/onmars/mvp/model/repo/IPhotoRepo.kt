package com.example.onmars.mvp.model.repo

import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Photos
import com.example.onmars.mvp.model.entity.Rover
import io.reactivex.rxjava3.core.Single

interface IPhotoRepo {
    fun getPhotosFromCameraDate(camera: Camera,date: String, rover: Rover): Single<Photos>
}