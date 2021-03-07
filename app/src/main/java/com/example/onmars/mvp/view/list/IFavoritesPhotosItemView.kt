package com.example.onmars.mvp.view.list

interface IFavoritesPhotosItemView : IItemView {
    fun setPhoto(url: String)
    fun setRoverName(roverName: String)
    fun setCameraName(cameraName: String)
    fun setDate(date: String)
    fun favorites()
    fun notFavorites()
    var isFavorites: Boolean
}