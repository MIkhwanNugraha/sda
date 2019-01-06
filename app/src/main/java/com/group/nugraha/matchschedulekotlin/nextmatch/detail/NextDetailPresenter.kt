package com.group.nugraha.matchschedulekotlin.nextmatch.detail

import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.EventsResponse
import com.group.nugraha.matchschedulekotlin.nextmatch.NextView
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextDetailPresenter (private val view: NextDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getLookUpEvents(eventId: String?, homeTeamId: String?, awayTeamId: String?){
        view.tunjukkanLoading()

        async(context.main) {
            async(context.main){
                val matchDetail = bg {
                    gson.fromJson(apiRepository.doRequest(ObjekSportDB.getMatchDetail(eventId)), EventsResponse::class.java)
                }

            }


        }
    }
}