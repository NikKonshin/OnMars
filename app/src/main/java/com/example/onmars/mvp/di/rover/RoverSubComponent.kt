package com.example.onmars.mvp.di.rover

import com.example.onmars.mvp.di.photos.PhotosSubComponent
import com.example.onmars.mvp.di.rover.module.RoverModule
import com.example.onmars.mvp.presenter.RoverPresenter
import com.example.onmars.mvp.ui.adapter.CamerasRVAdapter
import dagger.Subcomponent

@RoverScope
@Subcomponent(
    modules = [
        RoverModule::class
    ]
)
interface RoverSubComponent {
    fun photosSubcomponent(): PhotosSubComponent
    fun inject(roverPresenter: RoverPresenter)
    fun inject(camerasRVAdapter: CamerasRVAdapter)
}