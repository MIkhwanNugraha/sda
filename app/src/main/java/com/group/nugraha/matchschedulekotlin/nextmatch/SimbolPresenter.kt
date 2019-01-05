package com.group.nugraha.matchschedulekotlin.nextmatch


import android.support.compat.R.id.async
import android.widget.ImageView
import com.google.gson.Gson

import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.TeamResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.bg

import kotlin.coroutines.CoroutineContext

class SimbolPresenter(private val view: ImageView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getSimbol(teamId: String?){
        GlobalScope.launch(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(ObjekSportDB.getLookUpNDP(teamId)),
                    TeamResponse::class.java)
            }
            Picasso.get().load(data.await().teams!![0].strTeamBadge).into(view)
        }
    }
    open class CoroutineContextProvider{
        open val main: CoroutineContext by lazy { Dispatchers.Main }
    }
}