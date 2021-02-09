package com.example.onmars.mvp.model.repo

import com.example.onmars.mvp.model.entity.Rover
import io.reactivex.rxjava3.core.Single

interface IRoverData {
    fun getRoverData(roverName: String): Single<Rover>
}