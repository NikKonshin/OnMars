package com.example.onmars.mvp.presenter

import android.util.Log
import com.example.onmars.mvp.model.cache.IFavoritesPhotoCache
import com.example.onmars.mvp.model.entity.favorites.FavoritesPhoto
import com.example.onmars.mvp.model.entity.scroll.ISaveScroll
import com.example.onmars.mvp.presenter.list.IFavoritesPhotosListPresenter
import com.example.onmars.mvp.view.FavoritesPhotoView
import com.example.onmars.mvp.view.list.IFavoritesPhotosItemView
import com.example.onmars.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

private const val TAG = "FavoritesPresenter"
private const val FAVORITES_FRAGMENT = "favorites"

class FavoritesPhotosPresenter() : MvpPresenter<FavoritesPhotoView>() {

    @Inject
    lateinit var favoritesCache: IFavoritesPhotoCache

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var position: ISaveScroll

    class FavoritesPhotosListPresenter : IFavoritesPhotosListPresenter {
        val favoritesPhotos = mutableListOf<FavoritesPhoto>()
        override var itemClickListener: ((IFavoritesPhotosItemView) -> Unit)? = null
        override var favoritesItemClickListener: ((IFavoritesPhotosItemView) -> Unit)? = null

        override fun bindView(view: IFavoritesPhotosItemView) {
            val favoritesPhoto = favoritesPhotos[view.pos]
            favoritesPhoto.uri.let {
                view.setPhoto(it)
            }
            favoritesPhoto.cameraName.let {
                view.setCameraName(it)
            }
            favoritesPhoto.roverName.let {
                view.setRoverName(it)
            }
            favoritesPhoto.date.let {
                view.setDate(it)
            }
            favoritesPhoto.isFavorites.let {
                if (it) {
                    view.favorites()
                } else {
                    view.notFavorites()
                }
            }
        }

        override fun getCount(): Int = favoritesPhotos.size

    }

    val favoritesPhotosListPresenter = FavoritesPhotosListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        loadClickListeners()
    }

    private fun loadClickListeners() {
        favoritesPhotosListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.PhotoScreen(favoritesPhotosListPresenter.favoritesPhotos[itemView.pos]))
        }

        favoritesPhotosListPresenter.favoritesItemClickListener = { view ->
            if (favoritesPhotosListPresenter.favoritesPhotos[view.pos].isFavorites) {
                view.notFavorites()
                view.isFavorites = false
                favoritesPhotosListPresenter.favoritesPhotos[view.pos].isFavorites = false
                favoritesCache.delete(favoritesPhotosListPresenter.favoritesPhotos[view.pos])
                    .toSingleDefault(favoritesPhotosListPresenter.favoritesPhotos[view.pos])
                    .subscribe({
                        Log.v(
                            TAG,
                            "DELETE${favoritesPhotosListPresenter.favoritesPhotos[view.pos].isFavorites}"
                        )
                    }, {
                        Log.v(TAG, "Error: ${it.message}")
                    })
            } else {
                view.favorites()
                view.isFavorites = true
                favoritesPhotosListPresenter.favoritesPhotos[view.pos].isFavorites = true
                favoritesCache.insert(favoritesPhotosListPresenter.favoritesPhotos[view.pos])
                    .toSingleDefault(favoritesPhotosListPresenter.favoritesPhotos[view.pos])
                    .subscribe({
                    }, {
                        Log.v(TAG, "Error: ${it.message}")
                    })
            }
        }
    }

    private fun loadData() {
        favoritesCache
            .getAllFavoritesPhoto()
            .observeOn(mainThreadScheduler)
            .subscribe({
                if (it.isEmpty()) {
                    router.replaceScreen(Screens.IfEmptyFavoritesScreen())
                }
                favoritesPhotosListPresenter.favoritesPhotos.clear()
                favoritesPhotosListPresenter.favoritesPhotos.addAll(it)
                viewState.updateList()
            }, {
                Log.v(TAG, "Error: ${it.message}")
            })
    }

    fun saveScroll(pos: Int) {
        position.saveScroll(FAVORITES_FRAGMENT, pos)
    }

    fun getPosition() =
        position.getPosition(FAVORITES_FRAGMENT)

    fun backPressed(): Boolean{
        router.backTo(Screens.RoversScreen())
//        router.exit()
        return true
    }
}