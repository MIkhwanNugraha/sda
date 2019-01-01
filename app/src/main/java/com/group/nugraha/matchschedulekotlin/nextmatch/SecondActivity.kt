package com.group.nugraha.matchschedulekotlin.nextmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.nextmatch.NextView
import com.group.nugraha.matchschedulekotlin.util.invisible
import com.group.nugraha.matchschedulekotlin.util.visible
import org.jetbrains.anko.ctx

class SecondActivity : AppCompatActivity(), NextView {

    private var eventsNext: MutableList<EventsItem> = mutableListOf()

    private lateinit var presenter: NextPresenter
    private lateinit var nAdapter: NextAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewEvent: RecyclerView


    private var name2: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layouteventnext)




        progressBar = findViewById(R.id.pgBarNext)
        swipeRefresh =findViewById(R.id.swipeEventNext)
        recyclerViewEvent = findViewById(R.id.rvEventNext)

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerViewEvent.layoutManager = layoutManager
        nAdapter = NextAdapter(this, eventsNext)
        recyclerViewEvent.adapter = nAdapter

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = NextPresenter(this, apiRepository, gson)
        presenter.getEventNext(NextView.LEAGUE_ID)


    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLOading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<EventsItem>) {
        swipeRefresh.isRefreshing = false
        eventsNext.clear()
        eventsNext.addAll(data)
        nAdapter.notifyDataSetChanged()
    }

}