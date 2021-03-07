package com.example.onmars.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.onmars.R
import com.example.onmars.mvp.model.image.IImageLoader
import com.example.onmars.mvp.presenter.list.IFavoritesPhotosListPresenter
import com.example.onmars.mvp.view.list.IFavoritesPhotosItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_favorites_photo.view.*
import javax.inject.Inject

class FavoritesPhotoRVAdapter(
    private val presenter: IFavoritesPhotosListPresenter
) : RecyclerView.Adapter<FavoritesPhotoRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer,
        IFavoritesPhotosItemView {
        override fun setPhoto(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_item_favorites)
        }

        override fun setRoverName(roverName: String) = with(containerView) {
            rover_name_value_item_favorites.text = roverName
        }

        override fun setCameraName(cameraName: String) = with(containerView) {
            camera_name_value_item_favorites.text = cameraName
        }

        override fun setDate(date: String) = with(containerView) {
            date_item_value_favorites.text = date
        }

        override fun favorites() = with(containerView) {
            favorites_button_item_favorites.setImageResource(R.drawable.ic_favorite)
        }

        override fun notFavorites() = with(containerView) {
            favorites_button_item_favorites.setImageResource(R.drawable.ic_not_favorite)
        }

        override var isFavorites: Boolean = true

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorites_photo, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        with(holder.containerView) {
            iv_item_favorites.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
            favorites_button_item_favorites.setOnClickListener {
                presenter.favoritesItemClickListener?.invoke(
                    holder
                )
            }
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}