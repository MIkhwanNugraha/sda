package com.group.nugraha.matchschedulekotlin.preferred

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.group.nugraha.matchschedulekotlin.R
import com.group.nugraha.matchschedulekotlin.db.Preferred
import com.group.nugraha.matchschedulekotlin.db.database
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.support.v4.onRefresh
import java.util.ArrayList

class PreferredActivity : AppCompatActivity() {
    private var preferred: MutableList<Preferred> = mutableListOf()
    private lateinit var adapter: PreferredAdapter
    private lateinit var rvPreferred: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layoutpreferred)

        progressBar = findViewById(R.id.pgBarPreferred)
        swipeRefresh =findViewById(R.id.swipePreferred)
        rvPreferred = findViewById(R.id.rvPreferred)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        rvPreferred.layoutManager = layoutManager

        adapter = PreferredAdapter(Context, preferred as ArrayList<Preferred>)
        rvPreferred.adapter = adapter
        showPreferred()

        swipeRefresh.onRefresh{
            preferred.clear()
            showPreferred()

        }
    }

    private fun showPreferred(){
        context?.database?.use{
            swipeRefresh.isRefreshing = false
            val results = select(Preferred.TABLE_PREFERRED)
            val favoriteee = results.parseList(classParser<Preferred>())
            preferred.addAll(favoriteee)
            adapter.notifyDataSetChanged()

        }
    }
}