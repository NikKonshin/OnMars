package com.example.onmars.mvp.model.cache

import com.example.onmars.mvp.model.entity.Rover
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoverCache {
    fun insert(rover: Rover): Completable
    fun getRover(roverName: String): Single<Rover>
}