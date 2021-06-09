package com.example.onmars.mvp.model.cache

import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Photo
import com.example.onmars.mvp.model.entity.Photos
import com.example.onmars.mvp.model.entity.Rover
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IPhotoCache {
    fun insert(photos: Photos, camera: Camera, date: String): Completable
    fun getAllPhotoFromCamera(rover: Rover, camera: Camera, date: String): Single<Photos>
}