package com.example.onmars.mvp.model.cache

import com.example.onmars.mvp.model.entity.favorites.FavoritesPhoto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IFavoritesPhotoCache {
    fun insert(
        photo: FavoritesPhoto,
    ): Completable

    fun delete(photo: FavoritesPhoto): Completable

    fun getAllFavoritesPhoto(): Single<List<FavoritesPhoto>>
}