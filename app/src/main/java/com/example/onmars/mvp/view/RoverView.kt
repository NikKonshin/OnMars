package com.example.onmars.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RoverView: MvpView {
    fun init()
    fun updateList()
    fun setName(text: String)
    fun setLandingDate(text: String)
    fun setLaunchDate(text: String)
    fun setStatus(text: String)
    fun setMaxSol(sol: Int)
    fun setMaxDate(text: String)
    fun setTotalPhoto(value: Int)
    fun getDate()

}