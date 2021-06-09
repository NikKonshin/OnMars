package com.example.onmars.mvp.model.cache

import com.example.onmars.mvp.model.entity.Rovers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoversCache {
    fun insert(rovers: Rovers): Completable
    fun getAllRovers(): Single<Rovers>
}