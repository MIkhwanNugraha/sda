package com.group.nugraha.matchschedulekotlin.lastmatch

import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.EventsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastPresenter(private val view: LastView, private val apiRepository: ApiRepository, private val gson: Gson){
    fun getEventLast(leagueId: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(ObjekSportDB.getEventLast(leagueId)),
                EventsResponse::class.java)
            uiThread {
                view.hideLOading()
                view.showEventList(data.Events)
            }
        }
    }
}