package com.example.onmars.mvp.presenter.list

import com.example.onmars.mvp.view.list.IItemView

interface IListWithFavoritesPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    var favoritesItemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}