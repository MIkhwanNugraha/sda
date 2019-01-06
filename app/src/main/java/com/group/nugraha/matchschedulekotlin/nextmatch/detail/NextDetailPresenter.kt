package com.group.nugraha.matchschedulekotlin.nextmatch.detail

import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.EventsResponse
import com.group.nugraha.matchschedulekotlin.model.TeamResponse
import com.group.nugraha.matchschedulekotlin.util.CoroutineContextProvider
import kotlinx.coroutines.*




class NextDetailPresenter (private val view: NextDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getEventDetail(eventId: String?, homeTeamId: String?, awayTeamId: String?){
        view.tunjukkanLoading()

        GlobalScope.launch(context.main) {
                val matchDetail = gson.fromJson(apiRepository
                    .doRequest(ObjekSportDB.getLookUpNDP(eventId)),
                    EventsResponse::class.java)


                val homeTeam = gson.fromJson(ApiRepository()
                    .doRequest(ObjekSportDB.getTeamDetail(homeTeamId)),
                    TeamResponse::class.java)

                val awayTeam =  gson.fromJson(ApiRepository()
                    .doRequest(ObjekSportDB.getTeamDetail(awayTeamId)),
                    TeamResponse::class.java)


                view.showDetail(matchDetail.Events, homeTeam.teams, awayTeam.teams)
                view.sembunyikanLoading()



        }
    }

}