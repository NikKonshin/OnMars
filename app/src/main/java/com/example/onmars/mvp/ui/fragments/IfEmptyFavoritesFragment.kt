package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.presenter.IfEmptyFavoritesPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.view.IfEmptyFavoritesView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class IfEmptyFavoritesFragment : MvpAppCompatFragment(), IfEmptyFavoritesView, BackButtonListener {

    val presenter by moxyPresenter {
        IfEmptyFavoritesPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_if_empty_favorites, container, false)
    }

    companion object {
        fun newInstance() = IfEmptyFavoritesFragment()
    }

    override fun backPressed(): Boolean =
        presenter.backPressed()
}
