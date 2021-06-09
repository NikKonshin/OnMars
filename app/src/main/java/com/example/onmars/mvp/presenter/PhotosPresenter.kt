package com.example.onmars.mvp.presenter

import android.util.Log
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Photo
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.model.entity.favorites.FavoritesPhoto
import com.example.onmars.mvp.model.entity.scroll.ISaveScroll
import com.example.onmars.mvp.model.repo.IPhotoRepo
import com.example.onmars.mvp.presenter.list.IPhotosListPresenter
import com.example.onmars.mvp.view.PhotosView
import com.example.onmars.mvp.view.list.PhotosItemView
import com.example.onmars.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

private const val TAG = "PhotosPresenter"
private const val PHOTOS_FRAGMENT = "photos"

class PhotosPresenter(
    private val rover: Rover,
    private val camera: Camera,
    private val date: Date,
) : MvpPresenter<PhotosView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var photosRepo: IPhotoRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var position: ISaveScroll

    class PhotosListPresenter : IPhotosListPresenter {
        val photos = mutableListOf<Photo>()
        override var itemClickListener: ((PhotosItemView) -> Unit)? = null


        override fun bindView(view: PhotosItemView) {
            val photo = photos[view.pos]
            photo.imgSrc.let {
                view.setPhoto(it.toString())
                Log.v(TAG, it)
            }
        }

        override fun getCount(): Int = photos.size
    }

    val photosListPresenter = PhotosListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        photosListPresenter.itemClickListener = { itemView ->
            val favoritesPhoto = photosListPresenter.photos[itemView.pos].let {
                FavoritesPhoto(
                    it.id,
                    it.imgSrc,
                    date.dateString,
                    it.rover.name,
                    it.camera.fullName,
                    false
                )
            }
            router.navigateTo(Screens.PhotoScreen(favoritesPhoto))
        }
    }

    private fun loadData() {
        photosRepo.getPhotosFromCameraDate(camera, date.dateString, rover)
            .observeOn(mainThreadScheduler)
            .subscribe({
                if (it.photos.isEmpty()) {
                    router.replaceScreen(Screens.IfEmptyScreen())
                } else {
                    photosListPresenter.photos.clear()
                    photosListPresenter.photos.addAll(it.photos)
                    viewState.updateList()
                }
            }, {
                println("Error: ${it.message}")
            })
    }

    fun saveScroll(pos: Int) {
        position.saveScroll(PHOTOS_FRAGMENT, pos)
    }

    fun getPosition() =
        position.getPosition(PHOTOS_FRAGMENT)

    fun backPressed(): Boolean {
        router.exit()
        router.backTo(Screens.RoverScreen(rover, date))
        return true
    }
}