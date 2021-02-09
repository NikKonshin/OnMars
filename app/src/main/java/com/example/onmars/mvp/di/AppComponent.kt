package com.example.onmars.mvp.di

import com.example.onmars.MainActivity
import com.example.onmars.mvp.di.modules.*
import com.example.onmars.mvp.presenter.MainPresenter
import com.example.onmars.mvp.presenter.PhotosPresenter
import com.example.onmars.mvp.presenter.RoverPresenter
import com.example.onmars.mvp.presenter.RoversPresenter
import com.example.onmars.mvp.ui.adapter.CamerasRVAdapter
import com.example.onmars.mvp.ui.adapter.PhotosRVAdapter
import com.example.onmars.mvp.ui.adapter.RoversRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[
    ApiModule::class,
    AppModule::class,
    CiceroneModule::class,
    RepoModule::class,
    ImageLoaderModule::class,
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(roversPresenter: RoversPresenter)
    fun inject(roversRVAdapter: RoversRVAdapter)
    fun inject(roverPresenter: RoverPresenter)
    fun inject(camerasRVAdapter: CamerasRVAdapter)
    fun inject(photosPresenter: PhotosPresenter)
    fun inject(photosRVAdapter: PhotosRVAdapter)




}