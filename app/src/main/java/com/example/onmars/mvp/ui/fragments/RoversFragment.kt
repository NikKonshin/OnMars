package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.presenter.RoversPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.ui.adapter.RoversRVAdapter
import com.example.onmars.mvp.view.RoversView
import kotlinx.android.synthetic.main.fragment_rovers.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RoversFragment : MvpAppCompatFragment(), RoversView, BackButtonListener {

    companion object {
        fun newInstance() = RoversFragment()
    }

    private val presenter: RoversPresenter by moxyPresenter {
        RoversPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var adapter: RoversRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_rovers, null)


    override fun init() {
        rv_rovers.layoutManager = LinearLayoutManager(context)
        adapter = RoversRVAdapter(presenter.roversListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        rv_rovers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()


}