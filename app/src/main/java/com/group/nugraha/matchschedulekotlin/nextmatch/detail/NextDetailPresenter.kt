package com.group.nugraha.matchschedulekotlin.nextmatch.detail

import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.EventsResponse
import com.group.nugraha.matchschedulekotlin.model.TeamResponse
import com.group.nugraha.matchschedulekotlin.util.CoroutineContextProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync


class NextDetailPresenter (private val view: NextDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getEventDetail(eventId: String?, homeTeamId: String?, awayTeamId: String?){
        view.tunjukkanLoading()

        CoroutineScope(context.main) {
                val matchDetail = doAsync {
                    gson.fromJson(apiRepository.doRequest(ObjekSportDB.getLookUpNDP(eventId)), EventsResponse::class.java)
                }

                val homeTeam = async{
                    gson.fromJson(ApiRepository().doRequest(ObjekSportDB.getTeamDetail(homeTeamId)), TeamResponse::class.java)
                }
                val awayTeam = async{
                    gson.fromJson(ApiRepository().doRequest(ObjekSportDB.getTeamDetail(awayTeamId)), TeamResponse::class.java)
                }

                view.showDetail(matchDetail.await().Events, homeTeam.await().teams, awayTeam.await().teams)
                view.sembunyikanLoading()



        }
    }

}