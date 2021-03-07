package com.example.onmars.mvp.model.cache.room

import com.example.onmars.mvp.model.cache.IPhotoCache
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Photo
import com.example.onmars.mvp.model.entity.Photos
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.entity.room.RoomPhoto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomPhotoCache(private val db: Database) : IPhotoCache {
    override fun insert(photos: Photos, camera: Camera, date: String): Completable =
        Completable.fromAction {
            val roomPhoto = photos.photos.map {
                RoomPhoto(
                    it.id,
                    it.sol,
                    it.imgSrc,
                    camera.id,
                    date
                )
            }
            db.photoDao.insert(roomPhoto)
        }.subscribeOn(Schedulers.io())

    override fun getAllPhotoFromCamera(rover: Rover, camera: Camera, date: String): Single<Photos> =
        Single.fromCallable {
            Photos(
                db.photoDao.getAll(date, camera.id).map {
                    Photo(
                        it.id,
                        it.sol,
                        camera,
                        it.imgSrc,
                        rover
                    )
                })
        }.subscribeOn(Schedulers.io())
}