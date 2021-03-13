package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.di.favorites.FavoritesSubComponent
import com.example.onmars.mvp.presenter.FavoritesPhotosPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.ui.adapter.FavoritesPhotoRVAdapter
import com.example.onmars.mvp.view.FavoritesPhotoView
import kotlinx.android.synthetic.main.fragment_favorites_photo.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FavoritesPhotoFragment : MvpAppCompatFragment(), FavoritesPhotoView, BackButtonListener {

    private var favoritesSubComponent: FavoritesSubComponent? = null
    private val presenter by moxyPresenter {
        favoritesSubComponent = App.instance.initFavoritesSubComponent()
        FavoritesPhotosPresenter().apply {
            favoritesSubComponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites_photo, container, false)
    }

    companion object {
        fun newInstance() =
            FavoritesPhotoFragment()
    }

    private var adapter: FavoritesPhotoRVAdapter? = null

    override fun init() {
        adapter = FavoritesPhotoRVAdapter(presenter.favoritesPhotosListPresenter).apply {
            favoritesSubComponent?.inject(this)
        }
        rv_photos_fragment_favorites_photos.layoutManager = LinearLayoutManager(context)
        rv_photos_fragment_favorites_photos.adapter = adapter

        rv_photos_fragment_favorites_photos.scrollToPosition(presenter.getPosition() ?: 0)

        rv_photos_fragment_favorites_photos.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val position =
                    ((rv_photos_fragment_favorites_photos.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition())
                presenter.saveScroll(position)
            }
        })
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        favoritesSubComponent = null
        App.instance.releaseFavoritesSubComponent()
    }

    override fun backPressed(): Boolean =
        presenter.backPressed()
}