package com.example.onmars.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.onmars.R
import com.example.onmars.mvp.model.image.IImageLoader
import com.example.onmars.mvp.presenter.list.IPhotosListPresenter
import com.example.onmars.mvp.view.list.PhotosItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_photo.view.*
import javax.inject.Inject

class PhotosRVAdapter(
    private val presenter: IPhotosListPresenter,
) : RecyclerView.Adapter<PhotosRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, PhotosItemView {
        override fun setPhoto(uri: String) = with(containerView) {
            imageLoader.loadInto(uri, iv_photo)
        }

        override var pos: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}