package com.example.onmars.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onmars.R
import com.example.onmars.mvp.presenter.list.IRoverListPresenter
import com.example.onmars.mvp.view.list.RoverItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rovers.view.*

class RoversRVAdapter(
    private val presenter: IRoverListPresenter,
) : RecyclerView.Adapter<RoversRVAdapter.ViewHolder>(){
    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView),
            LayoutContainer, RoverItemView{

        override fun setName(name: String) = with(containerView) {
            button_rover.text = name
        }

        override var pos = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rovers, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()
}