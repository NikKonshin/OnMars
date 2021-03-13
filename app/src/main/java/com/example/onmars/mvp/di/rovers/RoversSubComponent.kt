package com.example.onmars.mvp.di.rovers

import com.example.onmars.mvp.di.rover.RoverSubComponent
import com.example.onmars.mvp.di.rovers.module.RoversModule
import com.example.onmars.mvp.presenter.RoversPresenter
import com.example.onmars.mvp.ui.adapter.RoversRVAdapter
import dagger.Subcomponent

@RoversScope
@Subcomponent(
    modules = [
        RoversModule::class
    ]
)
interface RoversSubComponent {
    fun roverSubcomponent(): RoverSubComponent
    fun inject(roversPresenter: RoversPresenter)
    fun inject(roversRVAdapter: RoversRVAdapter)
}