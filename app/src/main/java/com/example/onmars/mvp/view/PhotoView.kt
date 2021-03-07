package com.example.onmars.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PhotoView : MvpView {
    fun init()
    fun initPhoto(url: String)
    fun initPhotoIfEmpty()
    fun isFavorites()
    fun isNotFavorites()
    fun startPushActivity(uri: String)
    fun showToastSave()
    fun showToastError(text: String)
}