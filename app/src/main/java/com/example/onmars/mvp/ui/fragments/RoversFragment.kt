package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.di.rovers.RoversSubComponent
import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.presenter.RoversPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.ui.MainActivity
import com.example.onmars.mvp.ui.adapter.RoversRVAdapter
import com.example.onmars.mvp.view.RoversView
import kotlinx.android.synthetic.main.fragment_rovers.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.util.*

class RoversFragment : MvpAppCompatFragment(), RoversView, BackButtonListener {

    companion object {
        fun newInstance() = RoversFragment()
    }

    private var roversSubComponent: RoversSubComponent? = null

    private val presenter: RoversPresenter by moxyPresenter {
        roversSubComponent = App.instance.initRoversSubComponent()
        RoversPresenter().apply {
            roversSubComponent?.inject(this)
        }
    }
    private var adapter: RoversRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_rovers, null)


    override fun init() {
        val activity = activity as MainActivity
        activity.setSupportActionBar(toolbar_rovers_fragment)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)

        rv_rovers.layoutManager = LinearLayoutManager(context)
        adapter = RoversRVAdapter(presenter.roversListPresenter).apply {
            roversSubComponent?.inject(this)
        }
        rv_rovers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun getDate() {
        val calendar: Calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = Date()
        date.year = year
        date.month = month
        date.day = day
        date.dateString = "$year-${month}-$day"
        presenter.setDate(date)
    }

    override fun release() {
        roversSubComponent = null
        App.instance.releaseRoversSubComponent()
    }

    override fun backPressed() = presenter.backPressed()
}