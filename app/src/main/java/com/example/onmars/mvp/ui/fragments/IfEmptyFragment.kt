package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onmars.mvp.ui.MainActivity
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.presenter.IfEmptyPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.view.IfEmptyView
import kotlinx.android.synthetic.main.fragment_if_empty.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class IfEmptyFragment : MvpAppCompatFragment(), IfEmptyView, BackButtonListener {

    val presenter by moxyPresenter {
        IfEmptyPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_if_empty, container, false)
    }

    companion object {
        fun newInstance() = IfEmptyFragment()
    }

    override fun init() {
        val activity = activity as MainActivity
        activity.setSupportActionBar(toolbar_if_empty_fragment)
        val bar = activity.supportActionBar
        bar?.setDisplayShowTitleEnabled(false)
        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.setDisplayShowHomeEnabled(true)
        iv_if_empty_fragment.setImageResource(R.drawable.placeholder_if_empty)
    }

    override fun backPressed(): Boolean =
        presenter.backPressed()
}