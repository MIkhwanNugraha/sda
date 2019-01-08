package com.group.nugraha.matchschedulekotlin.detail

import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.EventsResponse
import com.group.nugraha.matchschedulekotlin.model.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextDetailPresenter (private val view: NextDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson){

    fun getEventDetail(eventId: String?, homeTeamId: String?, awayTeamId: String?){
        view.tunjukkanLoading()

        doAsync {
            val matchDetail = gson.fromJson(apiRepository
                    .doRequest(ObjekSportDB.getLookUpNDP(eventId)),
                    EventsResponse::class.java)

            val homeTeam = gson.fromJson(ApiRepository()
                    .doRequest(ObjekSportDB.getTeamDetail(homeTeamId)),
                    TeamResponse::class.java)

            val awayTeam =  gson.fromJson(ApiRepository()
                    .doRequest(ObjekSportDB.getTeamDetail(awayTeamId)),
                    TeamResponse::class.java)

            uiThread {
                view.showDetail(matchDetail.Events, homeTeam.teams, awayTeam.teams)
                view.sembunyikanLoading()
            }
        }
    }
}