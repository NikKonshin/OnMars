package com.example.onmars.mvp.di

import com.example.onmars.MainActivity
import com.example.onmars.mvp.di.modules.ApiModule
import com.example.onmars.mvp.di.modules.AppModule
import com.example.onmars.mvp.di.modules.CiceroneModule
import com.example.onmars.mvp.di.modules.RepoModule
import com.example.onmars.mvp.presenter.MainPresenter
import com.example.onmars.mvp.presenter.RoversPresenter
import com.example.onmars.mvp.ui.adapter.RoversRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[
    ApiModule::class,
    AppModule::class,
    CiceroneModule::class,
    RepoModule::class,
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(roversPresenter: RoversPresenter)
    fun inject(roversRVAdapter: RoversRVAdapter)
}