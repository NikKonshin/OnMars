package com.example.onmars.mvp.model.repo

import com.example.onmars.mvp.model.entity.Rovers
import io.reactivex.rxjava3.core.Single

interface IRoversRepo {
    fun getRovers(): Single<Rovers>
}