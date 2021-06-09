package com.example.onmars.mvp.presenter

import android.util.Log
import com.example.onmars.mvp.model.cache.IFavoritesPhotoCache
import com.example.onmars.mvp.model.entity.favorites.FavoritesPhoto
import com.example.onmars.mvp.model.image.ISaveImage
import com.example.onmars.mvp.view.PhotoView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

private const val TAG = "PhotoPresenter"

class PhotoPresenter(
    private val favoritesPhoto: FavoritesPhoto?
) : MvpPresenter<PhotoView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var favoritesCache: IFavoritesPhotoCache

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var saveImage: ISaveImage

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {

        if (favoritesPhoto != null) {
            viewState.initPhoto(favoritesPhoto.uri)
            if (favoritesPhoto.isFavorites) {
                viewState.isFavorites()
            } else {
                viewState.isNotFavorites()
            }
        } else {
            viewState.initPhotoIfEmpty()
        }
    }

    fun itemClickListener(): Boolean {
        if (favoritesPhoto != null) {
            if (favoritesPhoto.isFavorites) {
                favoritesPhoto.isFavorites = false
                favoritesCache.delete(favoritesPhoto)
                    .toSingleDefault(favoritesPhoto)
                    .subscribe({
                        Log.v(TAG, "DELETE: ${favoritesPhoto.isFavorites}")
                    }, {
                        Log.v(TAG, "Error: ${it.message}")
                    })
                viewState.isNotFavorites()
            } else {
                favoritesPhoto.isFavorites = true
                favoritesCache.insert(favoritesPhoto)
                    .toSingleDefault(favoritesPhoto)
                    .subscribe({
                        Log.v(TAG, "INSERT${favoritesPhoto.isFavorites}")
                    }, {
                        Log.v(TAG, "Error: ${it.message}")
                    })
                viewState.isFavorites()
            }
        }
        return true
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun saveAndShareImage() {
        if (favoritesPhoto != null)
            saveImage.savePicture(
                favoritesPhoto.uri,
                favoritesPhoto.date,
                favoritesPhoto.roverName,
                favoritesPhoto.cameraName,
                favoritesPhoto.id.toString()
            )
                .subscribe({
                    saveImage.getPath()?.let { viewState.startPushActivity(it) }
                    Log.v(TAG, "onComplete")
                }, {
                    Log.v(TAG, "Error: ${it.message}")
                })
    }

    fun saveImage() {
        if (favoritesPhoto != null)
            saveImage.savePicture(
                favoritesPhoto.uri,
                favoritesPhoto.date,
                favoritesPhoto.roverName,
                favoritesPhoto.cameraName,
                favoritesPhoto.id.toString()
            )
                .subscribe({
                    viewState.showToastSave()
                    Log.v(TAG, "onComplete")
                }, {
                    viewState.showToastError(it.message.toString())
                })
    }

    fun delete() {
        saveImage.delete().subscribe({
            Log.v(TAG, "onComplete")
        }, {
            Log.v(TAG, "Error: ${it.message}")
        })
    }

    val isFavorites = favoritesPhoto?.isFavorites

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}