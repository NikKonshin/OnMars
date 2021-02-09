package com.example.onmars.mvp.presenter

import android.util.Log
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Photo
import com.example.onmars.mvp.model.repo.IPhotoRepo
import com.example.onmars.mvp.presenter.list.IPhotosListPresenter
import com.example.onmars.mvp.view.PhotosView
import com.example.onmars.mvp.view.list.PhotosItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

private const val TAG = "PhotosPresenter"

class PhotosPresenter(
    private val roverName: String,
    private val camera: Camera,
    private val date: String,
) : MvpPresenter<PhotosView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var photosRepo: IPhotoRepo

    @Inject
    lateinit var router: Router

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
    }

    private fun loadData() {
        photosRepo.getPhotosFromCameraDate(camera.name, date, roverName)
            .observeOn(mainThreadScheduler)
            .subscribe({
                photosListPresenter.photos.clear()
                photosListPresenter.photos.addAll(it.photos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}