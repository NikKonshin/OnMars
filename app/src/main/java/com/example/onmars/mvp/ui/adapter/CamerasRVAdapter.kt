package com.example.onmars.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onmars.R
import com.example.onmars.mvp.presenter.list.ICameraListPresenter
import com.example.onmars.mvp.view.list.CameraItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cameras.view.*

class CamerasRVAdapter(
    private val presenter: ICameraListPresenter
) : RecyclerView.Adapter<CamerasRVAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer,
        CameraItemView {
        override fun setName(cameraName: String) = with(containerView) {
            button_camera.text = cameraName
        }

        override var pos = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cameras, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        with(holder.containerView) {
            button_camera.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}