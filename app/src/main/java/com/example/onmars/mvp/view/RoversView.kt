package com.example.onmars.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RoversView : MvpView {
    fun init()
    fun updateList()
    fun getDate()
    fun release()
}