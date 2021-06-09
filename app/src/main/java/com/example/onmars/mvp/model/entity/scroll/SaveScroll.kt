package com.example.onmars.mvp.model.entity.scroll

private const val PHOTOS_FRAGMENT = "photos"
private const val FAVORITES_FRAGMENT = "favorites"

class SaveScroll : ISaveScroll {
    private val saveScrollList = mutableMapOf(
        PHOTOS_FRAGMENT to 0, FAVORITES_FRAGMENT to 0
    )

    override fun saveScroll(key: String, pos: Int) {
        if (pos != -1) {
            saveScrollList[key] = pos
        }
    }

    override fun getPosition(key: String): Int? {
        return saveScrollList[key]
    }
}