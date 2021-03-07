package com.example.onmars.mvp.model.image

import io.reactivex.rxjava3.core.Completable

interface ISaveImage {
    fun savePicture(
        url: String,
        date: String,
        roverName: String,
        cameraName: String,
        id: String
    ): Completable

    fun getPath(): String
    fun delete(): Completable
}