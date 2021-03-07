package com.example.onmars.mvp.model.cache.room

import com.example.onmars.mvp.model.cache.IFavoritesPhotoCache
import com.example.onmars.mvp.model.entity.favorites.FavoritesPhoto
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.entity.room.RoomPhotoFavorites
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomFavoritesPhotoCache(private val db: Database) : IFavoritesPhotoCache {
    override fun insert(
        photo: FavoritesPhoto,
    ): Completable = Completable.fromAction {
        val roomPhotoFavorites = photo.let {
            RoomPhotoFavorites(
                it.id,
                it.uri,
                it.date,
                it.roverName,
                it.cameraName,
                it.isFavorites
            )
        }
        db.favoritesPhotoDao.insert(roomPhotoFavorites)
    }.subscribeOn(Schedulers.io())

    override fun delete(photo: FavoritesPhoto): Completable = Completable.fromAction {
        val roomFavoritesPhoto = photo.let {
            RoomPhotoFavorites(
                it.id,
                it.uri,
                it.date,
                it.roverName,
                it.cameraName,
                it.isFavorites
            )
        }
        db.favoritesPhotoDao.delete(roomFavoritesPhoto)
    }.subscribeOn(Schedulers.io())

    override fun getAllFavoritesPhoto(): Single<List<FavoritesPhoto>> = Single.fromCallable {
        db.favoritesPhotoDao.getAllPhoto().map {
            FavoritesPhoto(
                it.id,
                it.uri,
                it.date,
                it.roverName,
                it.cameraName,
                it.isFavorites
            )
        }
    }.subscribeOn(Schedulers.io())
}